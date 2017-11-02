//package moe.haozi.qingyunxiang.apiServer.HttpServer.Controllers.Server;
//
//import moe.haozi.qingyunxiang.apiServer.Annotations.Server;
//import moe.haozi.qingyunxiang.apiServer.HttpServer.Router.Decorators.*;
//import moe.haozi.qingyunxiang.apiServer.HttpServer.Server.Context;
//import org.json.simple.*;
//
//@Controller("/whitelist")
//public class ServerController {
//    @Get
//    @Path("/:id")
//    @HttpStatuCode(200)
//    public void getWhiteList(Context ctx, @Server() org.bukkit.Server server, @Param("id") String id) {
//        System.out.println("Url -> ");
//        System.out.println(ctx.httpExchange.getRequestURI());
//        try {
//            ctx.ContentType("application/json");
//            JSONObject jb = new JSONObject();
//            jb.put("url", ctx.url().getPath());
//            JSONArray jr = new JSONArray();
//            server.getWhitelistedPlayers().forEach(player -> {
//                jr.add(player.getName());
//            });
//            jb.put("user", jr);
//            jb.put("total", jr.size());
//            jb.put("id", id);
//            ctx.write(jb.toJSONString().getBytes());
//            ctx.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
