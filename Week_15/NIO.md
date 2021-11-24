# NIO

* 堵塞与非堵塞
  * 堵塞： 线程必须等待此命令执行完毕之后才可以执行之后的任务
  * 非堵塞：线程不用等待当前的指令执行完毕，可以先执行之后的指令
* 同步和异步
  * 同步：命令依次执行，严格有序
  * 异步：并行执行，指令可以在其他指令执行的同时执行

## NIO和BIO的区别

* BIO是面向流的，NIO是面向块的，面向缓存的
* NIO通过channel和selector实现异步的数据传输

![image-20210514154659621](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20210514154659621.png)