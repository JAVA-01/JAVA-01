## 系统属性
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210108101657221.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
-D 也可以这样设置获取，也相当于隐式的传参，我们可以在一个地方设置一个系统属性，然后在一个地方获取。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210108101715361.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210108101740662.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
## 运行模式
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210108102132650.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
## 堆内存 **
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210108102221746.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
DirectMemory 是堆外内存，不受GC管理，非堆是jvm使用的一些元数据
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210108102429462.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
xmx是最大堆内存，xms是初始堆内存，两这一般设置相等的数值，否则刚开始的时候JVM就可以会有FullGC（刚开始的时候，堆设置过小），扩容的时候可能会造成性能抖动。
Xmx一般设置为集群内存的60%~80%比较好

![在这里插入图片描述](https://img-blog.csdnimg.cn/20210108112635607.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)

