package com.lana.device.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.lana.device.entity.DevicesPinEntity;
import com.lana.device.entity.LogCountEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/10/29 18:34
 */
@Mapper
public interface DeviceHomeDataDao {

    DevicesPinEntity getDeviceStatusList();


    @DS("tdengine")
    long systemLog();

    @DS("tdengine")
    long deviceActionLog();

    @DS("tdengine")
    long deviceAlarmsLog();

    @DS("tdengine")
    long deviceAlarm();

    @DS("tdengine")
    long scenarioModeLog();

    @DS("tdengine")
    List<LogCountEntity> getLogCounts();
}
