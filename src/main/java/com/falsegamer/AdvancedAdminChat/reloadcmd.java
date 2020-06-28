package com.falsegamer.AdvancedAdminChat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class reloadcmd implements CommandExecutor {
    public Main main;
    private FileConfiguration config;

    private final Main plugin;
    public reloadcmd(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("aoreload")){
            plugin.getConfigManager().reload();
            sender.sendMessage(plugin.getConfigManager().getMessage("Message"));
        }
        return true;
    }
}
