package com.nomad.langchat;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Enabling Nightgale Languages...");
        System.out.println("NGL is coded by Nomad");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        System.out.println("NGL config enabled...");
        getCommand("dwarvish").setExecutor(new dwarvish(this));
        System.out.println("NGL DWARVISH enabled...");

        System.out.println("NGL Enabled!!!");


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Ember has deemed this plugin shall shutdown... God help us");
    }
}
