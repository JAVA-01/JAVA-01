import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader =new MyClassLoader();
        Class<?> Log =myClassLoader.loadClass("Hello");
        Method hello =Log.getDeclaredMethod("hello",null);
//        Class<?> Hello = Class.forName("Hello",true,myClassLoader);
        hello.invoke(Log.getDeclaredConstructor().newInstance());
    }
}
