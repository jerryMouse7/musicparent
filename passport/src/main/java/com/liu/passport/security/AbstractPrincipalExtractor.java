//package com.liu.passport.security;
//
//import com.liu.passport.entity.User;
//import com.liu.passport.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
//
//import java.util.Map;
//
//public abstract class AbstractPrincipalExtractor implements PrincipalExtractor {
//    @Autowired
//    protected UserService userService;
//
//    public abstract User getUserById(Long id);
//
//    @Override
//    public Object extractPrincipal(Map<String, Object> map) {
//        Long id = (Long) map.get("id");
//        User user = getUserById(id);
//        if(user == null){
//            user = new User();
//            user.setAccountName(id.toString());
//            userService.insertUser(user);
//        }
//        return user;
//    }
//}
