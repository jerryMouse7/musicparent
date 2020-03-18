package com.liu.clouddisk.service.impl;

import com.liu.clouddisk.entity.Capacity;
import com.liu.clouddisk.mapper.CapacityMapper;
import com.liu.clouddisk.service.CapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CapacityServiceImpl implements CapacityService {

    @Autowired
    private CapacityMapper capacityMapper;
    @Override
    public void initCapacity(Long userId) {
        Capacity capacity = new Capacity();
        capacity.setUserId(userId);
        capacity.setTotalCapacity(500L);
        capacity.setUsedCapacity(0L);
        capacityMapper.initCapacity(capacity);
    }

    @Override
    public void addCapacity(Long userId, Long size) {
        capacityMapper.addCapacity(userId,size);
    }

    @Override
    public Capacity getCapacityByUserId(Long userId) {
        return capacityMapper.getCapacityByUserId(userId);
    }

    @Override
    public void addTempCapacity(Long userId, Long tempCapacity, LocalDateTime expireTime) {

    }

    @Override
    public void addDate(Long userId, LocalDateTime expireTime) {

    }


}
