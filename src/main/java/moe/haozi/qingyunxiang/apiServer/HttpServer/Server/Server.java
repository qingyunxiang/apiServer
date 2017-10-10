package moe.haozi.qingyunxiang.apiServer.HttpServer.Server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.function.*;

public class Server {
    private HttpServer server;
    private ArrayList<BiConsumer<Context, Supplier<String>>> CallbackList = new ArrayList<>();
    public Server() throws Exception{
        server = HttpServer.create();
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                Context context = new Context(httpExchange);
                IntConsumer[] fs =  {null};
                fs[0] = index -> {
                    if(CallbackList.get(index) == null) {
                        return;
                    }
                    CallbackList.get(index).accept(context, () -> {
                        fs[0].accept(index + 1);
                        return "";
                    });
                };
                fs[0].accept(0);
            }
        });
    }

    public Server listen(InetSocketAddress inetSocketAddress) throws Exception {
        server.bind(inetSocketAddress, 0);
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        return this;
    }

    public Server use(BiConsumer<Context, Supplier<String>> callback) {
        CallbackList.add(callback);
        return this;
    }
}
