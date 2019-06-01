package moe.haozi.qingyunxiang.apiServer;

import moe.haozi.qingyunxiang.apiServer.HttpServer.RestfulServer;
import org.bukkit.Color;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ApiServer extends JavaPlugin {
    private Connection connection;
    private String prefix = McColor.red + "[工艺小镇]" + McColor.white;

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(
//                    "jdbc:mysql://127.0.0.1:3306/craft_townlet",
//                    "craftTownlet",
//                    "LyVMP8h3GnWP8iM"
//
//            );
//
//            if (connection.isClosed()) {
//                getServer().getLogger().info("链接数据库失败");
//            }
//
//            getLogger().info("ApiServer 数据库连接初始化完成");

            new RestfulServer(getServer(), getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
        getLogger().info(prefix + "插件加载成功");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("这个命令只能玩家用");
        }

        if (command.getName().equalsIgnoreCase("qqlink")) {
            if (args.length != 0) {
                sender.sendMessage(McColor.red + "参数格式为 /qqlink <QQ 号码 >");
                sender.sendMessage("必须是在群" + McColor.red + "782490531 \t" + McColor.Reset + "且没有被其他游戏玩家绑定过的账号");
                return true;
            }
        }
        return false;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void A(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!(player instanceof Player)) {
            getLogger().info("有鬼进服了");
        }
    }

}