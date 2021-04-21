package McddAPI.Xiufu.BoxFix;


import McddAPI.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;


public class BoxFixer implements Listener {
    @EventHandler
    public void onBlockDispense(BlockDispenseEvent e) {
        if (!Main.instance.boxFixer) {
            return;
        }
        if ((e.getBlock().getY() == 0 || e.getBlock().getY() == e.getBlock().getWorld().getMaxHeight() - 1) &&
                e.getItem().getType().name().endsWith("SHULKER_BOX"))
            e.setCancelled(true);
    }
}
