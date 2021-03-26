# 内存模型
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118200836482.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
堆内存用户共享，类通过引用会复制一个信息过去，使用完后会更新到堆内存中。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118200858883.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
程序计数器记录当前线程执行到那条语句，栈记录方法执行到哪里，程序计数器压栈，保护断点
本地变量表中有本地创建的原生数据类型变量和对象实例的引用。
![在这里插入图片描述](https://img-blog.csdnimg.cn/202101182041358.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)

# GC
    GC的选取要根据具体业务环境，做压测得出。
## SerialGC
每次进行GC的时候只有一个线程进行GC，执行GC时STW，每次执行时间长，回收的垃圾多
在大内存时，在延迟和吞吐量上性能很差，但是在内存1g时性能要比并发GC更好，且在一些不需要频繁创建对象的项目中，产型GC的吞吐量依然很不错。
## ParallelGC
JDK8默认GC
STW ，多个线程并行GC，每次执行的时间段，执行的次数多。相对于串行GC每次延迟降低，但是相对于串行GC而言，单次STW的世界仍然过程，总吞吐量占优。

## CMS
并发GC的初代版本，在JDK9被废除了。
## G1GC
并发GC ，分区算法，在大内存时性能较好。是JDK9以后的默认GC
## ZGC

