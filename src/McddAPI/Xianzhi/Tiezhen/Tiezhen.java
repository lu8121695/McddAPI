package McddAPI.Xianzhi.Tiezhen;


import McddAPI.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Tiezhen implements Listener {
    @EventHandler
    public void onTiezhen(PlayerInteractEvent event) {
        if (!Main.instance.tiezhen) {
            return;
        }
        if (event.hasBlock()) {
            Location location = event.getClickedBlock().getLocation();
            if (event.getClickedBlock().getType() == Material.getMaterial(145)) {
                if (event.getPlayer().hasPermission("McddAPI.Tiezhen") || event.getPlayer().isOp())
                    return;
                event.setCancelled(true);
                event.getPlayer().sendTitle("* 铁砧功能已禁用 *", "", 30, 5, 5);
            }
        }
    }
}
