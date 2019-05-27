package wenjalan.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import wenjalan.NoAttackCooldown;

// listens for events related to player states
public class NoAttackCooldownListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        // set their attack cooldown to whatever we're setting it to
        NoAttackCooldown.setAttackCooldown(e.getPlayer(), NoAttackCooldown.attackCooldown);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        // set their attack cooldown to vanilla
        NoAttackCooldown.setAttackCooldown(e.getPlayer(), NoAttackCooldown.VANILLA_ATTACK_SPEED);
    }

}
