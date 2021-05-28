package com.joe2shi.siamese.gateway.filter;

import com.alibaba.nacos.client.utils.JSONUtils;
import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.common.enums.HttpHeaderEnum;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.utils.JwtUtils;
import com.joe2shi.siamese.common.utils.UserInfo;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.gateway.config.FilterProperties;
import com.joe2shi.siamese.gateway.config.JwtProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.SneakyThrows;
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
@SuppressWarnings("rawtypes")
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
        return !requestURI.contains(SystemConstant.STRING_SWAGGER_JSON) && !isAllowPath(requestURI);
    }

    @SneakyThrows
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
                throw new SiameseException(ResponseEnum.INVALID_TOKEN);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            context.setSendZuulResponse(Boolean.FALSE);
            context.setResponseStatusCode(HttpStatus.OK.value());
            context.addZuulResponseHeader(HttpHeaderEnum.CONTENT_TYPE_JSON.getName(), HttpHeaderEnum.CONTENT_TYPE_JSON.getValue());
            context.setResponseBody(JSONUtils.serializeObject(new SiameseResult(ResponseEnum.UNAUTHORIZED)));
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
