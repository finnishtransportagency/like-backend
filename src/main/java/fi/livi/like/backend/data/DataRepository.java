package fi.livi.like.backend.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fi.livi.like.backend.data.dao.LocationDao;
import fi.livi.like.backend.data.dto.LocationDto;
import fi.livi.like.backend.domain.JourneyUpdate;

@Component
public class DataRepository {

    private LocationDao locationDao;

    @Autowired
    public DataRepository(LocationDao locationDao) {
        this.locationDao = locationDao;
    }
    
    public void addJourneyUpdates(JourneyUpdate[] journeyUpdates) {
        for (JourneyUpdate journeyUpdate : journeyUpdates) {
            LocationDto locationDto = LocationDtoFactory.getLocationDto(journeyUpdate);
            locationDao.addLocation(locationDto);
        }
    }
}