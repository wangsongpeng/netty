package com.wsp.netty.reactor.single;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 接受客户端请求,处理请求操作
 *
 * @author wsp
 */
public class Acceptor implements Runnable {

    /**
     * 服务端Socket管道对象
     */
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;


    public Acceptor(ServerSocketChannel serverSocketChannel,Selector selector) {
        this.serverSocketChannel = serverSocketChannel;
        this.selector = selector;
    }

    public void run() {
        try {
            SocketChannel ch = serverSocketChannel.accept();//接收请求
            if (ch != null) {
                System.out.println("New Connection ... " + ch.getRemoteAddress());
                new ReadWriteHandler(selector, ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
