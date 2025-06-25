package com.lana.device.service;

import com.lana.device.entity.vo.result.DevicesPinResult;
import com.lana.device.entity.vo.result.LogCountResult;

import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:33
 */
public interface DeviceHomeDataService {


    List<DevicesPinResult> getDeviceStatusList();

    LogCountResult getLogData();
}
