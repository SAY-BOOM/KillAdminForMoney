package zxc.deadinside.sayboom.killadminformoney.modules.nickname;

import org.bukkit.entity.Player;
import zxc.deadinside.sayboom.killadminformoney.config.ConfigManager;
import zxc.deadinside.sayboom.killadminformoney.modules.Module;

public class NicknameModule implements Module {
    @Override
    public boolean isAdmin(Player p) {
        return ConfigManager.getNicknames().contains(p.getName());
    }
}
