package com.liu.clouddisk.controller;

import com.liu.clouddisk.entity.Capacity;
import com.liu.clouddisk.remote.PassportRemoteClient;
import com.liu.clouddisk.service.CapacityService;
import com.liu.clouddisk.service.UserSignCountService;
import com.liu.clouddisk.vo.CapacityVo;
import com.liu.utils.api.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author liu
 * 网盘的容量接口
 */
@RestController
@RequestMapping("/api/capacity")
public class CapacityController {
    private Logger logger = LoggerFactory.getLogger(CapacityController.class);

    @Autowired
    private CapacityService capacityService;

    @Autowired
    private UserSignCountService userSignCountService;

    @Autowired
    private PassportRemoteClient passportRemoteClient;


    @PostMapping("/init")
    public ApiResponse<String> init(@RequestParam("userId") Long userId) {
        capacityService.initCapacity(userId);
        userSignCountService.initUserSignCount(userId);
        return new ApiResponse<>("添加成功");
    }

    public ApiResponse<CapacityVo> getCapacity(Principal principal){
        Map<String,Object> userInfo =  passportRemoteClient.userInfo(principal.getName());
        Capacity capacity = capacityService.getCapacityByUserId((Long) userInfo.get("id"));
        CapacityVo capacityVo = new CapacityVo();
        capacityVo.setUserdCapacity(capacity.getUsedCapacity());
        if(LocalDateTime.now().isBefore(capacity.getExpireDate())){
            capacityVo.setTotalCapacity(capacity.getTempCapacity() + capacity.getTotalCapacity());
        }
        return new ApiResponse<>(capacityVo);
    }

//    public ApiResponse<String> addTempCapacity(Principal principal){
//        capacityService
//    }

    @PostMapping("/tempCapacity")
    public ApiResponse<String> addTempCapacity(){
        return null;
    }
}
