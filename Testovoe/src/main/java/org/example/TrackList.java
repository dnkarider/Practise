package org.example;

import java.util.List;

public class TrackList {
    private List<Song> data;

    private void setData(List<Song> data) {
        this.data = data;
    }

    public List<Song> getData() {
        return data;
    }
}
