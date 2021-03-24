package mystarterspringbootstarter.demo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-03-20
 */
public class School implements Ishool{


    @Resource(name = "student1000")
    Student student1000;

    @Autowired
    Klass aClass20;



    @Override
    public void teach() {
        System.out.println("We will make you grow");
    }
}
