package mystarterspringbootstarter.demo;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-03-20
 */

@Data
public class Klass {

    String classname;

    @Autowired
   Student student;


    public   void readbook(){
        System.out.println("they are read books");
    }

}
