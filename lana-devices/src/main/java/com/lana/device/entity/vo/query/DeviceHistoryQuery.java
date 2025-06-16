package com.lana.device.entity.vo.query;

import com.alibaba.fastjson.annotation.JSONField;
import com.lana.base.syshandle.page.LanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "设备历史数据查询实体")
public class DeviceHistoryQuery extends LanaPageParams {


    @Schema(description = "设备ID")
    private Long deviceId;

    @Schema(description = "开始时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDate startTime;

    @Schema(description = "结束时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDate endTime;


}
