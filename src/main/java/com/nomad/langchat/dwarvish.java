package com.nomad.langchat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class dwarvish implements CommandExecutor {

    private final Main main;

    public dwarvish(Main main){
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        //if player exists, is a player and not console then set as sender
        Player player;
        if (sender instanceof Player) {
           player = (Player) sender;


            // if the command has /<arg 1> AND <arg 2>
            if (args.length >= 1) {
                //string builder makes the message arg one arg instead of each word being one
                StringBuilder builder = new StringBuilder();
                for (String arg : args) {
                    builder.append(arg).append(" ");
                }
                //get the player entities around a player (currently set to 10 block radius)
            for (Entity entity : player.getNearbyEntities(10, 10,10) ){
                if (entity instanceof Player) {
                    Player otherplayer = (Player) entity;
                    //check if players have the right permission
                    if(otherplayer.hasPermission("lang.dwarvish")){
                        //if so send this message
                        otherplayer.sendMessage(player.getName() + ChatColor.GREEN + " [Dwarvish] " + ChatColor.WHITE + builder);
                    } else { //swap the string letters by changing the string to an array, swapping some stuff, and then switching back
                        String replaceString = builder.toString();
                        char[] ch = replaceString.toCharArray();
                        for (int i = 0; i < ch.length - 1; i += 2){
                            char temp = ch[i];
                            ch[i] = ch[i + 1];
                            ch[i + 1] = temp;
                        }
                        String scrambled = ch.toString();
                        //send this scrambled message if player does not have the permission
                        otherplayer.sendMessage(player.getName() + ChatColor.GREEN + " [Foreign Language] " + scrambled);
                    }
                }
            }
            } else {
                //error message for wrong santex
                sender.sendMessage(ChatColor.RED + "Invalid usage! Use /dwarvish <message>");
        }
        return false;
    }
        return false;
    }}
