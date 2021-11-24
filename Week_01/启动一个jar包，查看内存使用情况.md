# 启动一个jar包
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210112135750197.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)

##  实操![在这里插入图片描述](https://img-blog.csdnimg.cn/20210112135811700.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
## 说明
```
最大堆内存 1g
-Xmx1g
初始堆内存1g
-Xms1g
关闭自适应参数
-XX：-UseAdaptiveSizePolicy
启用G1GC
-XX：+UseG1GC
设置GC理想暂停延迟为50ms
-XX：MaxGcPauseMillis=50
启动jar包
-jar  x.jar
```
## 查看进程相关信息
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210112140908248.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
## jmap实操
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210112140950403.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
### 说明
```
jmap -heap提示不能用，建议以jhsdb jmap代替
输入jhsdb jmap 后出现提示，后面跟具体的参数以执行相关操作
其中 you have to set --pid or --exe 指明是进程id还是镜像名 
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210112141744487.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
### 说明

可以看到JVM的版本号是9+181
垃圾回收机制是G1 有8个线程  16核的1/4  每核两个线程
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210112142027745.png)
然后我发现一件很奇怪的事情，就是这个NewRadio是老年代/新生代的比值对吧，然后他们比值是2，而MaxNewSize=516M，MaxHeapSize=1024m  就是说最大新生代和最大老年代的和大于最大堆的和了
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210112144947393.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
还可以注意到这MetaSpaceSize的值极大，应该意味着无上限的意思。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210112145532759.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
这里的regions是分区的数量  在这里可以看出是1M内存分为一区的。


### jstack实操
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210112150010192.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
可以看到一个线程的运行状态，runtime sleeping waiting，
还可以看到调用了哪个包下的哪个类或方法

###  jstat
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210112150725208.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210112150739288.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210112150803864.png)
### 说明
~~~
这里的C是容量，U是使用情况
S0C：第一个幸存区的大小
S1C：第二个幸存区的大小
S0U：第一个幸存区的使用大小
S1U：第二个幸存区的使用大小
EC：伊甸园区的大小
EU：伊甸园区的使用大小
OC：老年代大小
OU：老年代使用大小
MC：方法区大小
MU：方法区使用大小
CCSC:压缩类空间大小
CCSU:压缩类空间使用大小
YGC：年轻代垃圾回收次数
YGCT：年轻代垃圾回收消耗时间
FGC：老年代垃圾回收次数
FGCT：老年代垃圾回收消耗时间
GCT：垃圾回收消耗总时间
~~~
![在这里插入图片描述](https://img-blog.csdnimg.cn/2021011215133875.png)