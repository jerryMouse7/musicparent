package com.liu.song.mapper;

import com.liu.song.entity.Song;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.search.SearchTerm;
import java.util.List;
import java.util.Set;

@Mapper
public interface SongMapper {

    Long insertSong(@Param("song") Song song);

    String getSongNameById(@Param("id")long id);

    List<String> getSongNameByIds(@Param("ids") Set<Long> ids);

    List<String> selectSongNameByAlbumId(@Param("albumId") long albumId);
}
