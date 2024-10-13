
CREATE TABLE songs(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    track_name text not null,
    artist text not null,
    duration int not null
);

INSERT INTO songs(track_name, artist, duration)
 VALUES ('aleksa', 'alexin', 10),
        ('lubi', 'ptaha', 15);

SELECT * FROM songs;
