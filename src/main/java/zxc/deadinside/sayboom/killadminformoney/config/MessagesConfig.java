package zxc.deadinside.sayboom.killadminformoney.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class MessagesConfig {
    private static JavaPlugin plugin;
    private static FileConfiguration cfg;
    private static final String PREFIX = "messages.";

    public static void setup(JavaPlugin pl) {
        plugin = pl;
        pl.saveResource("messages.yml", false);
        reload();
    }

    public static void reload() {
        cfg = YamlConfiguration.loadConfiguration(
                new File(plugin.getDataFolder(), "messages.yml")
        );
    }

    private static String get(String key) {
        return cfg.getString(PREFIX + key, "");
    }

    public static String noPermission() {
        return get("no-permission");
    }

    public static String reloadSuccess() {
        return get("reload-success");
    }

    public static String reloadUsage() {
        return get("reload-usage");
    }

    public static String killAdminChat(String victim) {
        return get("kill-admin-success.chat")
                .replace("{victim}", victim);
    }

    public static String killAdminActionbar(String victim) {
        return get("kill-admin-success.actionbar")
                .replace("{victim}", victim);
    }

    public static String killAdminTitle(String victim) {
        return get("kill-admin-success.title")
                .replace("{victim}", victim);
    }
}
