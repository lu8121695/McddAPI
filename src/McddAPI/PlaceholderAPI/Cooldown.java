package McddAPI.PlaceholderAPI;


import java.lang.reflect.Method;

import McddAPI.Main;
import org.bukkit.entity.Player;

public class Cooldown {

    public static boolean isItemCooldownInHand(Player player) {
        return getItemCooldownInHand(player, 1) > 0.0F;
    }

    public static void setItemCooldownInHand(Player player, int tick) {
        try {
            Class<?> che = Class.forName("org.bukkit.craftbukkit." + Main.getMain().version + ".entity.CraftHumanEntity");
            Object converted = che.cast(player);
            Method handle = converted.getClass().getMethod("getHandle");
            Object eh = handle.invoke(converted);
            Method iteminhand = eh.getClass().getMethod("getItemInMainHand");
            Object ih = iteminhand.invoke(eh);
            Method mitem = ih.getClass().getMethod("getItem");
            Object item = mitem.invoke(ih);
            Method mic = eh.getClass().getMethod(Main.getMain().name);
            Object ic = mic.invoke(eh);
            Class<?> itemc = Class.forName("net.minecraft.server." + Main.getMain().version + ".Item");
            Method zx = ic.getClass().getMethod("a", new Class[] {itemc, int.class});
            zx.invoke(ic, new Object[]{item, tick});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getItemCooldownInHand(Player player, int v) {
        try {
            Class<?> che = Class.forName("org.bukkit.craftbukkit." + Main.main.version + ".entity.CraftHumanEntity");
            Object converted = che.cast(player);
            Method handle = converted.getClass().getMethod("getHandle");
            Object eh = handle.invoke(converted);
            Method iteminhand = eh.getClass().getMethod("getItemInMainHand");
            Object ih = iteminhand.invoke(eh);
            Method mitem = ih.getClass().getMethod("getItem");
            Object item = mitem.invoke(ih);
            Method mic = eh.getClass().getMethod(Main.getMain().name);
            Object ic = mic.invoke(eh);
            Class<?> itemc = Class.forName("net.minecraft.server." + Main.getMain().version + ".Item");
            Method zx = ic.getClass().getMethod("a", new Class[] {itemc, float.class});
            Object zxx = zx.invoke(ic, new Object[]{item, 0.0F});
            if(zxx instanceof Float){
                return (int)zxx * v;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
