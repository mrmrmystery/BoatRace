package de.swc.commands;

import de.swc.commands.subcmds.AddCheckpointCommand;
import de.swc.commands.subcmds.CreateTrackCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BoatRaceCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("boatrace")){
            sender.sendMessage("§cNo permission!");
            return false;
        }

        if(args.length == 0){
            sender.sendMessage("§cPlease specify a subcommand!");
            return false;
        }

        String[] newArgs = new String[args.length - 1];
        System.arraycopy(args, 1, newArgs, 0, args.length - 1);

        switch (args[0]){
            case "createtrack" -> {
                new CreateTrackCommand().onCommand(sender, command, label, newArgs);
            }

            case "addcheckpoint" -> {
                new AddCheckpointCommand().onCommand(sender, command, label, newArgs);
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> tabs = new ArrayList<>();
        if(args.length == 1){
            return List.of("createtrack", "addcheckpoint");
        }else {
            String[] newArgs = new String[args.length - 1];
            System.arraycopy(args, 1, newArgs, 0, args.length - 1);
            switch (args[0]){
                case "addcheckpoint" -> {
                    return new AddCheckpointCommand().onTabComplete(sender, command, label, newArgs);
                }
            }
        }
        return tabs;
    }
}
