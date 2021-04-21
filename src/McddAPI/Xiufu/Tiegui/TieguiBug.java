package McddAPI.Xiufu.Tiegui;

import McddAPI.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class TieguiBug implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void OnPhysEvent(BlockPhysicsEvent e) {
        if (!Main.instance.tieguiBug) {
            return;
        }
        Block b = e.getBlock();
        Material mat = b.getType();
        int is = 0;
        if (mat == Material.RAILS) {
            is = 1;
        } else if (mat == Material.POWERED_RAIL) {
            is = 1;
        } else if (mat == Material.DETECTOR_RAIL) {
            is = 1;
        } else if (mat == Material.ACTIVATOR_RAIL) {
            is = 1;
        } else if (mat == Material.CARPET) {
            is = 1;
        }
        if (is == 1) {
            Material cmat = e.getChangedType();
            Block db = b.getRelative(BlockFace.DOWN, 1);
            Material dmat = db.getType();
            if (dmat == Material.SLIME_BLOCK) {
                is = 2;
            } else if (dmat == Material.PISTON_MOVING_PIECE) {
                is = 2;
            } else if (dmat == Material.PUMPKIN) {
                is = 2;
            } else if (dmat == Material.PISTON_STICKY_BASE) {
                is = 2;
            } else if (dmat == Material.PISTON_EXTENSION) {
                is = 2;
            } else if (dmat == Material.PISTON_BASE) {
                is = 2;
            }
            if (dmat == Material.AIR) {
                is = 2;
            } else if (cmat == Material.PUMPKIN) {
                is = 2;
            } else if (cmat == Material.SLIME_BLOCK) {
                is = 2;
            } else if (cmat == Material.PISTON_MOVING_PIECE) {
                is = 2;
            } else if (cmat == Material.PISTON_BASE) {
                is = 2;
            } else if (cmat == Material.PISTON_STICKY_BASE) {
                is = 2;
            } else if (cmat == Material.PISTON_EXTENSION) {
                is = 2;
            } else if (cmat == Material.DISPENSER) {
                is = 2;
            } else if (cmat == Material.DROPPER) {
                is = 2;
            }
        }
        if (is == 2)
            e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void OnBreakEvent(BlockBreakEvent e) {
        if (!Main.instance.tieguiBug) {
            return;
        }
        if (!e.isCancelled()) {
            Block b = e.getBlock();
            Block b2 = b.getRelative(BlockFace.UP, 1);
            int is = 0;
            if (b2.getType() == Material.RAILS) {
                is = 1;
            } else if (b2.getType() == Material.POWERED_RAIL) {
                is = 1;
            } else if (b2.getType() == Material.DETECTOR_RAIL) {
                is = 1;
            } else if (b2.getType() == Material.ACTIVATOR_RAIL) {
                is = 1;
            } else if (b2.getType() == Material.CARPET) {
                is = 1;
            }
            if (is == 1)
                DropRailIfAir(b, b2);
        }
    }

    private void DropRailIfAir(final Block b, final Block b2) {
        Bukkit.getServer().getScheduler().runTaskLater((Plugin)Main.main, new Runnable() {
            public void run() {
                if (b.getType() == Material.AIR) {
                    int is = 0;
                    byte wert = 0;
                    if (b2.getType() == Material.RAILS) {
                        is = 1;
                    } else if (b2.getType() == Material.POWERED_RAIL) {
                        is = 2;
                    } else if (b2.getType() == Material.DETECTOR_RAIL) {
                        is = 3;
                    } else if (b2.getType() == Material.ACTIVATOR_RAIL) {
                        is = 4;
                    } else if (b2.getType() == Material.CARPET) {
                        is = 5;
                        wert = b2.getData();
                    }
                    if (is != 0) {
                        b2.setType(Material.AIR);
                        if (is == 1) {
                            b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.RAILS, 1));
                        } else if (is == 2) {
                            b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.POWERED_RAIL, 1));
                        } else if (is == 3) {
                            b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.DETECTOR_RAIL, 1));
                        } else if (is == 4) {
                            b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.ACTIVATOR_RAIL, 1));
                        } else if (is == 5) {
                            b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.CARPET, 1, wert));
                        }
                    }
                }
            }
        },10L);
    }
}



