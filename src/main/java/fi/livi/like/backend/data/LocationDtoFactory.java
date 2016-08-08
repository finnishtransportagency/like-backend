package fi.livi.like.backend.data;

import java.util.Date;

import fi.livi.like.backend.data.dto.LocationDto;
import fi.livi.like.backend.domain.JourneyUpdate;
import fi.livi.like.backend.domain.LikeLocation;

public class LocationDtoFactory {

    private LocationDtoFactory() {
    }

    public static LocationDto getLocationDto(JourneyUpdate journeyUpdate) {
        long journeyId = journeyUpdate.getJourneyId(); 
        long subJourneyId = journeyUpdate.getSubJourneyId();
        Date timeStamp = journeyUpdate.getTimestamp();
        LikeLocation likeLocation = journeyUpdate.getLikeLocation();
        double latitude = likeLocation.getLatitude(); 
        double longitude = likeLocation.getLongitude(); 
        int heading = likeLocation.getHeading();
        int speed = likeLocation.getSpeed();
        String userId = journeyUpdate.getUserId();
        String activityType = null;
        Integer activityConfidence = null;
        if (journeyUpdate.getLikeActivity() != null) {
            activityType = journeyUpdate.getLikeActivity().getType().toString();
            activityConfidence = journeyUpdate.getLikeActivity().getConfidence();
        }
        
        return new LocationDto(
            null,
            journeyId,
            subJourneyId,
            latitude, 
            longitude, 
            heading, 
            speed, 
            activityType,
            activityConfidence,
            timeStamp, 
            userId); 
    }
}
