package com.nomad.langchat;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Enabling Nightgale Languages...");
        getLogger().info("NGL is coded by Nomad");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getLogger().info("Checking config for errors...");
        if (getConfig().getString("Settings.Dwarvish.Permission") != null){
            if (getConfig().getString("Settings.Elvish.Permission") != null){
               if (getConfig().getString("Settings.Thieves Cant.Permission") != null) {
                   getLogger().info("NGL config enabled...");
               } else {
                   Bukkit.getPluginManager().disablePlugin(this);
                   getLogger().info("Thieves Cant has no permission set in the config");
               }
            } else {
                Bukkit.getPluginManager().disablePlugin(this);
                getLogger().info("Elvish has no permission set in the config");
            }
        } else {
            Bukkit.getPluginManager().disablePlugin(this);
            getLogger().info("Dwarvish has no permission set in the config");
        }

        getCommand("dwarvish").setExecutor(new dwarvish(this));
        if (getConfig().getBoolean("Settings.Dwarvish.Enabled")) {
            getLogger().info("DWARVISH is enabled...");
        } else {
            getLogger().info("Dwarvish is disabled...");
        }
        getCommand("elvish").setExecutor(new elvish(this));
        if (getConfig().getBoolean("Settings.Elvish.Enabled")) {
            getLogger().info("Elvish is enabled...");
        } else {
            getLogger().info("Elvish is disabled...");
        }
        getCommand("ThievesCant").setExecutor(new ThievesCant(this));
        if (getConfig().getBoolean("Settings.Thieves Cant.Enabled")) {
            getLogger().info("Thieves Cant is enabled...");
        } else {
            getLogger().info("Thieves Cant is disabled...");
        }

        getLogger().info("NGL Enabled!!!");


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Ember has deemed this plugin shall shutdown... God help us");
    }
}
