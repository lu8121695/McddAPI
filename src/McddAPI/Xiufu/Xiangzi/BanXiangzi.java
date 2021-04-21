package McddAPI.Xiufu.Xiangzi;



import McddAPI.Main;
import org.bukkit.Material;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.inventory.ItemStack;

public class BanXiangzi implements Listener {

    @EventHandler
    public void a(VehicleEnterEvent e) {
        if (!Main.instance.banXiangzi) {
            return;
        }
        if (e.getEntered().hasPermission("McddAPI.Xiangzi"))
            return;
        if (e.getVehicle() instanceof ChestedHorse) {
            ChestedHorse ch = (ChestedHorse)e.getVehicle();
            if (ch.isCarryingChest()) {
                for (int i = 2; i < ch.getInventory().getSize(); i++) {
                    ItemStack item = ch.getInventory().getItem(i);
                    if (item != null) {
                        ch.getWorld().dropItem(ch.getLocation(), item);
                        ch.getInventory().clear(i);
                    }
                }
                ch.setCarryingChest(false);
            }
        }
    }
    @EventHandler
    public void b(PlayerInteractEntityEvent e) {
        if (!Main.instance.banXiangzi) {
            return;
        }
        if (e.getPlayer().hasPermission("McddAPI.Xiangzi"))
            return;
        Player player = e.getPlayer();
        if (e.getRightClicked() instanceof ChestedHorse && (
                isChest(player.getInventory().getItemInMainHand()) || isChest(player.getInventory().getItemInOffHand())))
            e.setCancelled(true);
    }

    private boolean isChest(ItemStack item) {
        if (item != null &&
                item.getType() == Material.CHEST)
            return true;
        return false;
    }
}