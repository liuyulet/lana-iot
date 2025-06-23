package com.lana.device.service.impl;

import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.RedisCacheOps;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.base.syshandle.entity.DeviceControl;
import com.lana.base.syshandle.exception.LanaException;
import com.lana.device.convert.DeviceControlDataConvert;
import com.lana.device.dao.DeviceControlDataDao;
import com.lana.device.dao.DeviceHomeDataDao;
import com.lana.device.entity.DeviceControlDataEntity;
import com.lana.device.entity.DevicesPinEntity;
import com.lana.device.entity.vo.query.DeviceControlDataQuery;
import com.lana.device.entity.vo.result.DeviceControlDataResult;
import com.lana.device.entity.vo.result.DevicesPinResult;
import com.lana.device.entity.vo.save.DeviceControlDataSave;
import com.lana.device.entity.vo.update.DeviceControlDataUpdate;
import com.lana.device.service.DeviceControlDataService;
import com.lana.device.service.DeviceHomeDataService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/11/19 16:58
 */
@Slf4j
@Service
public class DeviceHomeDataServiceImpl implements DeviceHomeDataService {

    @Resource
    private DeviceHomeDataDao deviceHomeDataDao;


    @Override
    public List<DevicesPinResult> getDeviceStatusList() {
        DevicesPinEntity devicesPinEntity = deviceHomeDataDao.getDeviceStatusList();
        if (devicesPinEntity == null) {
            log.warn("No device status data found in database.");
            return Collections.emptyList();
        }

        List<DevicesPinResult> results = new ArrayList<>();
        results.add(new DevicesPinResult("在线设备",devicesPinEntity.getOnStatus()));
        results.add(new DevicesPinResult("离线设备",devicesPinEntity.getOffStatus()));
        return results;
    }
}
