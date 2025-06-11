package com.lana.abutment.mqtthandle.servecontrol;

import com.lana.base.syshandle.exception.LanaException;
import com.lana.base.syshandle.page.LanaPage;
import jakarta.annotation.Resource;
import org.dromara.mica.mqtt.codec.MqttQoS;
import org.dromara.mica.mqtt.core.server.model.ClientInfo;
import org.dromara.mica.mqtt.core.server.model.Subscribe;
import org.dromara.mica.mqtt.spring.server.MqttServerTemplate;
import org.springframework.stereotype.Component;
import org.tio.utils.page.Page;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/9/6 15:44
 */
@Component
public class MqttControlServe {
    @Resource
    private MqttServerTemplate server;


    /**
     * 关闭客户端连接
     * @param clientId
     * @return
     */
    public void close(String clientId) {
        try {
            server.close(clientId);
        }catch (Exception e){
            e.printStackTrace();
            new LanaException("关闭客户端连接失败");
        }


    }
    /**
     * 获取客户端的订阅信息
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public LanaPage<ClientInfo> getClients(Integer pageIndex, Integer pageSize) {
        Page<ClientInfo> page = server.getClients(pageIndex, pageSize);
        //将Page<ClientInfo> 转为LanaPage<ClientInfo>
        LanaPage<ClientInfo> lanaPage = new LanaPage<>(page.getList(), page.getTotalRow(), page.getPageNumber(), page.getTotalRow());
        return lanaPage;
        //return
    }
    /**
     * 获取客户端的信息
     * @param clientId
     * @return
     */
    public ClientInfo getClientInfo(String clientId) {
        return server.getClientInfo(clientId);
    }

    /**
     * 获取客户端的订阅信息
     * @param clientId
     * @return
     */
    public List<Subscribe> getSubscriptions(String clientId) {
        return server.getSubscriptions(clientId);
    }
}
