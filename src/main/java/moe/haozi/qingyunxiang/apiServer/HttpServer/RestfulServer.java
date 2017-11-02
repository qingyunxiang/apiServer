package moe.haozi.qingyunxiang.apiServer.HttpServer;

import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Router;
import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Server;

import java.io.IOException;
import java.net.InetSocketAddress;


public class RestfulServer {
    public RestfulServer(org.bukkit.Server bukkit,ClassLoader classLoader) throws IOException, Exception {
        Server server = new Server();
        Router.RegisterRoute( "moe.haozi.qingyunxiang.apiServer.HttpServer.Controllers", classLoader, bukkit);
        server.use(Router.route());
        server.use((ctx, next) -> {
            ctx.close();
        });
        server.listen(new InetSocketAddress(8099));
    }
}

