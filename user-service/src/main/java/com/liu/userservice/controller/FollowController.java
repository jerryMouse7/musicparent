package com.liu.userservice.controller;

import com.liu.userservice.dto.ViewObjectDto;
import com.liu.userservice.entity.User;
import com.liu.userservice.remote.PassportRemoteClient;
import com.liu.userservice.service.FollowService;
import com.liu.userservice.service.UserService;
import com.liu.utils.api.ApiResponse;
import com.liu.utils.api.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/follow")
public class FollowController {
    @Autowired
    private FollowService followService;

    @Autowired
    private PassportRemoteClient passportRemoteClient;

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ApiResponse<String> followUser(@RequestParam("entityId") long entityId, Principal principal) {
        System.out.println(principal.getName());
//        PassportUser operator = UserAuthenticationExtractor.extract(principal);
        Map<String, Object> userInfo = passportRemoteClient.userInfo(principal.getName());
        long userId = Long.valueOf(userInfo.get("id").toString());
        boolean result = followService.follow(userId, EntityType.ENTITY_USER, entityId);

        //发送异步事件

        //返回关注人数
        long followerAcount = followService.getFollowerCount(EntityType.ENTITY_USER, userId);
        return new ApiResponse<>(String.valueOf(followerAcount));
    }

    //取消关注
    @DeleteMapping("/user")
    public ApiResponse<String> followers(@RequestParam("entityId") long entityId, Principal principal) {
        Map<String, Object> userInfo = passportRemoteClient.userInfo(principal.getName());
        long userId = Long.valueOf(userInfo.get("id").toString());
        boolean result = followService.unfollow(userId, EntityType.ENTITY_USER, entityId);
        long followerAcount = followService.getFollowerCount(EntityType.ENTITY_USER, userId);
        return new ApiResponse<>(String.valueOf(followerAcount));
    }

    public ApiResponse<Long> getFollowersCount(Principal principal) {
        Map<String, Object> userInfo = passportRemoteClient.userInfo(principal.getName());
        long userId = Long.valueOf(userInfo.get("id").toString());
        Long count = followService.getFollowerCount(EntityType.ENTITY_USER, userId);
        return new ApiResponse<>(count);
    }

    //返回用户的粉丝
    @GetMapping("/{userId}/followers")
    public ApiResponse<List<ViewObjectDto>> getFollowers(@RequestParam("page") int page, @RequestParam("size") int size, Principal principal) {
        Map<String, Object> userInfo = passportRemoteClient.userInfo(principal.getName());
        long userId = Long.valueOf(userInfo.get("id").toString());
        List<Long> followerIds = followService.getFollowers(EntityType.ENTITY_USER, userId, page, size);

        List<User> users = userService.selectUserByIds(followerIds);

        return new ApiResponse<>(getUserInfo(userId,users));
    }

    //获取关注者
    @GetMapping("/{userId}/followees")
    public ApiResponse<List<ViewObjectDto>> getFollowees(@RequestParam("page") int page, @RequestParam("size") int size, Principal principal) {
        Map<String, Object> userInfo = passportRemoteClient.userInfo(principal.getName());
        long userId = Long.valueOf(userInfo.get("id").toString());
        List<Long> followeeIds = followService.getFollowees(EntityType.ENTITY_USER, userId, page, size);

        List<User> followees = userService.selectUserByIds(followeeIds);

        return new ApiResponse<>(getUserInfo(userId, followees));
    }

    private List<ViewObjectDto> getUserInfo(long localUserId, List<User> users) {
        List<ViewObjectDto> userInfo = new ArrayList<>();
        for (User user : users) {
            ViewObjectDto viewObjectDto = new ViewObjectDto();
            viewObjectDto.set("username", user.getAccountName());
            viewObjectDto.set("followerCount", followService.getFollowerCount(EntityType.ENTITY_USER, user.getId()));
            viewObjectDto.set("followeeCount", followService.getFolloweeCount(EntityType.ENTITY_USER, user.getId()));
            viewObjectDto.set("followed", followService.isFollower(localUserId, EntityType.ENTITY_USER, user.getId()));
            userInfo.add(viewObjectDto);
        }

        return userInfo;
    }
}
