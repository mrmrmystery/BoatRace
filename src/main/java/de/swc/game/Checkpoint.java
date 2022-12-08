package de.swc.game;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "checkpoint")
public class Checkpoint {

    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    public long id;

    private Type type;
    private int x;
    private int y;
    private int z;
    private int x2;
    private int y2;
    private int z2;

    public static enum Type {
        START,
        END,
        CHECKPOINT
    }

    public Checkpoint(Location from, Location to, Type type){
        x = from.getBlockX();
        y = from.getBlockY();
        z = from.getBlockZ();
        x2 = to.getBlockX();
        y2 = to.getBlockY();
        z2 = to.getBlockZ();
        this.type = type;
    }

}
