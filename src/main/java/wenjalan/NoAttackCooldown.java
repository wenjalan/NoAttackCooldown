package wenjalan;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import wenjalan.commands.SetAttackSpeedCommand;
import wenjalan.listeners.NoAttackCooldownListener;

public class NoAttackCooldown extends JavaPlugin {

    // minecraft's default attack speed
    // see https://minecraft.gamepedia.com/Attribute
    public static final float VANILLA_ATTACK_SPEED = 4.0f;

    // the maximum attack speed allowable
    public static final float MAX_ATTACK_SPEED = 1024.0f;

    // the attack cooldown to set to
    public static float attackCooldown = MAX_ATTACK_SPEED;

    @Override
    public void onEnable() {
        // attach event listeners
        // NoAttackCooldownListener.java
        this.getServer().getPluginManager().registerEvents(new NoAttackCooldownListener(), this);

        // attach command executors
        this.getCommand("setattackspeed").setExecutor(new SetAttackSpeedCommand());

        // set the Attack Cooldown of all players currently online
        setAllOnlinePlayersAttackSpeed(attackCooldown);
    }

    @Override
    public void onDisable() {
        // reset the Attack Cooldown of all players currently online to the default
        setAllOnlinePlayersAttackSpeed(NoAttackCooldown.attackCooldown);
    }

    // sets the attack speed of all online players
    public static void setAllOnlinePlayersAttackSpeed(float amount) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            setAttackCooldown(p, amount);
        }
    }

    // sets the attack cooldown of a player
    // p: the Player to set the Attack Speed attribute of
    // amount: the amount
    public static void setAttackCooldown(Player p, float amount) {
        AttributeInstance i = p.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        if (i != null) {
            i.setBaseValue(NoAttackCooldown.attackCooldown);
        }
        else {
            System.err.println("Couldn't set the attack speed of player " + p.getDisplayName());
        }
    }

}
