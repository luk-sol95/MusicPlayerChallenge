package com.soltys;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MusicPlayer implements Serializable {

    private LinkedList<Song> playList;
    private ArrayList<Album> albumList;
    private String ownerName;


    public MusicPlayer(String ownerName) {
        this.ownerName = ownerName;
        albumList = new ArrayList<>();
        playList = new LinkedList<>();


    }

    public String getOwnerName() {
        return ownerName;
    }

    public boolean addSong(String artist, String albumName, String songName, double duration) {
        Album album = findAlbum(artist, albumName);


        if (album != null) {
            return album.addSong(songName, duration);
        } else return false;


    }

    private Album findAlbum(String artist, String albumName) {
        for (Album album : albumList) {
            if (album.getAlbumName().equals(albumName) && album.getArtist().equals(artist)) {
                return album;
            }

        }
        return null;
    }

    public boolean addAlbum(String artist, String albumName) {
        if (findAlbum(artist, albumName) == null) {
            albumList.add(new Album(artist, albumName));
            return true;
        } else {
            return false;
        }
    }

    public boolean addSongToPlaylist(String artist, String albumName, String songName) {
        Album album = findAlbum(artist, albumName);
        if (album == null) {
            return false;
        } else if (album.findSong(songName) == null) {
            return false;
        } else {
            playList.add(album.findSong(songName));
            return true;

        }
    }

    public void printPlaylist() {
        for (Song song : playList
                ) {
            System.out.format(song.toString() + "\n");

        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean forward = true;
        boolean isQuit = false;
        ListIterator<Song> iterator = playList.listIterator();

        System.out.println("What would you like to do?\n" +
                "1. Play next song.\n" +
                "2. Play previous song.\n" +
                "3. Repeat current song.\n" +
                "4. Remove current song from playlist.\n" +
                "5. Print playlist.\n" +
                "0. Print list of commands again.\n" +
                "99. Quit.");

        while (!isQuit) {
            int action;

            try {
                action = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entered wrong number, try again.");
                scanner.nextLine();
                continue;
            }
            switch (action) {
                case 1:
                    if (iterator.hasNext()) {

                        if (!forward) {
                            iterator.next();
                            forward = true;
                        }
                        System.out.println("Now playing " + iterator.next());

                    } else {
                        System.out.println("You have reached end of the playlist.");
                    }

                    break;
                case 2:
                    if (iterator.hasPrevious()) {
                        if (forward) {
                            iterator.previous();
                            forward = false;
                        }
                        System.out.println("Now playing " + iterator.previous());


                    } else {
                        System.out.println("You have reached beginning of the playlist.");
                    }

                    break;
                case 3:
                    if (forward) {
                        System.out.println("Now playing " + iterator.previous());
                        forward = false;
                    } else {
                        System.out.println("Now playing " + iterator.next());
                        forward = true;
                    }
                    break;
                case 4:
                    if (forward) {

                        System.out.println("Removed " + iterator.previous());
                       iterator.remove();
                    } else {
                        System.out.println("Removed " + iterator.next());
                        iterator.remove();

                    }
                    break;
                case 5:
                    printPlaylist();
                    break;
                case 0:
                    System.out.println("What would you like to do?" +
                            "1. Play next song." +
                            "2. Play previous song." +
                            "3. Repeat current song." +
                            "4. Remove current song from playlist." +
                            "0. Print list of commands again." +
                            "99. Quit.");
                    break;
                case 99:
                    save();
                    isQuit = true;
                    break;
            }

        }

    }

    private void save() {
        Path playerPath = FileSystems.getDefault().getPath(ownerName + ".dat");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(playerPath)))) {
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }


}
