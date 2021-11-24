# JVM

jvm学习了JVM的内存结构，jvm的GC算法，字节码和类加载器等知识。

## JVM的内存结构



![image-20210514141954497](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20210514141954497.png)

* 元数据区，原为方法区
  * 编译后的类信息，包括方法信息，静态变量，finaly修饰的变量
  * 常量池也在方法区中，包括class文件信息和运行时常量池

* 堆
  * 存放运行时的对象实例
  * 分为young区和older区，young区分为Eden ，Survivor0和survivor1。

* 线程内存
  * 程序寄存器PC：保存线程运行的指令地址
  * 本地方法栈：调用JVM的native方法的辅助栈。其中存放的是栈帧
  * jvm虚拟机栈：为自定义的java方法服务，栈的深度取决于栈调用的深度。



线程内存含部分外部寄存器

![image-20210514143011913](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20210514143011913.png)

**栈帧**

* 局部变量表  局部变量和方法的引用
* 操作数栈  栈帧中用于计算的数据临时存储区
  * 字节码指令将数据压栈和入栈的的地址空间	

![image-20210514144859885](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20210514144859885.png)

## GC

* gc算法
  * 标记清除算法：将内存标记清除
  * 标记清除整理算法：将存活的对象向一端移动，之后清除边界意外的对象
  * 复制清除算法：分成两块内存，将存活对象向另外一个区域复制，然后清除本区域。两个区域轮流交换角色

* jvm 对于GC算法的应用 
  * 单线程的GC，STW的进行
  * 并行的执行，多线程执行GC
  * 并发的执行，和用户线程一起运行，STW在部分时期
  * G1GC选择性的清除，维护一个优先级。用户可以自定义gc时间。

## 字节码

* 可以依据字节码看是否有指令的重排
* jvm通过编译成的字节码文件在有jre的机器上即可以运行，实现跨平台