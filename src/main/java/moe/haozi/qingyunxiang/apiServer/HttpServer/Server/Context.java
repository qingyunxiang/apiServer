package moe.haozi.qingyunxiang.apiServer.HttpServer.Server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class Context {
    public HttpExchange httpExchange;
    public Context(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
    }




    public Context statu(int code) throws IOException {
        this.httpExchange.sendResponseHeaders(code, 0);
        return  this;
    }

    public Context ContentType(String type) {
        this.httpExchange.getResponseHeaders().set("Content-Type", type);
        return this;

    }

    public void close() {
        this.httpExchange.close();
    }

    public Context write(int b) throws IOException {
        this.httpExchange.getResponseBody().write(b);
        return this;
    }
    public Context write(byte b[]) throws IOException {
        this.httpExchange.getResponseBody().write(b);
        return this;
    }
    public Context write(byte b[], int off, int len) throws IOException {
        this.httpExchange.getResponseBody().write(b, off, len);
        return this;
    }
}
