# lana 轻量级物联网设备管理平台

## 1、项目文档：

使用该项目：  [项目文档](https://liuyulet.github.io/)

## 2、项目背景
~~~
项目背景：

自从接触了很多设备之后，一直想搞一个平台，能方便管理设备、方便进行设备接入、方便进行设备控制、方便进行设备数据展示。

并且云端仅仅管理设备，边缘负责接入设备。两者分开，各司其职，

所以，筹备了很久，lana总算有了一个雏形。

吃水不忘挖井人，因为该项目引用了一些开源的项目（具体请看下方技术栈），所以也为开源事业做一份贡献。

多有不足，请多指正！

特点：
● 接入协议简单...
● 专注于云平台的设备管理...
● 通俗易懂的代码与使用习惯，方便后期修改（实际上是自己懒，尽量简单实现）...
● 为后期diy设备做个准备...

~~~
## 3、项目所使用的技术栈

后端程序学习并参考了： [ruoyi](https://gitee.com/y_project/RuoYi)

前端项目基于scui修改：[scui](https://gitee.com/lolicode/scui)


| 序号 | 项目架构        | 架构描述                                                                                                                                                                                                                                                           | 
|----|-------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1  | 后端技术栈          | [springboot3.3](https://github.com/spring-projects/spring-boot) + [springSecurity6.2](https://github.com/spring-projects/spring-security) + [Mybatis-Plus3.5.5](https://github.com/baomidou/mybatis-plus) + [jwt0.11](https://github.com/jwtk/jjwt) + [AviatorScript5.4.3](https://github.com/killme2008/aviatorscript) + [mica-mqtt2.4.4](https://gitee.com/dromara/mica-mqtt) + [caffeine3.1.8](https://github.com/ben-manes/caffeine) + [redisson3.21.3](https://github.com/redisson/redisson)  + [hutool5.8.21](https://github.com/chinabugotech/hutool) + [captcha1.6.2](https://github.com/ele-admin/EasyCaptcha) + [knife4j4.3](https://github.com/xiaoymin/knife4j) + [mapstruct1.5.5](https://github.com/mapstruct/mapstruct) + [dynamic4.2](https://github.com/baomidou/dynamic-datasource) ... |
| 2  | 必要的服务          |  mysql8 + TDengine3.2 + redis7.0 + minio8.5.1 + jdk17 + node.js |
| 3  | 前端技术栈          | Vue3 + Element-Plus（由[scui](https://gitee.com/lolicode/scui)项目进行修改开发）                                                                                                                                                                                       |

### 在此感谢各位大佬们、前辈们的开源！为我点燃了前进的航路！


## 4、项目图片展示
![shouye.PNG](doc%2Fimg%2Fshouye.PNG)
![shouye2.PNG](doc%2Fimg%2Fshouye2.PNG)
![people.PNG](doc%2Fimg%2Fpeople.PNG)
![log.PNG](doc%2Fimg%2Flog.PNG)
![user.PNG](doc%2Fimg%2Fuser.PNG)
![role.PNG](doc%2Fimg%2Frole.PNG)
![post.PNG](doc%2Fimg%2Fpost.PNG)
![dict.PNG](doc%2Fimg%2Fdict.PNG)
![menus.PNG](doc%2Fimg%2Fmenus.PNG)

![product.png](doc%2Fimg%2Fproduct.png)
![productmode.png](doc%2Fimg%2Fproductmode.png)
![device.png](doc%2Fimg%2Fdevice.png)
![group.png](doc%2Fimg%2Fgroup.png)
![groupbind.png](doc%2Fimg%2Fgroupbind.png)
![protocols.jpg](doc%2Fimg%2Fprotocols.jpg)
![protocols_detil.png](doc%2Fimg%2Fprotocols_detil.png)
![rules.PNG](doc%2Fimg%2Frules.PNG)
![starts.png](doc%2Fimg%2Fstarts.png)
![condition.png](doc%2Fimg%2Fcondition.png)
![control.png](doc%2Fimg%2Fcontrol.png)
![circulation.png](doc%2Fimg%2Fcirculation.png)
![scenes.png](doc%2Fimg%2Fscenes.png)
