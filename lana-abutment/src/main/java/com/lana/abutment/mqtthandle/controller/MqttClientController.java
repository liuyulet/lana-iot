package com.lana.abutment.mqtthandle.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.abutment.mqtthandle.entity.vo.push.DirectivesSend;
import com.lana.abutment.mqtthandle.entity.vo.query.MqttClientQuery;
import com.lana.abutment.mqtthandle.servecontrol.MqttControlServe;
import com.lana.abutment.mqtthandle.servepublish.MessagePublish;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.syshandle.result.LanaResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.dromara.mica.mqtt.core.server.model.ClientInfo;
import org.dromara.mica.mqtt.core.server.model.Subscribe;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/11/22 16:06
 */
@RestController
@RequestMapping("/abutment/mqttClient")
@Tag(name = "指令推送")
@ApiSupport(author = "liuyulet")
public class MqttClientController {

    @Resource
    private MqttControlServe mqttControlServe;

    @GetMapping("/close")
    @Operation(summary = "关闭客户端连接")
    @OptLog(type = OperateTypeEnum.COMMAND)
    public LanaResult pushData(@RequestParam String clientId) {
        mqttControlServe.close(clientId);
        return LanaResult.ok();
    }


    @GetMapping("/getClients")
    @Operation(summary = "查询客户端信息")
    @OptLog(type = OperateTypeEnum.COMMAND)
    public LanaResult<LanaPage<ClientInfo>> pushData(@ParameterObject @Valid MqttClientQuery query) {
        LanaPage<ClientInfo> ClientInfoPage = mqttControlServe.getClients(query.getPage(),  query.getPageSize());
        return LanaResult.ok(ClientInfoPage);
    }


    @GetMapping("/getSubscriptions")
    @Operation(summary = "获取客户端的订阅信息")
    @OptLog(type = OperateTypeEnum.COMMAND)
    public LanaResult<List<Subscribe>> getSubscriptions(@RequestParam String clientId) {
        List<Subscribe> subscribeList= mqttControlServe.getSubscriptions(clientId);
        return LanaResult.ok(subscribeList);
    }
}
