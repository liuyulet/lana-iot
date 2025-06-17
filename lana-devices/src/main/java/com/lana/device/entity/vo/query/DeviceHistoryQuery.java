package com.lana.device.entity.vo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lana.base.syshandle.page.LanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/29 18:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "设备历史数据查询实体")
public class DeviceHistoryQuery extends LanaPageParams {

    @NotNull(message = "设备ID不能为空")
    @Schema(description = "设备ID")
    private Long deviceId;


    @NotNull(message = "开始时间不能为空")
    @Schema(description = "开始时间")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;

    @NotNull(message = "开始时间不能为空")
    @Schema(description = "结束时间")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;


}
