# Spring容器注入的三种方式

~~~json
# bean依赖注入需要的条件。
1.类信息——类路径
2、bean的属性信息—这个类的属性，不写默认为空
以上可以被称为上下文Context，xml方法获取bean就是从context中直接获取

~~~



## 1、以XML方法注入

### 编写XMl文件，将类路径和要添加的属性信息放在xml中，用于创建bean

~~~
         * 将类路径和创建bean时需要的属性赋值都放在xml中，
         * 创建ClassPathXmlApplicationContext,参数是上面xml的文件地址
         * 在context找那个getbean（）获取类实例
         *
         * 注：单例模式，懒加载等信息也可以在xml写bean信息时添加
~~~

不足：如果改变了类的位置，xml中的信息也要更改。这样显得很不灵活，很笨重。

![image-20210220214957302](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20210220214957302.png)

![image-20210220224148187](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20210220224148187.png)

## 2、Annotation方法注入

~~~json
# 类信息和属性填充：
AnnotationConfigApplicationContext参数是 容器配置 和 要扫描的类
1、容器配置类的方法上加@bean("beanId")，类上加@Configuration，一个配置类中可以有多个注解了@bean的方法来添加bean
在注解类中可以声明ApplicationContext注入Spring容器管理中的bean到新创建的实例bean中。
2、容器类上面加@Component("BeanId")
从AnnotationConfigApplicationContext中以beanID 来 getbean

~~~

![image-20210220233455549](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20210220233455549.png)

![image-20210220233514045](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20210220233514045.png)

## 3、beanDefinition

~~~json
#beanDefinition 方式注入
         *  1、factory
         *  2、ConstructorArgumentValues/MutablePropertyValues
         *  3、RootBeanDefinition    容器建立   
		    new RootBeanDefinition(Student.class,agrs,null)
            new RootBeanDefinition(Classroom.class, null, propertyValues);
         *  4、factory.registerBeanDefinition  将容器注册到工厂
~~~

![image-20210220235940001](C:\Users\DELL\AppData\Roaming\Typora\typora-user-images\image-20210220235940001.png)