package zxc.deadinside.sayboom.killadminformoney.listeners;

import static zxc.deadinside.sayboom.killadminformoney.config.ConfigManager.*;
import static zxc.deadinside.sayboom.killadminformoney.messages.MessageUtils.parse;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import zxc.deadinside.sayboom.killadminformoney.config.MessagesConfig;
import zxc.deadinside.sayboom.killadminformoney.modules.ModuleManager;

public class KillAdminListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player victim = e.getEntity();
        Player killer = victim.getKiller();
        if (killer == null || !ModuleManager.isAdmin(victim)) return;

        getGiveCommands().forEach(cmd ->
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                        cmd.replace("{player}", killer.getName()))
        );

        if (isSoundEnabled())
            getSounds().forEach(snd -> {
                try {
                    killer.playSound(killer.getLocation(),
                            Sound.valueOf(snd), 1f, 1f);
                } catch (IllegalArgumentException ex) {
                    killer.sendMessage(parse(
                            "&cОшибка в config.yml: звук " + snd + " не найден"));
                }
            });

        if (isChatEnabled())
            killer.sendMessage(parse(
                    MessagesConfig.killAdminChat(victim.getName())));

        if (isActionbarEnabled())
            killer.spigot().sendMessage(
                    ChatMessageType.ACTION_BAR,
                    new TextComponent(parse(
                            MessagesConfig.killAdminActionbar(victim.getName()))));

        if (isTitleEnabled())
            killer.sendTitle(
                    parse(MessagesConfig.killAdminTitle(victim.getName())),
                    "", 10, 70, 20
            );
    }
}
