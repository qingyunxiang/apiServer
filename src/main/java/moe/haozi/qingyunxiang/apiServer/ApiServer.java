package moe.haozi.qingyunxiang.apiServer;

import moe.haozi.qingyunxiang.apiServer.HttpServer.RestfulServer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.util.Timer;
import java.util.TimerTask;

public class ApiServer extends JavaPlugin {
    private Connection connection;
    private String prefix = McColor.red + "[工艺小镇]" + McColor.white;
    private Timer timer;
    private FileConfiguration configuration = null;

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
            new RestfulServer(getServer(), getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }

        reload_config();


        getLogger().info(prefix + "插件加载成功");


//        task();
    }

    public void reload_config() {
        //创建插件文件夹与config.yml文件
        File file = new File(getDataFolder(),"config.yml");
        if(!getDataFolder().exists()){//判断目录是否存在 这里的目录是 plugin/插件名称/
            getDataFolder().mkdir();//不存在则创建这个文件夹
        }
        if(!file.exists()){//判断文件是否存在
            this.saveDefaultConfig();//不存在则创建默认的config.yml文件
        }
        this.reloadConfig();//重载配置

        try {
            this.configuration = YamlConfiguration.loadConfiguration(file);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    private void timer_stop_server(long time) {
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new TimerTask() {
            private int count =0;
            @Override
            public void run() {
                count++;
                String str = null;
                String default_str = "服务器将会在 %time% 后关闭";
                if ((time - count) == 180) {
                    str = getConfig().getString("st.180", default_str);
                }
                if ((time - count) == 120) {
                    str = getConfig().getString("st.120", default_str);
                }
                if ((time - count) == 60) {
                    str = getConfig().getString("st.60", default_str);
                }
                if ((time - count) == 30) {
                    str = getConfig().getString("st.30", default_str);
                }
                if ((time - count) <= 10) {
                    str = getConfig().getString("st.10", default_str);
                    getServer().getOnlinePlayers().forEach(
                            player -> {
                                ((Player) player).playSound(((Player) player).getLocation(), Sound.VILLAGER_HIT, 1, 1);
                            }
                    );
                }

                if (time - count == 0) {
                    getServer().shutdown();
                }
                if (str != null) {
                    getServer().broadcastMessage(
                            getConfig().getString("st.prefix") +
                                    str.replace("%time%", time - count + "")
                    );
                }
            }
        }, 0, 1000);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof ConsoleCommandSender) {

            if (command.getName().equalsIgnoreCase("st") && args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    this.reload_config();
                    sender.sendMessage("配置文件已重载");
                    return true;
                }
                if (args[0].equalsIgnoreCase("cancel")) {
                    if (this.timer != null) {
                        this.timer.cancel();
                        sender.sendMessage("已取消");
                        getServer().broadcastMessage(
                                getConfig().getString("st.prefix") +
                                        getConfig().getString("st.cancel")
                        );
                    }
                    return true;
                }
                int time = Integer.parseInt(args[0]);
                timer_stop_server(time);
                getServer().broadcastMessage(
                        getConfig().getString("st.prefix") +
                        getConfig().getString("st.start").replace("%time%", time / 60 + "")
                );
                return true;
            } else {
                sender.sendMessage("你在用你马呢. /st <时间(秒)>   \n /st cancel 取消");
                return true;
            }
        }



//        if (!(sender instanceof Player)) {
//            sender.sendMessage("这个命令只能玩家用");
//        }

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

    public void task() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getServer().dispatchCommand(getServer().getConsoleSender(), "um tps");
                getServer().dispatchCommand(getServer().getConsoleSender(), "lag");
                getServer().dispatchCommand(getServer().getConsoleSender(), "who");
            }
        }, 60000L, 60000L);
    }
}