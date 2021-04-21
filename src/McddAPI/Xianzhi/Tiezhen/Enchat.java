package McddAPI.Xianzhi.Tiezhen;

import McddAPI.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class Enchat implements Listener {
    @EventHandler
    public void onEnchat(EnchantItemEvent e) {
        if (!Main.instance.enchat) {
            return;
        }
        if (e.getEnchanter().hasPermission("McddAPI.Fumo") || e.getEnchanter().isOp())
            return;
        e.getEnchanter().closeInventory();
        e.setCancelled(true);
        e.getEnchanter().sendTitle("* 附魔功能已禁用 *"," ", 30, 5, 5);
    }
}
