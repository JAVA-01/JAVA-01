import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
            Class log =null;
            //获取该文件的字节码数组
            byte [] classData =getData();

            if (classData !=null){
                log=defineClass(name,classData,0,classData.length);
            }
            return  log;

    }
    public  byte [] getData()  {
        FileInputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            inputStream = new FileInputStream("src\\Hello.xlass");
             byteArrayOutputStream=new ByteArrayOutputStream();
            byte [] data =new byte[1024];
            int size;
            while ((size =inputStream.read(data))!=-1){
                for (int i = 0; i <size ; i++) {
                    data[i]=(byte) (~data[i]);
                }
                byteArrayOutputStream.write(data,0,size);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();
    }

}
