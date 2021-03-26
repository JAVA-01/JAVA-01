package com.example.demo.spring;

import com.example.demo.spring.bean.Classroom;
import com.example.demo.spring.bean.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.parsing.AliasDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collections;

@NoArgsConstructor
@Data
public class Bean {

    public static void main(String[] args) {
        System.out.println("以Xml方式注入");
        /**
         * 将类路径和创建bean时需要的属性赋值都放在xml中，
         * 创建ClassPathXmlApplicationContext,参数是上面xml的文件地址
         * 在context找那个getbean（）获取类实例
         *
         * 注：单例模式，懒加载等信息也可以在xml写bean信息时添加
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
       
        Student studentA= (Student) context.getBean("student_01");
        studentA.info();
        
        Student studentB= (Student) context.getBean("student_02");
        studentB.info();

        Classroom classroom= (Classroom) context.getBean("class_A");
        classroom.info();
        /**
         *param 要扫描的包，其中包括配置类和容器 注释 :@Configuration 和@Component
         *
         * 在类上加@Component可以将其划归容器管理，在其中一个方法上加入@PostConstruct方法可以在init方法前，bean容器创建后，为bean容器赋值，
         *
         * 在配置类的方法上加@bean ，会将方法返回的类实例管理起来
         * */
        ApplicationContext context1=new AnnotationConfigApplicationContext("com.example.demo.spring.config",
                "com.example.demo.spring.bean");
        Student student3= (Student) context1.getBean("student_03");
        studentA.info();

        Student student4= (Student) context1.getBean("student_04");
        studentB.info();

        Classroom classroom2= (Classroom) context1.getBean("class_B");
        classroom.info();


        /**
         * beanDefinition 方式注入
         *  1、factory
         *  2、ConstructorArgumentValues/MutablePropertyValues
         *  3、RootBeanDefinition    容器建立   RootBeanDefinition(Student.class,agrs,null)，/
         *  new RootBeanDefinition(Classroom.class, null, propertyValues);
         *  4、factory.registerBeanDefinition  将容器注册到工厂
         *
         *
         * */
        DefaultListableBeanFactory factory=new DefaultListableBeanFactory();
        //配置方法1
        ConstructorArgumentValues agrs =new ConstructorArgumentValues();
        agrs.addIndexedArgumentValue(0,5);
        agrs.addIndexedArgumentValue(0,"len");
        agrs.addIndexedArgumentValue(0,"null");
        AbstractBeanDefinition student_05=new RootBeanDefinition(Student.class,agrs,null);
        factory.registerBeanDefinition("student_05",student_05);
        //配置方法2
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue("name","C");
        propertyValues.addPropertyValue("students", Collections.singletonList(new Student(5,"Nata"
                ,"C")));
        AbstractBeanDefinition classroom_c = new RootBeanDefinition(Classroom.class, null, propertyValues);
        factory.registerBeanDefinition("classroom_c", classroom_c);
        //get bean
        Student student05 = (Student) factory.getBean("student_05");
        student05.info();
        Classroom classroomC = (Classroom) factory.getBean("classroom_c");
        classroomC.info();

    }
}
