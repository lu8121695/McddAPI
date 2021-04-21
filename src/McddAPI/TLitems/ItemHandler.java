package McddAPI.TLitems;

import McddAPI.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemHandler {
    public static boolean isExpired(ItemStack itemStack, Date now) {
        if (!itemStack.hasItemMeta()) {
            return false;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (!itemMeta.hasLore()) {
            return false;
        }
        for (String loreLine : itemMeta.getLore()) {
            if (loreLine.length() != 16 + Main.startword.length() || !loreLine.startsWith(Main.startword)) {
                continue;
            }
            String expireDateString = loreLine.substring(0 + Main.startword.length(), 16 + Main.startword.length());
            Date expireDate = null;
            try {
                expireDate = DATE_FORMAT.parse(expireDateString);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            if (expireDate.before(now)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isExpired(ItemStack itemStack) {
        return isExpired(itemStack, new Date());
    }
    public static boolean isExpirable(ItemStack itemStack) {
        if (!itemStack.hasItemMeta()) {
            return false;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (!itemMeta.hasLore()) {
            return false;
        }
        for (String loreLine : itemMeta.getLore()) {
            if (loreLine.length() != 16 + Main.startword.length() || !loreLine.startsWith(Main.startword)) {
                continue;
            }
            return true;
        }
        return false;
    }
    public static void handleInventory(Player p) {
        ItemStack[] items = p.getInventory().getContents();
        boolean changed = false;
        for (int i = 0; i < items.length; i++) {
            ItemStack item = items[i];
            if (item != null && isExpired(item)) {
                items[i] = null;
                changed = true;
            }
        }
        if (changed) {
            p.getInventory().setContents(items);
        }
        ItemStack[] armors = p.getInventory().getArmorContents();
        boolean armorChanged = false;
        for (int j = 0; j < armors.length; j++) {
            ItemStack armor = armors[j];
            if (armor != null && isExpired(armor)) {
                armors[j] = null;
                armorChanged = true;
            }
        }
        if (armorChanged) {
            p.getInventory().setArmorContents(armors);
        }
    }
    public static void handleInvItem(Inventory inv, int slot) {
        ItemStack item = inv.getItem(slot);
        if (item == null) {
            return;
        }
        if (isExpired(item)) {
            inv.setItem(slot, null);
        }
    }
    public static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
}
