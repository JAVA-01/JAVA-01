## 并行GCVS G1GC
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117153547636.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
这里设置最大堆内存均为1g时，并行GC和默认参数下吞吐量明显不同，并行GC的吞吐量少
查看java版本后发现我这里安装的是jdk'9'，而jdk9默认使用G1GC，JDK8默认并行GC。


### 256m
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117154954621.png)

其中有一个 compose oops mode，JVM压缩指针  Oracle JDK从6 update 23开始在64位系统上会默认开启压缩指针。
[JVM之压缩指针——Compressed oops](https://blog.csdn.net/liuxiao723846/article/details/91981757)
zhel ![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117155249642.png)
这里是第一次GC  
六行分别是  GC的触发原因对那个区进的GC  内存分配失败   年轻代的内存变化 （G
C） 老年代的内存变化（提升）  元数据区（不变）  总的GC信息（触发原因，总内存变化  总用时 ）    
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117155622729.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
这里还都是youngGC  平均用时18ms/每次
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117160233219.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
这里就都是发生的fullGC了，每次GC后young区基本清零，old区略有增加，平均66ms/每次
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117160749659.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
这里有一个问题分配内存为256m按理说会崩掉，结果打印详细参数时不会崩掉且，设置的运行时间是2s，结果这里到了2.5s以上，而且仔细看2.14秒的内存还没溢出（这里写在代码里的循环时间控制并不是很精确，可能两秒前进入了GC，暂定掉了工作线程）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117161440613.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117162223837.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117162231308.png)
添加了PrintGCDetails后吞吐量变为原来的1/5了，难怪刚才没有报OOM异常
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117163045126.png)
另外在程序结束后还有一次总的GC，另外在2s的一个小邻域内可能会有触发一次Gc，所以时间才会超过2s
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117163831997.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117163842840.png)
仅仅只打印命令的不同，一个打印在了文件里，也会导致吞吐量的巨大差异
![在这里插入图片描述](https://img-blog.csdnimg.cn/2021011716430657.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117164320328.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
可以看出并行GC的吞吐量更好，每次GC的时间更短，并行（平均每次20ms），G1（平均每次40ms，每次stw10ms ）延迟更少

## 串行VS并行
内存1g时，串行的吞吐量居然更好
串行：时间在20~70ms
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117164914554.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
并行：时间在10~30ms
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117164939907.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
3：
并行：![!\[在这里插入图片描述\](https://img-blog.csdnimg.cn/20210117165209987.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70](https://img-blog.csdnimg.cn/20210117165336844.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)

串行：![在这里插入图片描述](https://img-blog.csdnimg.cn/20210117165350218.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
3g时，并行不论是在吞吐量还是在单词延迟上都可以完爆串行GC
## CMS 
jdk9废弃了CMS

