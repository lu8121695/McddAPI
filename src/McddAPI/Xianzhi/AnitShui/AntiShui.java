package McddAPI.Xianzhi.AnitShui;

import McddAPI.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public final class AntiShui implements Listener {
    private int waterLimit = 9;
    private int lavaLimit = 5;
    private int lavaNetherLimit = 15;


    @EventHandler(priority = EventPriority.HIGH)
    public void onForbidWaterFall(BlockFromToEvent e) {
        if (!Main.instance.antiShui) {
            return;
        }
        Block to = e.getToBlock();
        Block fr = e.getBlock();
        if ((fr.getType() == Material.STATIONARY_WATER || fr.getType() == Material.WATER) && to.getType() == Material.AIR) {
            int i = 1;
            while (i < this.waterLimit) {
                Material t = e.getBlock().getWorld().getBlockAt(to.getLocation().getBlockX(), to.getLocation().getBlockY() - i,
                        to.getLocation().getBlockZ()).getType();
                if (t == Material.AIR || t == Material.WATER || t == Material.LAVA) {
                    i++;
                    continue;
                }
                return;
            }
            e.setCancelled(true);
        } else if ((fr.getType() == Material.STATIONARY_LAVA || fr.getType() == Material.LAVA) && to.getType() == Material.AIR) {
            int i = 1;
            int limit = this.lavaLimit;
            if (e.getBlock().getWorld().getName().contains("nether"))
                limit = this.lavaNetherLimit;
            while (i < limit) {
                Material t = e.getBlock().getWorld().getBlockAt(to.getLocation().getBlockX(), to.getLocation().getBlockY() - i,
                        to.getLocation().getBlockZ()).getType();
                if (t == Material.AIR || t == Material.WATER || t == Material.LAVA) {
                    i++;
                    continue;
                }
                return;
            }
            e.setCancelled(true);
        } else if (fr.getType() == Material.AIR &&
                to.getType() == Material.AIR) {
            e.setCancelled(true);
        }
    }
}

