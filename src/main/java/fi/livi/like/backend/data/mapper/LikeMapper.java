package fi.livi.like.backend.data.mapper;

import org.apache.ibatis.annotations.Param;

import fi.livi.like.backend.data.dto.ActivityDto;
import fi.livi.like.backend.data.dto.LocationDto;


/**
 * Mybatis mappers
 */
public interface LikeMapper {
    public long insertLocation(@Param("locationDto") LocationDto location);
    public int insertActivity(@Param("activityDto") ActivityDto activity);
    public String selectUserId(@Param("username") String username, @Param("password") String password);
    public int insertUser(@Param("id") String id, @Param("username") String username, @Param("password") String password);
}
