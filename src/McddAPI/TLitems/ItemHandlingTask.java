package McddAPI.TLitems;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemHandlingTask extends BukkitRunnable {
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers())ItemHandler.handleInventory(p);
    }
}


