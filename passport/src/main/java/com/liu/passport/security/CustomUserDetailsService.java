package com.liu.passport.security;

import com.liu.passport.entity.User;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    public User loadUserByAccountnameAndPassword(String accountName, String password){
        return new User(accountName,"", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

    public User loadUserByEmailAndCode(String email,String verifyCode){
        return new User("",email, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
