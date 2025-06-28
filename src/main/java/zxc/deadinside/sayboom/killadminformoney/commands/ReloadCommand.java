package zxc.deadinside.sayboom.killadminformoney.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import static zxc.deadinside.sayboom.killadminformoney.messages.MessageUtils.parse;
import static zxc.deadinside.sayboom.killadminformoney.config.MessagesConfig.*;
import zxc.deadinside.sayboom.killadminformoney.config.ConfigManager;
import zxc.deadinside.sayboom.killadminformoney.config.MessagesConfig;
import zxc.deadinside.sayboom.killadminformoney.modules.ModuleManager;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("kafm.reload")) {
            sender.sendMessage(parse(noPermission()));
            return true;
        }
        if (args.length == 1 && "reload".equalsIgnoreCase(args[0])) {
            ConfigManager.reload();
            MessagesConfig.reload();
            ModuleManager.init();
            sender.sendMessage(parse(reloadSuccess()));
        } else {
            sender.sendMessage(parse(reloadUsage()));
        }
        return true;
    }
}
