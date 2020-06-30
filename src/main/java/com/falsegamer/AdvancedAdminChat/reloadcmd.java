package com.falsegamer.AdvancedAdminChat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class reloadcmd implements CommandExecutor {

    private final Main plugin;

    public reloadcmd(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("aoreload") && sender instanceof Player){
            if (player.hasPermission("adminonly.reload")) {
                this.plugin.cm.reload();
                sender.sendMessage(this.plugin.cm.message(this.plugin.cm.reloadMessage));
            }else {
                player.sendMessage(this.plugin.cm.cc(this.plugin.cm.noPermission));
            }
        }
        return true;
    }
}
