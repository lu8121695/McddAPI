package McddAPI.TLitems;

import McddAPI.Main;
import eos.moe.dragoncore.DragonCore;
import eos.moe.dragoncore.config.Config;
import eos.moe.dragoncore.database.IDataBase;
import eos.moe.dragoncore.network.PacketSender;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ItemListener implements Listener {
    private Main main;

    public ItemListener() { this.main = main; }

    @EventHandler
    public void onInventoryOpen(InventoryCloseEvent e) {
        if (!Main.instance.itemListener) {
            return;
        }
        if (!(e.getPlayer() instanceof Player))
            return;
        ItemHandler.handleInventory((Player)e.getPlayer());
        Player p = (Player)e.getPlayer();
        Config.getSlotConfig().getKeys(false).forEach(slot -> Main.core.getDB().getData((Player)e.getPlayer(), slot, new IDataBase.Callback<ItemStack>(){
            public void onResult(ItemStack itemStack) {
                if (itemStack == null) {
                    itemStack = new ItemStack(Material.AIR);
                    return;
                }
                if (!ItemHandler.isExpired(itemStack))
                    return;
                final ItemStack iih = itemStack;
                iih.setType(Material.AIR);
                Main.core.getDB().setData(p, slot, iih, new IDataBase.Callback<ItemStack>() {
                    public void onResult(ItemStack p0) {
                        PacketSender.putClientSlotItem((Player)e.getPlayer(), slot, iih);
                    }
                    public void onFail() {
                        p.getInventory().addItem(new ItemStack[] { iih });
                    }
                });
                PacketSender.putClientSlotItem(p, slot, itemStack);
            }
            public void onFail() {}
        }));
    }
    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e) {
        if (!Main.instance.itemListener) {
            return;
        }
        if (ItemHandler.isExpired(e.getItem().getItemStack())) {
            e.getItem().remove();
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!Main.instance.itemListener) {
            return;
        }
        ItemHandler.handleInventory(e.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onItemConsume(final PlayerItemConsumeEvent e) {
        if (!Main.instance.itemListener) {
            return;
        }
        if (e.getItem() == null || !ItemHandler.isExpirable(e.getItem()))
            return;
        if (!ItemHandler.isExpired(e.getItem())) {
            final ItemStack item = new ItemStack(e.getItem());
            item.setAmount(1);
            if (e.getItem().getType() == Material.POTION) {
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.main, new Runnable() {
                    public void run() {
                        e.getPlayer().setItemInHand(item);
                        e.getPlayer().updateInventory();
                    }
                });
            }
            else {
                e.getPlayer().getInventory().addItem(new ItemStack[] {item});
                e.getPlayer().updateInventory();
            }
        }
    }
}
