package zxc.deadinside.sayboom.killadminformoney;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import zxc.deadinside.sayboom.killadminformoney.commands.ReloadCommand;
import zxc.deadinside.sayboom.killadminformoney.config.ConfigManager;
import zxc.deadinside.sayboom.killadminformoney.config.MessagesConfig;
import zxc.deadinside.sayboom.killadminformoney.listeners.KillAdminListener;
import zxc.deadinside.sayboom.killadminformoney.modules.ModuleManager;

import static zxc.deadinside.sayboom.killadminformoney.messages.MessageUtils.parse;

public final class KillAdminForMoney extends JavaPlugin {

    private static KillAdminForMoney instance;

    public static KillAdminForMoney getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        setupConfigs();
        ModuleManager.init();

        registerListeners();
        registerCommands();

        log("&aKillAdminForMoney включён!");
    }

    @Override
    public void onDisable() {
        log("&cKillAdminForMoney выключен!");
    }

    private void setupConfigs() {
        ConfigManager.setup(this);
        MessagesConfig.setup(this);
    }

    private void registerListeners() {
        Bukkit.getPluginManager()
                .registerEvents(new KillAdminListener(), this);
    }

    private void registerCommands() {
        getCommand("kafm")
                .setExecutor(new ReloadCommand());
    }

    private void log(String message) {
        Bukkit.getConsoleSender()
                .sendMessage(parse(message));
    }
}
