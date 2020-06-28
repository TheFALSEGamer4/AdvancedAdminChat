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
    private final Map<String, String> messages = new HashMap<>();

    public ConfigManager(Plugin plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        this.config = plugin.getConfig();
        reload();
        reloadMessages();
    }

    public void reload() {
        plugin.saveResource("config.yml", false);
        plugin.reloadConfig();
        config = plugin.getConfig();
        reloadMessages();
    }

    public void reloadMessages() {
        messages.clear();
        ConfigurationSection messageSection = config.getConfigurationSection("AdminOnly.ReloadMessage");
        if (messageSection == null)
            return;
        for (Map.Entry<String, Object> message : messageSection.getValues(false).entrySet()) {
            try {
                messages.put(message.getKey(), cc((String) message.getValue()));
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
    }

    public String getMessage(String key) {
        return messages.getOrDefault(key, "Unknown message '" + key + "'");
    }

    private String cc(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
