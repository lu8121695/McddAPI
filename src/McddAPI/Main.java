package McddAPI;

import java.util.logging.Logger;

import McddAPI.Commad.Commands;
import McddAPI.NoRespawnScreen.NoRespawn;
import McddAPI.TLitems.ItemHandlingTask;
import McddAPI.Xianzhi.NoEffect.NoEffect;
import eos.moe.dragoncore.DragonCore;
//
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.HandlerList;
//
import McddAPI.Xianzhi.AnitShui.AntiShui;
import McddAPI.Xiufu.BoxFix.BoxFixer;
import McddAPI.Xianzhi.NoFish.NoFish;
import McddAPI.Xianzhi.NoHunger.NoHunger;
import McddAPI.Xiufu.Tiegui.TieguiBug;
import McddAPI.Xianzhi.Tiezhen.Tiezhen;
import McddAPI.Xiufu.Xiangzi.BanXiangzi;
import McddAPI.PlaceholderAPI.TimePapi;
import McddAPI.TLitems.ItemListener;
import McddAPI.Xianzhi.Tiezhen.Enchat;
//




public  class Main extends JavaPlugin {
    public static Main instance;
    public static NoEffect noeffect;
    public static String startword;
    public static DragonCore core;
    private boolean isHookPlaceholder;
    public static Main main = null;
    private Logger logger = getLogger();

    public String name;
    public String version;


    public boolean antiShui;
    public boolean noFish;
    public boolean banXiangzi;
    public boolean tieguiBug;
    public boolean boxFixer;
    public boolean tiezhen;
    public boolean enchat;
    public boolean noHunger;
    public boolean itemListener;
    public boolean effect;
    public boolean siwang;

    @Override
    public void onEnable(){
        this.version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        if (this.version.equals("v1_12_R1")) {
            this.name = "getCooldownTracker";
        } else {
            this.name = null;
        }
        if (this.name == null) {
            getLogger().severe("&c你的游戏版本不支持使用本插件..");
            return;
        }
        instance = this;
        noeffect = new NoEffect();
        getLogger().info("§a开始载入插件....");
        getLogger().info("§a已兼容龙之核心...!");
        hookPlaceHolderAPI();
        saveDefaultConfig();
        getLogger().info("§a默认配置已保存....");
        loadconfig();
        getLogger().info("§a配置文件加载完成....");
        onMcddAPI();
        getLogger().info("§a监听事件注册成功....");
        Bukkit.getPluginCommand("mcddapi").setExecutor((CommandExecutor)new Commands());
        getLogger().info("§aMcddAPI加载完成...");
    }
    public void onDisable(){
        HandlerList.unregisterAll((Plugin)this);
        Bukkit.getScheduler().cancelTasks((Plugin)this);
        super.onDisable();
        getLogger().info("§cMcddAPI 已卸载");
    }
    public static Main getMain() {
        return instance;
    }
    public void ItemListenerreload() {
        core = DragonCore.getInstance();
        startword = Main.instance.getConfig().getString("startword");
        (new ItemHandlingTask()).runTaskTimer((Plugin)this, 0L, 1200L);
    }
    public void onMcddAPI() {
        noeffect.onEffect();
        WorldCommandBlock();
        getServer().getPluginManager().registerEvents(new NoRespawn(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new ItemListener(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new Tiezhen(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new Enchat(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new BanXiangzi(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new TieguiBug(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new NoHunger(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new BoxFixer(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new NoFish(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new AntiShui(), (Plugin)this);
    }

    public void  loadconfig(){
        siwang = getConfig().getBoolean("Siwang");
        if (siwang) {
            getLogger().info("§6关闭死亡窗口 §a已启用!");
        } else {
            getLogger().info("§6关闭死亡窗口 §c未启用!");
        }
        antiShui = getConfig().getBoolean("AntiShui");
        if (antiShui) {
            getLogger().info("§6高空水流限制 §a已启用!");
        } else {
            getLogger().info("§6高空水流限制 §c未启用!");
        }
        effect = getConfig().getBoolean("Effect");
        if (effect) {
            getLogger().info("§6攻击粒子减少 §a已启用!");
        } else {
            getLogger().info("§6攻击粒子减少 §c未启用!");
        }
        banXiangzi = getConfig().getBoolean("BanXiangzi");
        if (banXiangzi) {
            getLogger().info("§6禁止给驴马放箱子 §a已启用!");
        } else {
            getLogger().info("§6禁止给驴马放箱子 §c未启用!");
        }
        tiezhen = getConfig().getBoolean("Tiezhen");
        if (tiezhen) {
            getLogger().info("§6铁砧禁用功能 §a已启用!");
        } else {
            getLogger().info("§6铁砧禁用功能 §c未启用!");
        }
        boxFixer = getConfig().getBoolean("BoxFixer");
        if (boxFixer) {
            getLogger().info("§6潜影盒蹦服bug修复 §a已启用!");
        } else {
            getLogger().info("§6潜影盒蹦服bug修复 §c未启用!");
        }
        enchat = getConfig().getBoolean("Enchat");
        if (enchat) {
            getLogger().info("§6附魔禁用功能 §a已启用!");
        } else {
            getLogger().info("§6附魔禁用功能 §c未启用!");
        }
        noHunger = getConfig().getBoolean("NoHunger");
        if (noHunger) {
            getLogger().info("§6饱食度锁定 §a已启用!");
        } else {
            getLogger().info("§6饱食度锁定 §c未启用!");
        }
        noFish = getConfig().getBoolean("NoFish");
        if (noFish) {
            getLogger().info("§6钓鱼不出附魔书 §a已启用!");
        } else {
            getLogger().info("§6钓鱼不出附魔书 §c未启用!");
        }
        tieguiBug = getConfig().getBoolean("TieguiBug");
        if (tieguiBug) {
            getLogger().info("§6刷铁轨bug修复 §a已启用!");
        } else {
            getLogger().info("§6刷铁轨bug修复 §c未启用!");
        }
        itemListener = getConfig().getBoolean("ItemListener");
        if (itemListener) {
            ItemListenerreload();
            getLogger().info("§6时间限制功能 §a已启用!");
        } else {
            getLogger().info("§6时间限制功能 §c未启用!");
        }
    }

    public boolean isHookPlaceholder() {
        return isHookPlaceholder;
    }
    private void hookPlaceHolderAPI() {
        this.isHookPlaceholder = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
        if (isHookPlaceholder()) {
            instance.logger.info("§aPlaceholderAPI 已检测到!");
            this.logger.info("§aPlaceholderAPI 已注册!");
            TimePapi api = new TimePapi(Main.instance);
            this.logger.info("§aPlaceholderAPI 已成功加载!");
            api.register();
        }
    }
    public void  WorldCommandBlock() {
        getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler(priority = EventPriority.HIGHEST)
            public void onCommand(PlayerCommandPreprocessEvent e) {
                Player p = e.getPlayer();
                if (getConfig().contains(p.getLocation().getWorld().getName()))
                    for (String cmd : getConfig().getStringList(p.getLocation().getWorld().getName())) {
                        if (e.getMessage().toLowerCase().contains(cmd.toLowerCase())) {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED + "该指令不存在");
                        }
                    }
            }
        },  (Plugin)this);
    }
}

