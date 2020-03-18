package com.liu.clouddisk.controller;

import com.liu.clouddisk.entity.UserSignCount;
import com.liu.clouddisk.remote.PassportRemoteClient;
import com.liu.clouddisk.service.CapacityService;
import com.liu.clouddisk.service.UserSignCountService;
import com.liu.utils.api.ApiResponse;
import com.liu.utils.time.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/sing")
public class SignController {
    private Logger logger = LoggerFactory.getLogger(SignController.class);


    @Autowired
    private CapacityService capacityService;

    @Autowired
    private UserSignCountService userSignCountService;

    @Autowired
    private PassportRemoteClient passportRemoteClient;

    /**
     * 用户签到
     *
     * @param principal
     * @return
     */
    @PostMapping
    public ApiResponse<String> sign(Principal principal) {
        Map<String, Object> userInfo = passportRemoteClient.userInfo(principal.getName());
        long userId = Long.valueOf(userInfo.get("id").toString());
        UserSignCount userSignCount = userSignCountService.selectByUserId(userId).get();

        LocalDate now = LocalDate.now();
        int timeDifference = TimeUtil.getTimeDifference(userSignCount.getLastSign(), now);
        if (timeDifference == 0) {
            //用户当天已经签到  直接返回
            return new ApiResponse<>("您今天已经签到！");
        } else if (timeDifference == 1) {
            //用户连续签到
            userSignCount.addSignCount();
            userSignCount.setLastSign(now);
            int size = userSignCount.getSignCount() % 7;
            capacityService.addCapacity(userId, Long.valueOf(size + 5));
            userSignCountService.update(userSignCount);
        } else {
            //用户签到
            userSignCount.setSignCount(1);
            userSignCount.setLastSign(now);
            capacityService.addCapacity(userId, Long.valueOf(5));
            userSignCountService.update(userSignCount);
        }
        return new ApiResponse<>("签到成功");

    }
}
