package com.liu.utils.redis;

public class RedisKeyUtil {
    public static String SPLIT=":";
    public static String BIZ_LIKE="LIKE";
    public static String BIZ_DISLIKE="DISLIKE";
    public static String BIZ_EVENTQUEUE = "EVENT_QUEUE";

    //粉丝
    public static String BIZ_FOLLOWER = "FOLLOWER";

    //关注对象
    public static String BIZ_FOLLOWEE = "FOLLOWEE";
    public static String BIZ_TIMELINE = "TIMELINE";


    //关于排序
    public static String QUES_VIEW = "QUES_VIEW";
    public static String QUES_LIKE = "QUES_LIKE";

    public static String getQuesViewKey(int questionId){
        return QUES_VIEW+SPLIT+questionId;
    }

    public static  String getQuesLikeKey(int questionId){
        return QUES_LIKE+SPLIT+questionId;
    }


    public static String getLikeKey(int entityType, long entityId){
        return BIZ_LIKE+SPLIT+entityType+entityId;
    }
    public static String getDislikeKey(int entityType, long entityId) {
        return BIZ_DISLIKE + SPLIT + entityType + entityId;
    }

    //获取消息队列的key
    public static String getEventQueueKey(){
        return BIZ_EVENTQUEUE;
    }

    //获取粉丝的key
    public static String getFollowerKey(int entityType, long entityId){
        return BIZ_FOLLOWER+SPLIT+entityType+SPLIT+entityId;
    }

    //获取关注对象的key
    public static String getFolloweeKey(int entityType,long userId){
        return BIZ_FOLLOWEE+SPLIT+userId+SPLIT+entityType;
    }

    public static String getTimelineKey(long userId){
        return BIZ_FOLLOWEE+SPLIT+userId;
    }


















    public static String getEventQueue(){
        return BIZ_EVENTQUEUE;
    }

}
