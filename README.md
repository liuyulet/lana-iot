# lana-iot

核心理念
```
原则一、代码简单易懂，不要炫技
原则二、性能为上，不要炫技
原则三、进度为上，不要炫技
为什么不要炫技呢，因为我不会，你这样会让我很难受的.....
```

项目结构：
```   
├── lana-access
|       └── lana-gateway                      --- 网关模块
|       └── lana-api                          --- 统一接口
|       └── lana-outh                         --- 认证
├── lana-cloud
|       └── lana-monitor                      --- 服务监控 
|       └── lana-seata                        --- 分布式事务  
|       └── lana-sentinel                     --- 哨兵(限流&熔断降级)  
|       └── lana-xxl-job                      --- 任务调度  
├── lana-common
|       └── lana-log                          --- 日志
|       └── lana-encrypt                      --- 加密  
|       └── lana-sms                          --- 短信
|       └── lana-other                        --- 第三方平台  
|       └── lana-swagger                      --- 接口文档 
|       └── lana-openfeign                    --- 服务调用
|       └── lana-clickhouse                   --- 列式存储  
|       └── lana-tdengine                     --- 时序库  
|       └── lana-redis                        --- 内存存储
|       └── lana-mongodb                      --- 文档存储
|       └── lana-minio                        --- 对象存储
|       └── lana-es                           --- 搜索
├── lana-communication
|       └── lana-mq                           --- AMQP消息
|       └── lana-kafka                        --- kafka消息
|       └── lana-coap                         --- coap消息
|       └── lana-mqtt                         --- mqtt消息
|       └── lana-netty                        --- 网络通讯
├── lana-hardware
|       └── lana-tslmodel                     --- 物模型
|       └── lana-rule                         --- 规则引擎(easy-rules) 
|       └── lana-camera                       --- 流媒体 
|       └── lana-collection                   --- 设备数采
|       └── lana-simulator                    --- 设备模拟器   
├── lana-serves
        └── lana-admin                        --- 基础服务     
```

前期规划：
```
1、微服务权限体系架构与基础服务
2、统一网关
3、统一接口
4、mqtt设备接入
5、时序库整合
6、redis数据缓存
7、minio文件存储
8、物模型
9、规则引擎
10、数采
```