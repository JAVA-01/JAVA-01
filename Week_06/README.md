###2月10日课程：
1. （选做）尝试使用 Lambda/Stream/Guava 优化之前作业的代码。
2. （选做）尝试使用 Lambda/Stream/Guava 优化工作中编码的代码。
3. （选做）根据课上提供的材料，系统性学习一遍设计模式，并在工作学习中思考如何用设计模式解决问题。
4. （选做）根据课上提供的材料，深入了解 Google 和 Alibaba 编码规范，并根据这些规范，检查自己写代码是否符合规范，有什么可以改进的。
###2月22日课程：
1. （选做）基于课程中的设计原则和最佳实践，分析是否可以将自己负责的业务系统进行数据库设计或是数据库服务器方面的优化
2. （必做）基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交DDL的SQL文件到Github（后面2周的作业依然要是用到这个表结构）。
CREATE  DATABASE shop;
CREATE TABLE users (id INT PRIMARY KEY, username VARCHAR(20), PASSWORD VARCHAR(20), iphoneCall VARCHAR(20)) ;
CREATE TABLE goods (id INT PRIMARY KEY, price VARCHAR(20), TIME VARCHAR(20)) ;
CREATE TABLE orders(id INT PRIMARY KEY, price VARCHAR(20), TIME VARCHAR(20),goodsid INT);
CREATE TABLE goodsid (id1 INT,id2 INT, id3 INT );
3. （选做）尽可能多的从“常见关系数据库”中列的清单，安装运行，并使用上一题的SQL测试简单的增删改查。
4. （选做）基于上一题，尝试对各个数据库测试100万订单数据的增删改查性能。
5. （选做）尝试对MySQL不同引擎下测试100万订单数据的增删改查性能。
6. （选做）模拟1000万订单数据，测试不同方式下导入导出（数据备份还原）MySQL的速度，包括jdbc程序处理和命令行处理。思考和实践，如何提升处理效率。
7. （选做）对MySQL配置不同的数据库连接池（DBCP、C3P0、Druid、Hikari），测试增删改查100万次，对比性能，生成报告。