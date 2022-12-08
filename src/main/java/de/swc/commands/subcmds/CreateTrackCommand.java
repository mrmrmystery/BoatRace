package de.swc.commands.subcmds;

import de.swc.Main;
import de.swc.game.Track;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CreateTrackCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("boatrace.createtrack")){
            sender.sendMessage("§cNo permission!");
            return false;
        }

        if(args.length == 0){
            sender.sendMessage("§cPlease specify a track name!");
            return false;
        }

        String trackName = args[0];

        Track track = new Track(trackName);
        Main.getDb().insert(track);

        return false;
    }
}
