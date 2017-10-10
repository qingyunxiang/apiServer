package moe.haozi.qingyunxiang.apiServer.HttpServer;

import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Server;

import java.io.IOException;
import java.net.InetSocketAddress;


public class RestfulServer {
    public RestfulServer() throws Exception {
        Server server = new Server();
        server.use((ctx, next) -> {
            try {
                ctx.statu(200);
                ctx.ContentType("text/plain");
                ctx.write("hello world".getBytes());
                ctx.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        server.listen(new InetSocketAddress(8090));
    }
}

