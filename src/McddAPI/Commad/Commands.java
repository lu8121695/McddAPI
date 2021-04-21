package McddAPI.Commad;

import McddAPI.PlaceholderAPI.Cooldown;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import McddAPI.Main;
import org.bukkit.entity.Player;


public class Commands implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0){
            if (sender.isOp()) {
                if(args[0].equalsIgnoreCase("reload")){
                    Main.instance.reloadConfig();
                    Main.instance.loadconfig();
                    Main.instance.onMcddAPI();
                    sender.sendMessage("§aMcddAPI 已经重载");
                }
                else{
                    sender.sendMessage("§c你没有权限");
                }
            }else if (args[0].equalsIgnoreCase("cd")){
                Cooldown.setItemCooldownInHand((Player)((Object)sender), (int)Float.parseFloat(args[1]));
            }
            else{
                sender.sendMessage("§c指令输入有误");
            }
        }else{
            sender.sendMessage("§7------------- McddAPI -------------");
            sender.sendMessage("§7/mcddapi reload 重载插件");
        }
        return true;
    }
}

