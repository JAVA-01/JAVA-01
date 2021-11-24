# RPC和微服务

### RPC

* RPC远程过程调用
  * 基于zk
    * 服务提供者将服务注册到zk
    * 服务消费者从zk中调用服务
  * 基于HTTP/okkttp/netty等去post调用接口

* 基本原理
* 消费端
  * aop/字节码增强（cglib动态代理）/或者JDK动态代理。获取代理类。使用代理类去调用方法。
  * 代理类：
    * 封装请求
    * 序列化
    * post/调用服务
    * 获取返回值
    * JSON.praseObject获取响应

* 服务端
  * 从spring托管的bean中找到需要额bean
  * 通过反射调用方法序列
  * 结果序列化，封装到响应中
  * 返回

服务端创建core的时候需要实现过滤器，路由，负载均衡策略，bean的查找工具类

![image-20210514211941291](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20210514211941291.png)

### 微服务

* 功能服务
* 增加吞吐量
* 高可用
* 可以使用集群，路由等

![image-20210514212536074](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20210514212536074.png)