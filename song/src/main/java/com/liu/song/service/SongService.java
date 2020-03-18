package com.liu.song.service;

import com.liu.song.entity.Song;

import java.util.List;

public interface SongService {

    String getSongNameById(long id);

    List<Song> getSongNameByAlbumId(long albumId);

    void insertSong(long singerId, long albumId, String name, String backgroundPicture,
                    String lyric);

    Long insertSong(long singerId, String songName, String backgroundPicture,
                    String lyric);

    List<Song> getSongsByAlbumId(Long albumId);
}
