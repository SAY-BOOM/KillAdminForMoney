package zxc.deadinside.sayboom.killadminformoney.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public final class ConfigManager {
    private static JavaPlugin plugin;
    private static FileConfiguration cfg;
    private static final String MOD = "modules.";

    public static void setup(JavaPlugin pl) {
        plugin = pl;
        pl.saveResource("config.yml", false);
        reload();
    }

    public static void reload() {
        cfg = YamlConfiguration.loadConfiguration(
                new File(plugin.getDataFolder(), "config.yml")
        );
    }

    public static List<String> getGiveCommands() {
        return cfg.getStringList("give-commands");
    }

    public static List<String> getNicknames() {
        return cfg.getStringList("nicknames");
    }

    public static List<String> getGroups() {
        return cfg.getStringList("groups");
    }

    public static List<String> getSounds() {
        return cfg.getStringList("sound");
    }

    public static boolean isNicknameModuleEnabled()  { return cfg.getBoolean(MOD + "nickname"); }
    public static boolean isGroupModuleEnabled()     { return cfg.getBoolean(MOD + "group"); }
    public static boolean isSoundEnabled()            { return cfg.getBoolean(MOD + "sound"); }
    public static boolean isActionbarEnabled()        { return cfg.getBoolean(MOD + "actionbar"); }
    public static boolean isTitleEnabled()            { return cfg.getBoolean(MOD + "title"); }
    public static boolean isChatEnabled()             { return cfg.getBoolean(MOD + "chat"); }
}
