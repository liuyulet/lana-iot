package com.lana.device.entity;

import lombok.Data;

/**
 * @author liuyulet
 * @create 2025/6/25 8:52
 */
@Data
public class LogCountEntity {
    private String logType;     // 日志类型（systemLog、deviceActionLog 等）
    private Long countValue;    // 对应日志类型的数量
}
