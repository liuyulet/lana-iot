package com.lana.device.entity.vo.result;

import lombok.Data;

/**
 * @author liuyulet
 * @create 2025/6/23 16:53
 */
@Data
public class LogCountResult {

    //系统操作
    private long systemLog;
    //设备操作
    private long deviceActionLog;
    //设备告警
    private long deviceAlarmsLog;
    //解除告警
    private long deviceAlarm;
    //情景模式
    private long scenarioModeLog;

}
