学习笔记
##JVM 基础知识：不积跬步，无以至千里
两个功能相同的代码可以通过字节码的过程对比可以看到是否一样活着哪个更复杂
##Java 字节码技术： 不积细流，无以成江河
##JVM 类加载器： 山不辞土，故能成其高
JDK 9以后直接使用 classLoader
#JVM 内存模型
规范了线程间的交互操作
对象，静态类，数组要放到堆
#JVM 配置
XMX 不要超过系统的 60~80%

#JVM 命令行工具
## 编译有关的工具
## 辅助性工具（探查，分析)
jsp 获取 jpid
经常使用
jstat -gc jpid time count
jstat -gcutil jpid time count
jstack
jmap
##打印堆栈的信息
kill -3 

## 查看 jcmd 命令
###jcmd 7901 help

##Jrunscript用法
### 当 curl 使用
### 运行js

## 图形工具
jconsole
jvisualvm
jmc - oracle

wrk 是压测工具

#GC背景
内存不释放会满，其他应用无法使用
内存申请和释放的管理器
### 标记清除算法 MARK and SWEEP

### 复制算法
### 整理算法

### STW stop the world
jvm 会 停止 5 毫秒的时间
### HEAP 内存区
年轻代的较老数据放到老年代
年轻代又分新生代和S0, S1, 新生代和 S0, S1 比为 8：1：1
#### 年轻代 minor GC 比较快
#### 老年代使用 major GC 比较慢
根据区域使用不同 GC 策略
### 对象池之间转移
####新生代使用复制方式 
####老年代使用移动方式

### GC ROOT 的对象
#### 正在执行的方法里的局部变量和参数
####活动参数
####所有静态字段
####JNI 引用

### 串行GC
#### 都会触发 STW 事件
#### 年轻代 标记-复制策略 mark-copy
#### 老年代 标记-清楚-整理 mark-sweep-compact
#### 单线程执行 GC

### 并行GC
#### 都会触发 STW 事件
#### 年轻代 标记-复制策略 mark-copy
#### 老年代 标记-清楚-整理 mark-sweep-compact
#### 多个线程并行执行
#### JDK 8 

### CMS GC
#### 都会触发 STW 事件
#### 年轻代 并行标记-复制策略 mark-copy
#### 老年代 并发标记-清楚-整理 mark-sweep-compact
#### 业务线程与 GC 线程并发执行
#### 多个线程并行执行

### CMS GC 6个阶段
#### 初始标记
#### Concurrent Mark (并发标记)
#### Concurrent Preclean (并发预清理) 
#### Final Remakr （最终标记)
#### Concurrent Sweep(并发清除)
#### Concurrent Rest（并发重置)


### G1 GC
#### CMS 的升级版本
#### 分城 2048 个区域
#### 每次GC 年轻代和小部分老年代

### 如何选择 GC 算法
#### 吞吐优先 - 并行GC, JDK 6，7，8 默认
#### 低延迟 - CMS GC
#### 堆比较大 - G1 
