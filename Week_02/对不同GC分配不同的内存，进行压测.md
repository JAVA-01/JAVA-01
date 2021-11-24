# GC对比分析
    在图上可以看出G1GC 和串行GC的吞吐量是比较高，几乎完胜并行GC，预测可能是因为这个项目简单，并不怎么需要GC，所以小步多走比不上大步少走。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118184409953.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
    

 
# G1GC
## 堆内存1g
    堆内存1g 的时候吞吐量提升了7W  35W->42W，每秒的并发量从5.8k->6.9k并发能力提升
  
![在这里插入图片描述](https://img-blog.csdnimg.cn/2021011816093689.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118161001752.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)

   ## 堆内存2g
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118160633418.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118160705541.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118160717889.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)


##  堆内存3g
![在这里插入图片描述](https://img-blog.csdnimg.cn/2021011816191819.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118161930319.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)


## 堆内存4g
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118161708840.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118161719690.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
# ParallelGC
 ## 1G
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118164724589.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118164851228.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
## 2G
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118165114514.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118165123868.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
## 3G
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118165419615.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118165459488.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
## 4G
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118170107207.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118170123750.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
# SerialGC
## 1G
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118184205105.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118184222787.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)

## 2G
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118181431180.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118181539704.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
## 3G
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118183936283.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118184009843.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)


## 4G
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118170445898.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210118170457414.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3RoZWZseWJyaWQ=,size_16,color_FFFFFF,t_70)

