# Sharding-proxy做分库分表

库表

~~~sql
CREATE database orders_1;
use orders_1;
create table order_1  (name varchar(7),price int,  goods varchar(7)); 
create table order_2  (name varchar(7),price int,  goods varchar(7)); 
create table order_3  (name varchar(7),price int,  goods varchar(7)); 
create table order_4  (name varchar(7),price int,  goods varchar(7)); 
create table order_5  (name varchar(7),price int,  goods varchar(7)); 
create table order_6  (name varchar(7),price int,  goods varchar(7)); 
create table order_7  (name varchar(7),price int,  goods varchar(7)); 
create table order_8  (name varchar(7),price int,  goods varchar(7)); 
create table order_9  (name varchar(7),price int,  goods varchar(7)); 
create table order_10  (name varchar(7),price int,  goods varchar(7)); 
create table order_11  (name varchar(7),price int,  goods varchar(7)); 
create table order_12  (name varchar(7),price int,  goods varchar(7)); 
create table order_13  (name varchar(7),price int,  goods varchar(7)); 
create table order_14  (name varchar(7),price int,  goods varchar(7)); 
create table order_15  (name varchar(7),price int,  goods varchar(7)); 
create table order_16  (name varchar(7),price int,  goods varchar(7)); 
CREATE database orders_2;
use orders_2;
create table order_1  (name varchar(7),price int,  goods varchar(7)); 
create table order_2  (name varchar(7),price int,  goods varchar(7)); 
create table order_3  (name varchar(7),price int,  goods varchar(7)); 
create table order_4  (name varchar(7),price int,  goods varchar(7)); 
create table order_5  (name varchar(7),price int,  goods varchar(7)); 
create table order_6  (name varchar(7),price int,  goods varchar(7)); 
create table order_7  (name varchar(7),price int,  goods varchar(7)); 
create table order_8  (name varchar(7),price int,  goods varchar(7)); 
create table order_9  (name varchar(7),price int,  goods varchar(7)); 
create table order_10  (name varchar(7),price int,  goods varchar(7)); 
create table order_11  (name varchar(7),price int,  goods varchar(7)); 
create table order_12  (name varchar(7),price int,  goods varchar(7)); 
create table order_13  (name varchar(7),price int,  goods varchar(7)); 
create table order_14  (name varchar(7),price int,  goods varchar(7)); 
create table order_15  (name varchar(7),price int,  goods varchar(7)); 
create table order_16  (name varchar(7),price int,  goods varchar(7)); 
~~~



docker-compose.yml

~~~yml
version: '3.1'
services:
   sharding-proxy:
      restart: always
      image: apache/sharding-proxy
      container_name: sharding-poxy-test
      ports:
        - 3307:3307
      volumes:
        - ./conf:/conf
~~~

config-xxx.yaml

~~~yml
schemaName: sharding_db

dataSources:
  ds0: 
    url: jdbc:mysql://localhost:3306/orders_1
    username: root
    password: root
    autoCommit: true
    connectionTimeout: 30000
    idleTimeout: 60000
    maxLifetime: 1800000
    maximumPoolSize: 65
  ds1:
    url: jdbc:mysql://localhost:3306/orders_2
    username: root
    password: root
    autoCommit: true
    connectionTimeout: 30000
    idleTimeout: 60000
    maxLifetime: 1800000
    maximumPoolSize: 65

shardingRule:  
  tables:
    order: 
      actualDataNodes: orders_${0..1}.order_{0..16}
      databaseStrategy:
        inline:
          shardingColumn: price
          algorithmExpression: orders_${price % 2}
      tableStrategy: 
        inline:
          shardingColumn: price
          algorithmExpression: order_${price % 16}
      keyGeneratorColumnName: price
  bindingTables:
    - order
  defaultTableStrategy:
    none:
  defaultKeyGeneratorClassName: io.shardingsphere.core.keygen.DefaultKeyGenerator
~~~

server.yml

~~~yml
authentication:
  users:
    root:
      password: root
~~~

