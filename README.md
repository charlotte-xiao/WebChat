# WebChat
>介绍

Socket Chat是在初学Java的时候写的一个基于socket的网络聊天室。基于C/S(Client/Server)的架构。

> 功能

- 客户端之间,客户端与服务端之间通信
- 客户端的账号密码登录
- 机器人聊天功能

> 配置环境

- Java 8.0 以上
- 管理工具 Maven
- 数据库 Mysql
- 开发环境 IDEA

>  使用

- 配置数据库:配置文件config.properties;创建数据库DataBase/webchat.sql
- 配置腾讯机器人:`src/main/java/Server/Utils/TencentRobot.java`类中配置为自己申请的app_id和app_key;
  申请地址:https://ai.qq.com/console/
- 启动类:`src/main/java/main/*.java`

> 更多

- 界面是用JFormDesigner(IDEA上的一个插件)做的,本质和Swing的一样,其实所有`*.jfb`文件,可以删除。