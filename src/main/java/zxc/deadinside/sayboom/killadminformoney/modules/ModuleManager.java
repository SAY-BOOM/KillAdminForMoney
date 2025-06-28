package zxc.deadinside.sayboom.killadminformoney.modules;

import org.bukkit.entity.Player;
import zxc.deadinside.sayboom.killadminformoney.config.ConfigManager;
import zxc.deadinside.sayboom.killadminformoney.modules.group.GroupModule;
import zxc.deadinside.sayboom.killadminformoney.modules.nickname.NicknameModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModuleManager {
    private static List<Module> modules = Collections.emptyList();

    public static void init() {
        List<Module> loaded = new ArrayList<>(2);
        if (ConfigManager.isNicknameModuleEnabled()) {
            loaded.add(new NicknameModule());
        }
        if (ConfigManager.isGroupModuleEnabled()) {
            loaded.add(new GroupModule());
        }
        modules = Collections.unmodifiableList(loaded);
    }

    public static boolean isAdmin(Player player) {
        for (Module module : modules) {
            if (module.isAdmin(player)) {
                return true;
            }
        }
        return false;
    }
}
