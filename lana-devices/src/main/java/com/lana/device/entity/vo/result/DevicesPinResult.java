package com.lana.device.entity.vo.result;

import lombok.Data;

/**
 * @author liuyulet
 * @create 2025/6/23 16:53
 */
@Data
public class DevicesPinResult {

    private int value;
    private String name;

    public DevicesPinResult(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
}
