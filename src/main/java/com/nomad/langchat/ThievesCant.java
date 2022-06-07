package com.nomad.langchat;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class ThievesCant implements CommandExecutor {


    private final Main main;

    public ThievesCant(Main main){
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player;
        if (sender instanceof Player) {
            player = (Player) sender;

            if(main.getConfig().getBoolean("Settings.Thieves Cant.Enabled")) {
                // if the command has /<arg 1> AND <arg 2>
                if (args.length >= 1) {
                    //string builder makes the message arg one arg instead of each word being one
                    StringBuilder builder = new StringBuilder();
                    for (String arg : args) {
                        builder.append(arg).append(" ");
                    }
                    //get the player entities around a player (currently set to 10 block radius)
                    for (Entity entity : player.getNearbyEntities(main.getConfig().getInt("Settings.Thieves Cant.Distance"), main.getConfig().getInt("Settings.Thieves Cant.Distance"),main.getConfig().getInt("Settings.Thieves Cant.Distance")) ){
                        if (entity instanceof Player) {
                            Player otherplayer = (Player) entity;
                            //check if players have the right permission
                            if(otherplayer.hasPermission(main.getConfig().getString("Settings.Thieves Cant.Permission"))){
                                //if so send this message
                                otherplayer.sendMessage(player.getName() + ChatColor.GREEN + " [Thieves Cant] " + ChatColor.WHITE + builder);
                            } else{
                                //send this scrambled message if player does not have the permission
                                otherplayer.sendMessage(ChatColor.GREEN + " [Perception] " + ChatColor.WHITE + "You feel something is off");
                            }
                        } else {
                            player.sendMessage(ChatColor.GREEN + "There is no one around...");
                        }
                        //kickback message to original sender
                        player.sendMessage(player.getName() + ChatColor.GREEN + " [Thieves Cant] " + ChatColor.WHITE + builder);
                    }
                } else {
                    //error message for wrong santex
                    sender.sendMessage(ChatColor.RED + "Invalid usage! Use /ThievesCant <message> OR /th <message>");
                }
            } else {
                player.sendMessage("Thieves Cant is not enabled in the config");
            }
            return false;
        }

        return false;
    }
}
