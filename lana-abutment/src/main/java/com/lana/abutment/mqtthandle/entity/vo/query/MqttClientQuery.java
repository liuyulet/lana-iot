package com.lana.abutment.mqtthandle.entity.vo.query;

import com.lana.base.syshandle.page.LanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户查询
 *
 * @auther liuyulet
 * @date 2025/6/11 19:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "客户端列表查询")
public class MqttClientQuery extends LanaPageParams {

}
