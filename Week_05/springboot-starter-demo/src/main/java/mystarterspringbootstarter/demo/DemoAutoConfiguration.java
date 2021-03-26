package mystarterspringbootstarter.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-03-20
 */

@Configuration
public class DemoAutoConfiguration {

    @Bean(name = "student1000")
    public Student  getStudent(){
        return  new Student("jia",1);
    }
    @Bean
    public  Klass getKlass(){
        return new Klass();
    }

    @Bean
    public School getSchool(){
        return  new School();

    }


}
