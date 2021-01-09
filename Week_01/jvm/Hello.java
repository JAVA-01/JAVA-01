package jvm;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Hello extends ClassLoader {

    public void hello(){
    }

    public static void main(String[] args) {
        Hello hello = new Hello();
        try {
            Class orginHello = hello.findClass("Hello");
            Method[] methods = orginHello.getMethods();
            for (int i = 0; i < methods.length; i++) {
                System.out.println(methods[i].getName());
            }
            Method method = orginHello.getMethod("hello");
            method.invoke(orginHello.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bs = getClassBS();
        if (bs == null) {
            return null;
        }

        for (int i = 0; i < bs.length; i++) {
            bs[i] = (byte) (255 - bs[i]);
        }
        return defineClass(name, bs, 0, bs.length);
    }

    private byte[] getClassBS() {
        byte[] bs;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("Hello.xlass"));
            bs = new byte[fileInputStream.available()];
            fileInputStream.read(bs);
            return bs;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}