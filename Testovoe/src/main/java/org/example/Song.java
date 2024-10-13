package org.example;

public class Song {
    private String track_name;
    private String artist;
    private int duration;

    public void setTrack_name(String track_name) {
        this.track_name = track_name;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getTrack_name() {
        return track_name;
    }
    public String getArtist() {
        return artist;
    }
    public int getDuration() {
        return duration;
    }
}
