package com.vava.io.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Steve
 * Created on 2020-04
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 6666);
        s.getOutputStream().write("HelloServer".getBytes());
        s.getOutputStream().flush();
        System.out.println("client write end, wait for response");
        byte[] bytes = new byte[1024];
        int len = s.getInputStream().read(bytes);
        System.out.println("response:" + new String(bytes, 0, len));
        s.close();
    }
}
