package McddAPI.Xianzhi.NoFish;


import McddAPI.Main;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;



public class NoFish implements Listener {
    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {
        if (!Main.instance.noFish) {
            return;
        }
        Item itm = (Item)e.getCaught();
        if (e.getState() == PlayerFishEvent.State.CAUGHT_FISH && itm.getItemStack().getType() != (new ItemStack(Material.RAW_FISH)).getType())
            e.getCaught().remove();
    }
}
