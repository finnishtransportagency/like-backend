package fi.livi.like.backend.data.dto;

import java.time.LocalDateTime;


public class ActivityDto {

    private Long id;
    private String type;
    private int confidence;
    private LocalDateTime timestamp;
    private String userId;
    private long locationId;

    @SuppressWarnings("unused")
    private ActivityDto() {
        // For myBatis
    }
    
    public ActivityDto(Long id, String type, int confidence, LocalDateTime timestamp, String userId, long locationId) {
        this.id = id;
        this.type = type;
        this.confidence = confidence;
        this.timestamp = timestamp;
        this.userId = userId;
        this.locationId = locationId;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getConfidence() {
        return confidence;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public long getLocationId() {
        return locationId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + confidence;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + (int) (locationId ^ (locationId >>> 32));
        result = prime * result
                + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        ActivityDto other = (ActivityDto) obj;
        if (confidence != other.confidence)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (locationId != other.locationId)
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
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
        return "ActivityDto [id=" + id + ", type=" + type + ", confidence="
                + confidence + ", timestamp=" + timestamp + ", userId="
                + userId + ", locationId=" + locationId + "]";
    }
    
    
}
