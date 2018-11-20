# WebSocketDemo #

#### 项目介绍
在Spring Boot中使用`WebSocket`的Demo项目，这个示例包括简单模式、STOMP模式消息、处理对方不在线情况、分布式WebSocket等。

#### 技术依赖 ####

- `Spring Boot`：项目基础架构
- `thymeleaf`：用于构建测试页面模板
- `MyBatis`：用于访问`MySQL`数据库，实现用户登录功能

#### 环境依赖 ####

- `JDK8+`
- `MySQL5.7+`
- `Redis集群`

#### 三个子项目说明 ####

- `sample-websocket`：最基础的demo项目，包含：使用Java提供的@ServerEndpoint注解实现`WebSocket`、使用Spring提供的低层级WebSocket API实现`WebSocket`
- `stomp-websocket`：这个demo项目包含：**使用STOMP消息实现`WebSocket`**、**向指定用户发送WebSocket消息并处理对方不在线的情况**等代码。
- `mq-websocket`：这个demo项目包含：**使用消息队列实现分布式`WebSocket`**等代码

------

有关这个项目代码的详细开发思路，可以参考我的这三篇文章：

1. [Spring Boot中使用WebSocket总结（一）：几种实现方式详解](https://www.zifangsky.cn/1355.html)
2. [Spring Boot中使用WebSocket总结（二）：向指定用户发送WebSocket消息并处理对方不在线的情况](https://www.zifangsky.cn/1359.html)
3. [Spring Boot中使用WebSocket总结（三）：使用消息队列实现分布式WebSocket](https://www.zifangsky.cn/1364.html)