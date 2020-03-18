package com.liu.passport.granter;

import com.liu.passport.entity.User;
import com.liu.passport.security.CustomUserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

public class EmailCodeTokenGranter extends AbstractCustomTokenGranter {

    private CustomUserDetailsService userDetailsService;
    protected EmailCodeTokenGranter(CustomUserDetailsService userDetailsService,
                                    AuthorizationServerTokenServices tokenServices,
                                    ClientDetailsService clientDetailsService,
                                    OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, "verifyCode");
        this.userDetailsService =  userDetailsService;
    }

    @Override
    protected User getCustomUser(Map<String, String> parameters) {
        String email = parameters.get("email");
        String verifyCode = parameters.get("verifyCode");
        return userDetailsService.loadUserByEmailAndCode(email,verifyCode);
    }
}
