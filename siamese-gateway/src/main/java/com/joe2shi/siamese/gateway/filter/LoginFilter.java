package com.joe2shi.siamese.gateway.filter;

import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.gateway.config.FilterProperties;
import com.joe2shi.siamese.gateway.config.JwtProperties;
import com.joe2shi.siamese.gateway.utils.JwtUtils;
import com.joe2shi.siamese.gateway.utils.UserInfo;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
@EnableConfigurationProperties(value = {JwtProperties.class, FilterProperties.class})
public class LoginFilter extends ZuulFilter {
    @Resource
    private FilterProperties filterProperties;
    @Resource
    private JwtProperties jwtProperties;

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
            UserInfo info = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
            context.addZuulRequestHeader(SystemConstant.STRING_ID, info.getId());
        } catch (Exception e) {
            context.setSendZuulResponse(Boolean.FALSE);
            context.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
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
