package zxc.deadinside.sayboom.killadminformoney.modules.group;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.entity.Player;
import zxc.deadinside.sayboom.killadminformoney.config.ConfigManager;
import zxc.deadinside.sayboom.killadminformoney.modules.Module;

import java.util.Collection;

public class GroupModule implements Module {
    @Override
    public boolean isAdmin(Player player) {
        User user = LuckPermsProvider.get()
                .getUserManager()
                .getUser(player.getUniqueId());
        if (user == null) {
            return false;
        }
        Collection<String> allowed = ConfigManager.getGroups();
        if (allowed.contains(user.getPrimaryGroup())) {
            return true;
        }
        return user.getNodes().stream()
                .filter(node -> node instanceof InheritanceNode)
                .map(node -> ((InheritanceNode) node).getGroupName())
                .anyMatch(allowed::contains);
    }
}
