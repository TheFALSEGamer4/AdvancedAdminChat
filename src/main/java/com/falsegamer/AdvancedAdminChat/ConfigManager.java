package com.falsegamer.AdvancedAdminChat;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private final Plugin plugin;
    private FileConfiguration config;
    public String prefix;
    public String reloadMessage;
    public String invalidMessage;
    public String AOMessage;

    public ConfigManager(Plugin plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        this.config = plugin.getConfig();
        this.reloadMessages();
    }

    public void reload() {
        plugin.saveResource("config.yml", false);
        plugin.reloadConfig();
        config = plugin.getConfig();
        this.prefix = this.cc(this.config.getString("AdminOnly.Prefix"));
        this.reloadMessage = this.cc(this.config.getString("AdminOnly.ReloadMessage"));
        this.invalidMessage = this.cc(this.config.getString("AdminOnly.InvalidUsageMessage"));
        this.AOMessage = this.cc(this.config.getString("AdminOnly.Message"));
        this.reloadMessages();
    }

    public void reloadMessages() {
        plugin.saveResource("config.yml", false);
        plugin.reloadConfig();
        config = plugin.getConfig();
        this.prefix = this.cc(this.config.getString("AdminOnly.Prefix"));
        this.reloadMessage = this.cc(this.config.getString("AdminOnly.ReloadMessage"));
        this.invalidMessage = this.cc(this.config.getString("AdminOnly.InvalidUsageMessage"));
        this.AOMessage = this.cc(this.config.getString("AdminOnly.Message"));
    }


    public String message(String message) {
        return this.prefix + " " + this.cc(message);
    }

    public String cc(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
