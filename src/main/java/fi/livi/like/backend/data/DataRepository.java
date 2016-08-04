package fi.livi.like.backend.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fi.livi.like.backend.data.dao.LocationDao;
import fi.livi.like.backend.data.dto.ActivityDto;
import fi.livi.like.backend.data.dto.LocationDto;
import fi.livi.like.backend.domain.Activity;
import fi.livi.like.backend.domain.Location;

@Component
public class DataRepository {

    private LocationDao locationDao;

    @Autowired
    public DataRepository(LocationDao locationDao) {
        this.locationDao = locationDao;
    }
    
    public void addLocations(Location[] locations) {
        for (Location location : locations) {
            LocationDto locationDto = LocationDtoFactory.getLocationDto(location);
            locationDto = locationDao.addLocation(locationDto);
            Activity activity = location.getActivity();
            if (activity != null) {
                ActivityDto activityDto = ActivityDtoFactory.getActivityDto(activity, locationDto);
                locationDao.addActivity(activityDto);
            }
        }
    }
}