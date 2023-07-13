# lana-iot
```   

├── lana-access
        └── lana-access-gateway                      --- 网关模块（整合协议适配器，并插件化协议解析，数据从原始协议转换为统一的数据格式）
        └── lana-access-api                          --- 统一接口
        └── lana-access-outh                         --- 业务认证（提供基础的认证功能，对外部系统进行认证和安全保护）
├── lana-communication
        └── lana-communication-mq                    --- AMQP消息
        └── lana-communication-coap                  --- coap消息
        └── lana-communication-mqtt                  --- mqtt消息
├── lana-hardware
        └── lana-hardware-tslmodel                   --- 物模型
        └── lana-hardware-rule                       --- 规则引擎(easy-rules) 
        └── lana-hardware-camera                     --- 流媒体 
        └── lana-hardware-collection                 --- 设备数采
        └── lana-hardware-mqtt                       --- mqtt消息队列
        └── lana-hardware-coap                       --- coap消息
        └── lana-hardware-simulator                  --- 设备模拟器   
├── lana-cloud
        └── lana-cloud-monitor                       --- 服务监控 
        └── lana-cloud-seata                         --- 分布式事务  
        └── lana-cloud-sentinel                      --- 哨兵(限流&熔断降级)  
        └── lana-cloud-xxl-job                       --- 任务调度  
├── lana-common
        └── lana-common-log                          --- 日志
        └── lana-common-encrypt                      --- 加密  
        └── lana-common-sms                          --- 短信
        └── lana-common-other                        --- 第三方平台  
        └── lana-common-swagger                      --- 接口文档 
        └── lana-common-openfeign                    --- 服务调用
        └── lana-common-netty                        --- 网络通讯
        └── lana-common-hbase                        --- 大数据  
        └── lana-common-clickhouse                   --- 列式存储  
        └── lana-common-tdengine                     --- 时序库  
        └── lana-common-redis                        --- 内存存储
        └── lana-common-mongodb                      --- 文档存储
        └── lana-common-minio                        --- 对象存储
        └── lana-common-es                           --- 搜索
├── lana-serves
        └── lana-serves-admin                        --- 基础服务
        
        
```