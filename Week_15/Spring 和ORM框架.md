# Spring 和ORM框架

* Spring 开发框架
  * bean 管理
  * bean注入
  * aop 切面控制
    * jdk 自带的
    * 字节码增强

* spring- boot

  * 自动装配

    * 根据配置的依赖和jar包猜测可能会用到的bean，并做注入

  * starter

    * 支持自定义starter，将项目代码打包复用

  * 约定大于配置

    * 简化spring的复杂配置，选择一套最广泛应用的配置作为默认的配置即约定。

      ![image-20210514174751698](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20210514174751698.png)

* ORM框架
  * java定义了一套自定的SPI（services provider Interface），是提供给第三方厂商扩展和实现的
  * 其中mysql数据会实现一个mysql的驱动，通过DriverManager获取连接
  * Connection conn = DriverManager.getConnection(url, username, pwd);
  * 原始使用的时候，需要调用数据库驱动加载数据库相关API，获取数据库连接池，获取连接，也可以不用连接池，直接使用原生的JDBC进行操作
  * 这里掉用出来的是一条条关系型数据库中的记录。需要和java的pojo做映射
  * 这就需要ORM框架做实现
    * pojo和item的映射
    * 事务
    * 日志



