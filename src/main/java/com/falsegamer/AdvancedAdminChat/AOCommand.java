package com.falsegamer.AdvancedAdminChat;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AOCommand implements CommandExecutor {

    private final Main plugin;

    public AOCommand(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ao") && sender instanceof Player) {

            Player player = (Player)sender;

            if (player.hasPermission("adminonly.chat")) {

                String message;
                String pworld = this.plugin.pc.worldParser(player.getWorld().toString());


                if (args.length > 0) {

                    message = StringUtils.join(args, ' ', 0, args.length);
                    String ChatMessage = this.plugin.cm.AOMessage;

                    ChatMessage = ChatMessage.replaceAll("%player%", player.getName());
                    ChatMessage = ChatMessage.replaceAll("%message%", message);
                    ChatMessage = ChatMessage.replaceAll("%playerworld%", pworld);

                    Bukkit.broadcast(this.plugin.cm.message(ChatMessage), "adminonly.chat");
                } else {
                    player.sendMessage(this.plugin.cm.message(this.plugin.cm.invalidMessage));
                }
            } else {
                String playermessage = StringUtils.join(args, ' ', 0, args.length);
                player.sendMessage(this.plugin.cm.cc(this.plugin.cm.noPermission));
                String message = this.plugin.cm.NoPermissionButTried;
                message = message.replaceAll("%player%", sender.getName());
                message = message.replaceAll("%message%", playermessage);
                Bukkit.broadcast(this.plugin.cm.message(message), "adminonly.denied");
            }
        }
        return true;
    }



}
