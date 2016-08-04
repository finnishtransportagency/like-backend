package fi.livi.like.backend.data;

import fi.livi.like.backend.data.dto.ActivityDto;
import fi.livi.like.backend.data.dto.LocationDto;
import fi.livi.like.backend.domain.Activity;

public class ActivityDtoFactory {

    private ActivityDtoFactory() {
    }

    public static ActivityDto getActivityDto(Activity activity, LocationDto locationDto) {
        return new ActivityDto(
            null,
            activity.getType(),
            activity.getConfidence(),
            locationDto.getTimestamp(),
            locationDto.getUserId(),
            locationDto.getId());
    }
}
