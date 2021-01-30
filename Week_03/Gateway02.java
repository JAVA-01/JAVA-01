package java0.nio01;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Gateway02 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket =new ServerSocket(5555);
        while(true){
            Socket socket = serverSocket.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    OutputStream  outputStream = null;
                    try {
                        outputStream = socket.getOutputStream();
                        InputStream  inputStream =socket.getInputStream();
                        Socket socket1=new Socket("127.0.01",8801);
                        OutputStream outputStream1=socket1.getOutputStream();
                        InputStream inputStream1 =socket1.getInputStream();
                        byte b[]=new byte[1024];

                        while(inputStream.read(b)>0){
                            outputStream1.write(b);

                            inputStream1.read(b);
                            outputStream.write(b);
                        }

                        //加了这两句话算不算实现过滤器呀
                        outputStream.write("消息经网关传输成功".getBytes());
                        outputStream1.write("此消息由网关做中转".getBytes());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }


}
