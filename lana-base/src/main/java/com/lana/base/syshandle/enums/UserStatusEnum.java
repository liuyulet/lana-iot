package com.lana.base.syshandle.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    /**
     * 停用
     */
    DISABLE(0),
    /**
     * 正常
     */
    ENABLED(1);

    private final int value;

}
