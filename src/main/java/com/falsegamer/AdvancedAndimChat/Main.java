package com.falsegamer.AdvancedAdminChat;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    FileConfiguration config;
    File cfile;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(this, this);
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        console.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        console.sendMessage(ChatColor.BLUE + "Advanced Admin Only");
        console.sendMessage(ChatColor.BLUE + "Version 1.0");
        console.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        this.getCommand("ao").setExecutor(new AOCommand(this));
        this.getCommand("aoreload").setExecutor(new reloadcmd(this));
        this.config = this.getConfig();
        this.config.options().copyDefaults(true);
        this.saveConfig();
        this.cfile = new File(this.getDataFolder(), "config.yml");
    }
    public ConfigManager getConfigManager() {
        return configManager;
    }



    public void onDisable() {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        console.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        console.sendMessage(ChatColor.BLUE + "Advanced Admin Only");
        console.sendMessage(ChatColor.BLUE + "Version 1.0");
        console.sendMessage(ChatColor.RED + "Disabled!");
        console.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
