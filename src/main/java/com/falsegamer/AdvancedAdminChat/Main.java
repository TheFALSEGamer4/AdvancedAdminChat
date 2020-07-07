package com.falsegamer.AdvancedAdminChat;

import java.io.File;
import java.util.logging.Logger;

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

public class Main extends JavaPlugin implements Listener {

    FileConfiguration config;
    File cfile;
    private ConfigManager configManager;
    public reloadcmd rc = new reloadcmd(this);
    public ConfigManager cm = new ConfigManager(this);
    public parserClass pc = new parserClass(this);

    public String updateAvailable = "false";

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        console.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        console.sendMessage(ChatColor.BLUE + "Advanced Admin Only");
        console.sendMessage(ChatColor.BLUE + "Version 1.5");
        console.sendMessage(ChatColor.GREEN + "Enabled Yay!!");
        console.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        this.getCommand("ao").setExecutor(new AOCommand(this));
        this.getCommand("aoreload").setExecutor(new reloadcmd(this));
        this.config = this.getConfig();
        this.config.options().copyDefaults(true);
        this.saveConfig();
        this.cfile = new File(this.getDataFolder(), "config.yml");

        Logger logger = this.getLogger();
        getServer().getPluginManager().registerEvents(this, this);
        new UpdateChecker(this, 81160).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("There is not a new update available.");
                updateAvailable = "false";
            } else {
                logger.info("There is a new update available.");
                updateAvailable = "true";
            }
        });
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(PlayerJoinEvent event) {

        Player p = event.getPlayer();
        if (p.hasPermission("adminonly.notify")) {
            if (updateAvailable.equals("true")) {
                p.sendMessage(this.cm.message("&2Update found, go download it at https://www.spigotmc.org/resources/advancedadminchat.81160/"));
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
