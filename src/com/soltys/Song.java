package com.soltys;

import java.io.Serializable;

public class Song implements Serializable {

    private String name;
    private double duration;

    public double getDuration() {
        return duration;
    }

    public Song(String name, double duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ": " + duration;
    }
}
