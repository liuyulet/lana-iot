package com.lana.device.service.impl;

import com.lana.device.dao.DeviceHomeDataDao;
import com.lana.device.entity.DevicesPinEntity;
import com.lana.device.entity.LogCountEntity;
import com.lana.device.entity.vo.result.DevicesPinResult;
import com.lana.device.entity.vo.result.LogCountResult;
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
        // 这个检索，没有数据权限，展示的是平台所有的。
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

    @Override
    public LogCountResult getLogData() {
        // 这个检索，没有数据权限，展示的是平台所有的。
/*        long systemLog = deviceHomeDataDao.systemLog();
        long deviceActionLog = deviceHomeDataDao.systemLog();
        long deviceAlarmsLog = deviceHomeDataDao.deviceAlarmsLog();
        long deviceAlarm = deviceHomeDataDao.deviceAlarm();
        long scenarioModeLog = deviceHomeDataDao.scenarioModeLog();
        LogCountResult result = new LogCountResult();
        result.setSystemLog(systemLog);
        result.setDeviceActionLog(deviceActionLog);
        result.setDeviceAlarmsLog(deviceAlarmsLog);
        result.setDeviceAlarm(deviceAlarm);
        result.setScenarioModeLog(scenarioModeLog);*/

        List<LogCountEntity> logCounts = deviceHomeDataDao.getLogCounts();

        log.info("logCounts:{}",logCounts);
        LogCountResult result = new LogCountResult();

        for (LogCountEntity item : logCounts) {
            switch (item.getLogType()) {
                case "systemLog" -> result.setSystemLog(item.getCountValue());
                case "deviceActionLog" -> result.setDeviceActionLog(item.getCountValue());
                case "deviceAlarmsLog" -> result.setDeviceAlarmsLog(item.getCountValue());
                case "deviceAlarm" -> result.setDeviceAlarm(item.getCountValue());
                case "scenarioModeLog" -> result.setScenarioModeLog(item.getCountValue());
            }
        }
        return result;
    }
}
