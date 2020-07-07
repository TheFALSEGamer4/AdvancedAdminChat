package com.falsegamer.AdvancedAdminChat;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Consumer;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {

    FileConfiguration config;
    public reloadcmd rc = new reloadcmd(this);
    public ConfigManager cm = new ConfigManager(this);
    public parserClass pc = new parserClass(this);

    public String updateAvailable;
    public String currentVersion;
    public String newVersion;

    @Override
    public void onEnable() {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        console.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        console.sendMessage(ChatColor.BLUE + "Advanced Admin Only");
        console.sendMessage(ChatColor.BLUE + "Version 1.5");
        console.sendMessage(ChatColor.GREEN + "Enabled Yay!!");
        console.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        this.getCommand("ao").setExecutor(new AOCommand(this));
        this.getCommand("aoreload").setExecutor(rc);
        this.config = this.getConfig();
        this.config.options().copyDefaults(true);
        this.saveConfig();
        HttpsURLConnection connection = null;
        try {
            connection = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=81160").openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            newVersion = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger logger = this.getLogger();
        getServer().getPluginManager().registerEvents(this, this);
        currentVersion = this.getDescription().getVersion();


        if (currentVersion.equalsIgnoreCase(String.valueOf(newVersion))) {
            logger.info("There is not a new update available. ");
            updateAvailable = "false";
        } else {
            logger.info("There is a new update available. Current version: " + currentVersion + ", new version: " + newVersion);
            updateAvailable = "true";
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        if(p.hasPermission("adminonly.notify")){
            if(updateAvailable.equals("true")){
                String message = this.cm.updateAvailableMessage;
                message = message.replaceAll("%currentversion%", currentVersion);
                message = message.replaceAll("%newversion%", newVersion);
                message = message.replaceAll("%downloadurl%", "https://www.spigotmc.org/resources/advancedadminchat.81160/");
                p.sendMessage(this.cm.message(message));
            }
        }
    }


    public void onDisable() {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        console.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        console.sendMessage(ChatColor.BLUE + "Advanced Admin Only");
        console.sendMessage(ChatColor.BLUE + "Version 1.5");
        console.sendMessage(ChatColor.RED + "Disabled!");
        console.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
