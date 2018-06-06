package com.soltys;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable {
    private String artist;
    private String albumName;
    private ArrayList<Song> songList;

    public Album(String artist, String albumName) {
        this.artist = artist;
        this.albumName = albumName;
        this.songList = new ArrayList<>();
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public boolean addSong(String name, double duration) {
        try {
            if (findSong(name) != null) {
                return false;
            } else {
                songList.add(new Song(name, duration));
                return true;
            }
        } catch (NullPointerException e) {
        return false;
        }

    }

    public boolean removeSong(String name) {
        if (findSong(name) == null) {
            return false;
        } else {
            songList.remove(findSong(name));
            return true;
        }
    }

    public Song findSong(String name) {

        for (Song song : songList) {
            if (song.getName().equals(name)) {
                return song;
            }
        }
        return null;

    }
}
