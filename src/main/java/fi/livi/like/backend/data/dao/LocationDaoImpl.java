package fi.livi.like.backend.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fi.livi.like.backend.data.dto.ActivityDto;
import fi.livi.like.backend.data.dto.LocationDto;
import fi.livi.like.backend.data.mapper.LikeMapper;

@Component
public class LocationDaoImpl implements LocationDao {

    private final LikeMapper mapper;
    
    @Autowired
    public LocationDaoImpl(LikeMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public LocationDto addLocation(LocationDto location) {
        mapper.insertLocation(location);
        return location;
    }
    
    @Override
    public void addActivity(ActivityDto activity) {
        mapper.insertActivity(activity);
    }
}
