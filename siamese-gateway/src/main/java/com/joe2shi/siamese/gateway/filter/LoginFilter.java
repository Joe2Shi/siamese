package com.joe2shi.siamese.gateway.filter;

import com.joe2shi.siamese.common.constant.LoggerConstant;
import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.gateway.config.FilterProperties;
import com.joe2shi.siamese.gateway.config.JwtProperties;
import com.joe2shi.siamese.gateway.utils.JwtUtils;
import com.joe2shi.siamese.gateway.utils.UserInfo;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@EnableConfigurationProperties(value = {JwtProperties.class, FilterProperties.class})
@Slf4j
public class LoginFilter extends ZuulFilter {
    @Resource
    private FilterProperties filterProperties;
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String requestURI = request.getRequestURI();
        return !isAllowPath(requestURI);
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getHeader(SystemConstant.STRING_TOKEN);
        try {
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
            String redisToken = redisTemplate.boundValueOps(userInfo.getId()).get();
            if (!StringUtils.isBlank(redisToken) && token.equals(redisToken)) {
                // token available extension of time
                redisTemplate.expire(userInfo.getId(), SystemConstant.NUMBER_THIRTY, TimeUnit.MINUTES);
                context.addZuulRequestHeader(SystemConstant.STRING_ID, userInfo.getId());
            } else {
                // token invalid or tampered
                throw new RuntimeException(LoggerConstant.INVALID_TOKEN);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            context.setSendZuulResponse(Boolean.FALSE);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }

    private Boolean isAllowPath(String uri) {
        List<String> allowPaths = filterProperties.getAllowPaths();
        boolean allow = Boolean.FALSE;
        for (String allowPath : allowPaths) {
            if (uri.startsWith(allowPath)) {
                allow = Boolean.TRUE;
                break;
            }
        }
        return allow;
    }
}
