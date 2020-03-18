package com.liu.passport.granter;

import com.liu.passport.entity.User;
import com.liu.passport.security.CustomUserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

public class AccountnameCodeTokenGranter extends AbstractCustomTokenGranter {
    protected CustomUserDetailsService userDetailsService;

    public AccountnameCodeTokenGranter(CustomUserDetailsService userDetailsService, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, "pwd");
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected User getCustomUser(Map<String, String> parameters) {
        String accountName = parameters.get("accountName");
        String password = parameters.get("password");
        return userDetailsService.loadUserByAccountnameAndPassword(accountName, password);
    }
}
