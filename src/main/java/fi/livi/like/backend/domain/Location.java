package fi.livi.like.backend.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ 
    "latitude", 
    "longitude",
    "heading",
    "speed",
    "timestamp",
    "userId",
    "activity"
    })
public class Location {

    private double latitude;
    private double longitude;
    private int heading;
    private int speed;
    private LocalDateTime timestamp;
    private String userId;
    private Activity activity;
    
    @JsonCreator
    public Location(
            @JsonProperty("latitude") double latitude, 
            @JsonProperty("longitude") double longitude, 
            @JsonProperty("heading") int heading, 
            @JsonProperty("speed") int speed,
            @JsonProperty("timestamp") LocalDateTime timestamp,
            @JsonProperty("userId") String userId,
            @JsonProperty("activity") Activity activity
            ) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.longitude = longitude;
        this.heading = heading;
        this.speed = speed;
        this.timestamp = timestamp;
        this.userId = userId;
        this.activity = activity;
    }

    @JsonProperty("latitude")
    public double getLatitude() {
        return latitude;
    }

    @JsonProperty("longitude")
    public double getLongitude() {
        return longitude;
    }

    @JsonProperty("heading")
    public int getHeading() {
        return heading;
    }

    @JsonProperty("speed")
    public int getSpeed() {
        return speed;
    }

    @JsonProperty("timestamp")
    @JsonSerialize(using = LocalDateTimeSerializer.class)    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @JsonProperty("activity")
    public Activity getActivity() {
        return activity;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((activity == null) ? 0 : activity.hashCode());
        result = prime * result + heading;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + speed;
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
        Location other = (Location) obj;
        if (activity == null) {
            if (other.activity != null)
                return false;
        } else if (!activity.equals(other.activity))
            return false;
        if (heading != other.heading)
            return false;
        if (Double.doubleToLongBits(latitude) != Double
                .doubleToLongBits(other.latitude))
            return false;
        if (Double.doubleToLongBits(longitude) != Double
                .doubleToLongBits(other.longitude))
            return false;
        if (speed != other.speed)
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
        return "Location [latitude=" + latitude + ", longitude=" + longitude
                + ", heading=" + heading + ", speed=" + speed + ", timestamp="
                + timestamp + ", userId=" + userId + ", activity=" + activity
                + "]";
    }

}
