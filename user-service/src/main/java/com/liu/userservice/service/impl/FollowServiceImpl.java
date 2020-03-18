package com.liu.userservice.service.impl;

import com.liu.userservice.service.FollowService;
import com.liu.utils.redis.RedisKeyUtil;
import com.liu.utils.redis.StringRedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private StringRedisClient stringRedisClient;

    @Override
    public boolean follow(long userId, int entityType, long entityId) {
        String followerKey = RedisKeyUtil.getFollowerKey(entityType, entityId);
        String followeeKey = RedisKeyUtil.getFolloweeKey(entityType, userId);

        LocalDateTime localDateTime = LocalDateTime.now();

//        stringRedisClient.multi();
        //粉丝加1
        boolean resultA = stringRedisClient.zAdd(followerKey, String.valueOf(userId), 0.0);
        //关注者加1
        boolean resultB = stringRedisClient.zAdd(followeeKey, String.valueOf(entityId), 0.0);

//        stringRedisClient.exec();

        return resultA && resultB;

    }

    @Override
    public boolean unfollow(long userId, int entityType, long entityId) {
        String followerKey = RedisKeyUtil.getFollowerKey(entityType, entityId);
        String followeeKey = RedisKeyUtil.getFolloweeKey(entityType, userId);

        LocalDateTime localDateTime = LocalDateTime.now();

        stringRedisClient.multi();

        //粉丝加1
        long resultA = stringRedisClient.zRemove(followerKey, String.valueOf(userId), localDateTime.toEpochSecond(ZoneOffset.of("+8")));
        //关注者加1
        long resultB = stringRedisClient.zRemove(followeeKey, String.valueOf(entityId), localDateTime.toEpochSecond(ZoneOffset.of("+8")));

        stringRedisClient.exec();

        return resultA > 0 && resultB > 0;
    }

    @Override
    public List<Long> getFollowers(int entityType, long entityId, int count) {
        return getFollowers(entityType, entityId, 0, count);
    }

    @Override
    public List<Long> getFollowers(int entityType, long entityId, int offset, int count) {
        String followerKey = RedisKeyUtil.getFollowerKey(entityType, entityId);

        return getIdsFromSet(stringRedisClient.zRange(followerKey, offset, offset + count));
    }

    @Override
    public List<Long> getFollowees(int entityType, long userId, int count) {
        return getFollowees(entityType, userId, 0, count);
    }

    @Override
    public List<Long> getFollowees(int entityType, long userId, int offset, int limit) {
        String followeeKey = RedisKeyUtil.getFolloweeKey(entityType, userId);
        return getIdsFromSet(stringRedisClient.zRange(followeeKey, offset, offset + limit));
    }

    @Override
    public long getFollowerCount(int entityType, long entityId) {
        String followerKey = RedisKeyUtil.getFollowerKey(entityType, entityId);
        return stringRedisClient.zZCard(followerKey);
    }

    @Override
    public long getFolloweeCount(int entityType, long userId) {
        String followeeKey = RedisKeyUtil.getFolloweeKey(entityType, userId);
        return stringRedisClient.zZCard(followeeKey);
    }

    @Override
    public boolean isFollower(long userId, int entityType, long entityId) {
        String followerKey = RedisKeyUtil.getFollowerKey(entityType, entityId);
        return stringRedisClient.zScore(followerKey, String.valueOf(userId)) != null;
    }

    private List<Long> getIdsFromSet(Set<String> idset) {
        List<Long> ids = new ArrayList<>();
        for (String string : idset) {
            ids.add(Long.parseLong(string));
        }
        return ids;
    }
}
