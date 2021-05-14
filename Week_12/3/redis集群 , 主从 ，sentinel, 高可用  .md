# redis集群 , 主从 ，sentinel, 高可用

### 主从

~~~yml
version: '3.1'
services:
   redise1: 
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 6379:6379
      volumes:
         - ./conf/redis.conf:/usr/local/redis/redis.conf
      command: ["redis-server","/usr/local/redis/redis.conf"]
~~~

~~~yml
version: '3.1'
services:
   redise1: 
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 6380:6380
      volumes:
         - ./conf/redis.conf:/usr/local/redis/redis.conf
      command: ["redis-server","/usr/local/redis/redis.conf"]      
~~~

~~~yml
replicaof 127.0.0.1 6379
~~~

排坑 不同dockers的内网地址不同，具体可用dockers inspect docker_id查看 

改为

~~~yml
eplicaof 47.98.192.167 6379
~~~

另外主节点使用密码的话从节点也许配置masterauth password

~~~yml
port 6380
masterauth 1234
replicaof 47.98.192.167 6379
~~~

### 哨兵

排坑，哨兵和从库对主库的密码配置不一样

~~~yml
sentinel auth-pass master01 1234
~~~

哨兵的配置

~~~
# 指定master的节点ip和端口号（主）
sentinel monitor master（别名） 47.98.192.167 6379 2（需要从节点确认的个数）
# 哨兵每个多久监听一次redis架构
sentinel down-after-milliseconds mymaster 10000
port 6381
~~~

哨兵的docker-compose文件

~~~yml
version: '3.1'
services:
   redise1: 
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis6381
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 6381:6381
      volumes:
         - ./conf/redis81.conf:/usr/local/redis/sentinel.conf
      command: ["redis-sentinel","/usr/local/redis/sentinel.conf"]
   redis2: 
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis6382
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 6382:6382
      volumes:
         - ./conf/redis82.conf:/usr/local/redis/sentinel.conf
      command: ["redis-sentinel","/usr/local/redis/sentinel.conf"]
~~~



### 集群

集群总线端口数必须是redis端口数+10000

~~~yml
version: '3.1'
services:
   redise1: 
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis1
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 7010:7010
         - 17010:17010
      volumes:
         - ./conf/redis1.conf:/usr/local/redis/redis.conf
      command: ["redis-server","/usr/local/redis/redis.conf"]
   redis2: 
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis2
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 7020:7020
         - 17020:17020
      volumes:
         - ./conf/redis2.conf:/usr/local/redis/redis.conf
      command: ["redis-server","/usr/local/redis/redis.conf"]   
   redis3:
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis3
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 7030:7030
         - 17030:17030
      volumes:
         - ./conf/redis3.conf:/usr/local/redis/redis.conf
      command: ["redis-server","/usr/local/redis/redis.conf"] 
~~~

##### 集群配置 

~~~yml
#redis.conf
# 指定redis的端口号
port 7010
#开启redis集群
cluster-enabled yes
# 集群信息的文件
cluster-config-file nodes-7010.conf
# 集群的对外ip地址
cluster-announce-ip 47.98.192.167
# 集群的对外端口号
cluster-announce-port 7010
#集群的总线端口号
cluster-announce-bus-port 17010
~~~

~~~yml
#redis.conf
# 指定redis的端口号
port 7020
#开启redis集群
cluster-enabled yes
# 集群信息的文件
cluster-config-file nodes-7020.conf
# 集群的对外ip地址
cluster-announce-ip 47.98.192.167
# 集群的对外端口号
cluster-announce-port 7020
#集群的总线端口号
cluster-announce-bus-port 17020
~~~

~~~yml
#redis.conf
# 指定redis的端口号
port 7030
#开启redis集群
cluster-enabled yes
# 集群信息的文件
cluster-config-file nodes-7030.conf
# 集群的对外ip地址
cluster-announce-ip 47.98.192.167
# 集群的对外端口号
cluster-announce-port 7030
#集群的总线端口号
cluster-announce-bus-port 17030
~~~



### 为集群配置从库和sentinel 

#### 从库

~~~yml
version: '3.1'
services:
   redise1: 
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis11
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 7011:7011
      volumes:
         - ./conf/redis1.conf:/usr/local/redis/redis.conf
      command: ["redis-server","/usr/local/redis/redis.conf"]
   redise2: 
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis21
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 7021:7021
      volumes:
         - ./conf/redis2.conf:/usr/local/redis/redis.conf
      command: ["redis-server","/usr/local/redis/redis.conf"]  
   redise3: 
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis31
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 7031:7031
      volumes:
         - ./conf/redis3.conf:/usr/local/redis/redis.conf
      command: ["redis-server","/usr/local/redis/redis.conf"]      
~~~

~~~yml
redis-cli --cluster create 47.98.192.167:7010 47.98.192.167:7020 47.98.192.167:7030 
~~~



##### 从库的配置

~~~
port 7011
replicaof 47.98.192.167 7010
~~~

~~~yml
port 7021
replicaof 47.98.192.167 7020
~~~

~~~yml
port 7031
replicaof 47.98.192.167 7030
~~~

### sentinel

~~~yml
version: '3.1'
services:
   redise1: 
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis12
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 7012:7012
      volumes:
         - ./conf/redis12.conf:/usr/local/redis/sentinel.conf
      command: ["redis-sentinel","/usr/local/redis/sentinel.conf"]
   redise2: 
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis13
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 7013:7013
      volumes:
         - ./conf/redis13.conf:/usr/local/redis/sentinel.conf
      command: ["redis-sentinel","/usr/local/redis/sentinel.conf"]
   redise3: 
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis22
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 7022:7022
      volumes:
         - ./conf/redis22.conf:/usr/local/redis/sentinel.conf
      command: ["redis-sentinel","/usr/local/redis/sentinel.conf"]
   redise4: 
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis23
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 7023:7023
      volumes:
         - ./conf/redis23.conf:/usr/local/redis/sentinel.conf
      command: ["redis-sentinel","/usr/local/redis/sentinel.conf"]     
   redise5: 
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis32
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 7032:7032
      volumes:
         - ./conf/redis32.conf:/usr/local/redis/sentinel.conf
      command: ["redis-sentinel","/usr/local/redis/sentinel.conf"]
   redise6: 
      image: daocloud.io/library/redis:5.0.7
      restart: always
      container_name: redis33
      environment:
         - TZ=Asia/Shanghai
      ports:
         - 7033:7033
      volumes:
         - ./conf/redis33.conf:/usr/local/redis/sentinel.conf
      command: ["redis-sentinel","/usr/local/redis/sentinel.conf"]      
~~~

#### 哨兵的设置

~~~yml
# 指定master的节点ip和端口号（主）
sentinel monitor master 47.98.192.167 7010 2
# 哨兵每个多久监听一次redis架构
sentinel down-after-milliseconds master 10000
port 7012
~~~

~~~yml
# 指定master的节点ip和端口号（主）
sentinel monitor master 47.98.192.167 7010 2
# 哨兵每个多久监听一次redis架构
sentinel down-after-milliseconds master 10000
port 7013
~~~

#### 

~~~yml
# 指定master的节点ip和端口号（主）
sentinel monitor master 47.98.192.167 7020 2
# 哨兵每个多久监听一次redis架构
sentinel down-after-milliseconds master 10000
port 7022
~~~

~~~yml
# 指定master的节点ip和端口号（主）
sentinel monitor master 47.98.192.167 7020 2
# 哨兵每个多久监听一次redis架构
sentinel down-after-milliseconds master 10000
port 7023
~~~

#### 

~~~yml
# 指定master的节点ip和端口号（主）
sentinel monitor master 47.98.192.167 7030 2
# 哨兵每个多久监听一次redis架构
sentinel down-after-milliseconds master 10000
port 7032
~~~

~~~yml
# 指定master的节点ip和端口号（主）
sentinel monitor master 47.98.192.167 7030 2
# 哨兵每个多久监听一次redis架构
sentinel down-after-milliseconds master 10000
port 7033
~~~

