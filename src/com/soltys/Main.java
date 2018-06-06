package com.soltys;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        MusicPlayer defaultPlayer = new MusicPlayer("default");
//        defaultPlayer.addAlbum("Judas Priest", "Painkiller");
//        defaultPlayer.addSong("Judas Priest", "Painkiller", "Painkiller", 6.06);
//        defaultPlayer.addSong("Judas Priest", "Painkiller", "Hell Patrol", 3.37);
//        defaultPlayer.addSong("Judas Priest", "Painkiller", "All Guns Blazing", 3.57);
//        defaultPlayer.addSong("Judas Priest", "Painkiller", "Leather Rebel", 3.35);
//        defaultPlayer.addSong("Judas Priest", "Painkiller", "Metal Meltdown", 4.50);
//        defaultPlayer.addSong("Judas Priest", "Painkiller", "Night Crawler", 5.45);
//        defaultPlayer.addSong("Judas Priest", "Painkiller", "Between Hammer and the Anvil", 4.49);
//        defaultPlayer.addSong("Judas Priest", "Painkiller", "A touch of Evil", 5.45);
//        defaultPlayer.addSong("Judas Priest", "Painkiller", "Battle Hymn", 0.56);
//        defaultPlayer.addSong("Judas Priest", "Painkiller", "One Shot at Glory", 6.48);
//
//        defaultPlayer.addAlbum("AC/DC", "Black Ice");
//        defaultPlayer.addSong("AC/DC", "Black Ice", "Rock'n'Roll Train", 4.22);
//        defaultPlayer.addSong("AC/DC", "Black Ice", "Skies on Fire", 3.34);
//        defaultPlayer.addSong("AC/DC", "Black Ice", "Big Jack", 3.57);
//        defaultPlayer.addSong("AC/DC", "Black Ice", "Anything Goes", 3.22);
//        defaultPlayer.addSong("AC/DC", "Black Ice", "War Machine", 3.10);
//        defaultPlayer.addSong("AC/DC", "Black Ice", "Smash 'n' Grab", 4.06);
//        defaultPlayer.addSong("AC/DC", "Black Ice", "Spoilin' for a Fight", 3.17);
//        defaultPlayer.addSong("AC/DC", "Black Ice", "Wheels", 3.28);
//        defaultPlayer.addSong("AC/DC", "Black Ice", "Decibel", 3.34);
//        defaultPlayer.addSong("AC/DC", "Black Ice", "Stormy May Day", 3.10);
//        defaultPlayer.addSong("AC/DC", "Black Ice", "She Likes Rock'n'Roll", 3.53);
//        defaultPlayer.addSong("AC/DC", "Black Ice", "Money Made", 4.15);
//        defaultPlayer.addSong("AC/DC", "Black Ice", "Rock'n'Roll Dream", 4.41);
//        defaultPlayer.addSong("AC/DC", "Black Ice", "Rocking All the Way", 3.22);
//        defaultPlayer.addSong("AC/DC", "Black Ice", "Black Ice", 3.25);
//
//        defaultPlayer.addAlbum("Pantera", "Reinventing the Steel");
//
//        defaultPlayer.addSong("Pantera", "Reinventing the Steel", "Hellbound", 2.42);
//        defaultPlayer.addSong("Pantera", "Reinventing the Steel", "Goddamn Electric", 4.57);
//        defaultPlayer.addSong("Pantera", "Reinventing the Steel", "Yesterday Don't Mean Shit", 4.20);
//        defaultPlayer.addSong("Pantera", "Reinventing the Steel", "You've Gotta Belong To It", 4.13);
//        defaultPlayer.addSong("Pantera", "Reinventing the Steel", "Revolution Is My Name", 5.16);
//        defaultPlayer.addSong("Pantera", "Reinventing the Steel", "Death Rattle", 3.18);
//        defaultPlayer.addSong("Pantera", "Reinventing the Steel", "We'll Grind That Axe For A Long Time", 3.45);
//        defaultPlayer.addSong("Pantera", "Reinventing the Steel", "Up Lift", 3.46);
//        defaultPlayer.addSong("Pantera", "Reinventing the Steel", "It Makes Them Disappear", 6.22);
//        defaultPlayer.addSong("Pantera", "Reinventing the Steel", "I'll Cast A Shadow", 5.22);
//
//        defaultPlayer.addSongToPlaylist("Pantera", "Reinventing the Steel", "Up Lift");
//        defaultPlayer.addSongToPlaylist("Judas Priest", "Painkiller", "Battle Hymn");
//        defaultPlayer.addSongToPlaylist("Pantera", "Reinventing the Steel", "Goddamn Electric");
//        defaultPlayer.addSongToPlaylist("AC/DC", "Black Ice", "Wheels");
//        defaultPlayer.addSongToPlaylist("Pantera", "Reinventing the Steel", "Hellbound");
//        defaultPlayer.addSongToPlaylist("Judas Priest", "Painkiller", "Night Crawler");
//        defaultPlayer.addSongToPlaylist("Judas Priest", "Painkiller", "A touch of Evil");
//        defaultPlayer.addSongToPlaylist("AC/DC", "Black Ice", "Black Ice");
//
//        defaultPlayer.play();

        powerOn();

    }


    public static void powerOn() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name (enter default to power on default music player): ");
        String ownerName = scanner.nextLine();
        MusicPlayer desiredPlayer = load(ownerName);
        if (desiredPlayer == null) {
            System.out.println("Couldn't find your music player.");
        } else {
            desiredPlayer.play();
        }
    }


    public static MusicPlayer load(String ownerName) {
        Path musicPlayerPath = FileSystems.getDefault().getPath(ownerName + ".dat");
        try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(musicPlayerPath)))) {
            return (MusicPlayer) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
