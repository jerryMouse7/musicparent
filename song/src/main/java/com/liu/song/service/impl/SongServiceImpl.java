package com.liu.song.service.impl;

import com.liu.song.entity.Song;
import com.liu.song.mapper.SongMapper;
import com.liu.song.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Override
    public String getSongNameById(long id) {
        return songMapper.getSongNameById(id);
    }

    @Override
    public List<Song> getSongNameByAlbumId(long albumId) {
        return null;
    }

    @Override
    public void insertSong(long singerId, long albumId, String songName, String backgroundPicture, String lyric) {

    }

    @Override
    public Long insertSong(long singerId, String songName, String backgroundPicture, String lyric) {
        Song song = new Song();
        song.setSingerId(singerId);
        song.setName(songName);
        song.setBackgroundPicture(backgroundPicture);
        song.setLyric(lyric);
        song.setCreateTime(LocalDateTime.now());
        return songMapper.insertSong(song);
    }

    @Override
    public List<Song> getSongsByAlbumId(Long albumId) {
        return null;
    }

}
