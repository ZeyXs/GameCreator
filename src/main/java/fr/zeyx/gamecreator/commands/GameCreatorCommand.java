package fr.zeyx.gamecreator.commands;

import fr.zeyx.gamecreator.GameCreator;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("ALL")
public class GameCreatorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0)  {
                player.sendMessage("§cWrong usage: /gcreator help");
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "help" -> {
                    player.sendMessage("§6Help menu:");
                    player.sendMessage("§7- /gcreator give <function_block>");
                }
                case "give" -> {
                    if (args.length < 2) player.sendMessage("§cWrong usage: /gcreator give <function_block>");
                    else player.sendMessage("§5\\o/");
                }
                case "debug" -> {
                    player.sendMessage("debug");
                }
            }
        }

        return false;
    }

}
