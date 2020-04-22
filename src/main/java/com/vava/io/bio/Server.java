package com.vava.io.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Steve
 * Created on 2020-04
 * <p>
 * reference:
 * https://www.bilibili.com/video/BV1i4411p7kk
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress("127.0.0.1", 6666));
        while (true) {
            Socket s = ss.accept();
            new Thread(() -> {
                handle(s);
            }).start();
        }
    }

    private static void handle(Socket s) {
        try {
            byte[] bytes = new byte[1024];
            int len = s.getInputStream().read(bytes);
            System.out.println(new String(bytes, 0, len));
            s.getOutputStream().write(bytes, 0 ,len);
            s.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
