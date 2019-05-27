package wenjalan.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import wenjalan.NoAttackCooldown;

public class SetAttackSpeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            if (strings.length < 1) {
                return false;
            }

            // catch the number they want
            Float newAttackSpeed = Float.parseFloat(strings[0]);

            // check it
            if (newAttackSpeed < 0 || newAttackSpeed > NoAttackCooldown.MAX_ATTACK_SPEED) {
                throw new IllegalArgumentException();
            }

            // set the new Attack Speed
            NoAttackCooldown.attackCooldown = newAttackSpeed;

            // set everyone's attack speed
            NoAttackCooldown.setAllOnlinePlayersAttackSpeed(NoAttackCooldown.attackCooldown);

            // return
            return true;
        } catch (IllegalArgumentException e) {
            commandSender.sendMessage("Invalid argument: " + strings[0]);
            return false;
        }
    }

}
