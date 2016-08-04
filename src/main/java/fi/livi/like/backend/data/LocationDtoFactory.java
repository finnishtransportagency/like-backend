package fi.livi.like.backend.data;

import fi.livi.like.backend.data.dto.LocationDto;
import fi.livi.like.backend.domain.Location;

public class LocationDtoFactory {

    private LocationDtoFactory() {
    }

    public static LocationDto getLocationDto(Location location) {
        return new LocationDto(
            null, 
            location.getLatitude(), 
            location.getLongitude(), 
            location.getHeading(), 
            location.getSpeed(), 
            location.getTimestamp(), 
            location.getUserId()); 
    }
}
