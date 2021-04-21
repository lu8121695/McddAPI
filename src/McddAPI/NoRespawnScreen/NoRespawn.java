package McddAPI.NoRespawnScreen;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Logger;

import McddAPI.Main;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class NoRespawn implements Listener {
    public void forceRespawn(final Player player) {
        new BukkitRunnable(){
            public void run() {
                try {
                    Object nmsPlayer = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
                    Object con = nmsPlayer.getClass().getDeclaredField("playerConnection").get(nmsPlayer);
                    Class<?> EntityPlayer = Class.forName(String.valueOf(nmsPlayer.getClass().getPackage().getName()) + ".EntityPlayer");
                    Field minecraftServer = con.getClass().getDeclaredField("minecraftServer");
                    minecraftServer.setAccessible(true);
                    Object mcserver = minecraftServer.get(con);
                    Object playerlist = mcserver.getClass().getDeclaredMethod("getPlayerList", new Class[0]).invoke(mcserver, new Object[0]);
                    Method moveToWorld = playerlist.getClass().getMethod("moveToWorld", EntityPlayer, Integer.TYPE, Boolean.TYPE);
                    moveToWorld.invoke(playerlist, nmsPlayer, 0, false);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }.runTaskLater(Main.instance, 3L);
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (!Main.instance.siwang) {
            return;
        }
        Player player = e.getEntity();
        forceRespawn(player);
    }
}