package fi.livi.like.backend.data.dao;

import fi.livi.like.backend.data.dto.ActivityDto;
import fi.livi.like.backend.data.dto.LocationDto;


public interface LocationDao {
    public LocationDto addLocation(LocationDto location);
    public void addActivity(ActivityDto activity);
}
