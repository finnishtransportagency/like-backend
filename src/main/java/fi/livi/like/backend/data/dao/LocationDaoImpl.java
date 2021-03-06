package fi.livi.like.backend.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void addLocation(LocationDto location) {
        mapper.insertLocation(location);
    }
}
