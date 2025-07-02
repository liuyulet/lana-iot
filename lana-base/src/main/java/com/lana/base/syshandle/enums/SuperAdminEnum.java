package com.lana.base.syshandle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 超级管理员枚举
 */
@Getter
@AllArgsConstructor
public enum SuperAdminEnum {
    /**
     * 是
     */
    YES(1),
    /**
     * 否
     */
    NO(0);

    private final Integer value;


}