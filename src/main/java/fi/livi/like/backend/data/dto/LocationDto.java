package fi.livi.like.backend.data.dto;

import java.util.Date;



public class LocationDto {

    private Long id;
    private long journeyId;
    private long subJourneyId;
    private double latitude;
    private double longitude;
    private int heading;
    private int speed;
    private String activityType;
    private Integer activityConfidence;
    private Date timestamp;
    private String userId;
    
    @SuppressWarnings("unused")
    private LocationDto() {
        // For myBatis
    }
    
    public LocationDto(Long id, long journeyId, long subJourneyId, double latitude, double longitude, int heading, int speed, 
            String activityType, Integer activityConfidence, 
            Date timestamp, String userId) {
        this.id = id;
        this.journeyId = journeyId;
        this.subJourneyId = subJourneyId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.heading = heading;
        this.speed = speed;
        this.activityType = activityType;
        this.activityConfidence = activityConfidence;
        this.timestamp = timestamp;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public long getJourneyId() {
        return journeyId;
    }

    public long getSubJourneyId() {
        return subJourneyId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getHeading() {
        return heading;
    }

    public int getSpeed() {
        return speed;
    }
    
    public String getActivityType() {
        return activityType;
    }

    public Integer getActivityConfidence() {
        return activityConfidence;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((activityConfidence == null) ? 0 : activityConfidence
                        .hashCode());
        result = prime * result
                + ((activityType == null) ? 0 : activityType.hashCode());
        result = prime * result + heading;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + (int) (journeyId ^ (journeyId >>> 32));
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + speed;
        result = prime * result + (int) (subJourneyId ^ (subJourneyId >>> 32));
        result = prime * result
                + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LocationDto other = (LocationDto) obj;
        if (activityConfidence == null) {
            if (other.activityConfidence != null)
                return false;
        } else if (!activityConfidence.equals(other.activityConfidence))
            return false;
        if (activityType == null) {
            if (other.activityType != null)
                return false;
        } else if (!activityType.equals(other.activityType))
            return false;
        if (heading != other.heading)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (journeyId != other.journeyId)
            return false;
        if (Double.doubleToLongBits(latitude) != Double
                .doubleToLongBits(other.latitude))
            return false;
        if (Double.doubleToLongBits(longitude) != Double
                .doubleToLongBits(other.longitude))
            return false;
        if (speed != other.speed)
            return false;
        if (subJourneyId != other.subJourneyId)
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LocationDto [id=" + id + ", journeyId=" + journeyId
                + ", subJourneyId=" + subJourneyId + ", latitude=" + latitude
                + ", longitude=" + longitude + ", heading=" + heading
                + ", speed=" + speed + ", activityType=" + activityType
                + ", activityConfidence=" + activityConfidence + ", timestamp="
                + timestamp + ", userId=" + userId + "]";
    }

}
