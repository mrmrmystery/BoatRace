package de.swc.commands.subcmds;

import com.fastasyncworldedit.core.FAWEPlatformAdapterImpl;
import com.fastasyncworldedit.core.FaweAPI;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.internal.annotation.Selection;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import de.swc.Main;
import de.swc.game.Checkpoint;
import de.swc.game.Track;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AddCheckpointCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("boatrace.addcheckpoint")){
            sender.sendMessage("§cNo permission!");
            return false;
        }

        if(!(sender instanceof Player player)){
            sender.sendMessage("§cOnly players can use this command!");
            return false;
        }

        if(args.length == 0){
            sender.sendMessage("§cPlease specify a track name!");
            return false;
        }


        if(WorldEdit.getInstance().getSessionManager().findByName(player.getName()).getSelection(BukkitAdapter.adapt(player.getWorld())) == null){
            sender.sendMessage("§cPlease select a region with worldedit first!");
            return false;
        }

        Region region = WorldEdit.getInstance().getSessionManager().findByName(player.getName()).getSelection();

        Track track = Main.getDb().sql("SELECT * FROM track WHERE name = ?", args[0]).first(Track.class);

        Checkpoint checkpoint = new Checkpoint(
                BukkitAdapter.adapt(player.getWorld(), region.getMaximumPoint()),
                BukkitAdapter.adapt(player.getWorld(), region.getMinimumPoint()),
                Checkpoint.Type.CHECKPOINT
        );

        track.addCheckpoint(checkpoint);

        Main.getDb().update(track);

        sender.sendMessage("§aSuccessfully added checkpoint to %s!".formatted(args[0]));



        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1){
            return Main.getDb().sql("SELECT name FROM track").results(String.class);
        }
        return null;
    }
}
