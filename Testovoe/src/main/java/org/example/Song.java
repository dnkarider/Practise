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
        if(track_name != null) {
            return track_name;
        } else throw new NullPointerException("track_name is null");
    }
    public String getArtist() {
        if(artist != null) {
            return artist;
        } else throw new NullPointerException("artist is null");
    }
    public int getDuration() {
        if(duration != 0) {
            return duration;
        } else throw new NullPointerException("duration is null");
    }
}
