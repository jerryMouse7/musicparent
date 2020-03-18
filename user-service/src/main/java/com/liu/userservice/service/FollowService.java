package com.liu.userservice.service;

import java.util.List;

public interface FollowService {

    boolean follow(long userId, int entityType, long entityId);

    boolean unfollow(long userId, int entityType, long entityId);

    //获取关注着
    //获取关注者   分页
    List<Long> getFollowers(int entityType, long entityId, int count);

    List<Long> getFollowers(int entityType, long entityId, int offset, int count);

    //查看关注列表
    List<Long> getFollowees(int entityType, long userId, int count);

    List<Long> getFollowees(int entityType, long userId, int offset, int limit);

    //获取关注者的数量
    long getFollowerCount(int entityType, long entityId);

    //获取我关注的数量
    long getFolloweeCount(int entityType, long userId);

    //判断两者之间的关注关系
    boolean isFollower(long userId, int entityType, long entityId);


}
