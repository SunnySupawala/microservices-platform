package com.in.company.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class FeignAuthInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof JwtAuthenticationToken jwtAuth){
            String tokenValue = jwtAuth.getToken().getTokenValue();
            requestTemplate.header("Authorization", "Bearer "+ tokenValue);
        }
    }
}
