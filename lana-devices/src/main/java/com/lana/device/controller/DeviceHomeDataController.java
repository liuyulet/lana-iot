package com.lana.device.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.device.entity.vo.result.DevicesPinResult;
import com.lana.device.service.DeviceControlDataService;
import com.lana.device.service.DeviceHomeDataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liuyulet
 * @create 2025/6/23 15:54
 */
@RestController
@RequestMapping("/devices/deviceHomeDataController")
@Tag(name = "设备列在线情况查询")
@ApiSupport(author = "liuyulet")
public class DeviceHomeDataController {

    @Resource
    private DeviceHomeDataService deviceHomeDataService;

    @GetMapping("/devicesPin")
    public LanaResult<List<DevicesPinResult>> devicesPin() {
        return LanaResult.ok(deviceHomeDataService.getDeviceStatusList());
    }


}
