package com.liu.passport.security;

import com.liu.passport.entity.User;
import com.liu.passport.enums.UserDef;
import com.liu.passport.exception.BadRequestException;
import com.liu.passport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    //spring security权限前缀
    private final String SPRING_ROLE_PREFIX = "ROLE_";

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        User user = userService.findByAccountNameAndState(accountName,
                UserDef.State.NORMAL.getCode());
//        User user = new User();
//        user.setPassword("123");
//        user.setType(1);
//        user.setId(1L);
            String password = user.getPassword();
            //用户角色列表
            List<GrantedAuthority> authorities = new ArrayList<>(2);

            if (user.getType() == 1) {
                authorities.add(new SimpleGrantedAuthority(SPRING_ROLE_PREFIX + "admin"));
            } else {
                authorities.add(new SimpleGrantedAuthority(SPRING_ROLE_PREFIX + "user"));
            }

            return new org.springframework.security.core.userdetails.User(
                    accountName, password, authorities);
    }

}





















