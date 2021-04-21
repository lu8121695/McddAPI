package McddAPI.Xianzhi.NoHunger;


import McddAPI.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;


public class NoHunger implements Listener {
    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        if (!Main.instance.noHunger) {
            return;
        }
        Player p = (Player)e.getEntity();
        e.setCancelled(true);
        p.setFoodLevel(20);
    }
}
