package java0.nio01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket =new Socket("127.0.0.1",8801);
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //读取第一行，状态码和协议
            System.out.println(bufferedReader.readLine());
            //读取第二行，文本类型和字符串编码
            System.out.println(bufferedReader.readLine());
            //读取第三行，报文体长度
            String length =bufferedReader.readLine();
            String [] lengths= length.split(":");
            System.out.println(lengths[1]);
            //读取报文头和报文体之间的换行，这个换行站两个字符，可以会使读取下面的报文体有遗漏
            bufferedReader.readLine();
            //UTF-8下一个英文字符相当于一个字节，上面的长度是字节数
            char body[]=new char[Integer.valueOf(lengths[1])];
            bufferedReader.read(body,0,Integer.valueOf(lengths[1]));
            System.out.println("body:"+new String(body));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
