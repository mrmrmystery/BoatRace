package de.swc.game;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Table(name = "track")
public class Track {

    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    public long id;

    private String name;
    private ArrayList<Checkpoint> checkpoints;

    public Track(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCheckpoint(Checkpoint checkpoint){
        checkpoints.add(checkpoint);
    }
}
