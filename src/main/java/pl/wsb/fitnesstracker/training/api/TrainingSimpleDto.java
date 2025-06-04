package pl.wsb.fitnesstracker.training.api;

import lombok.Getter;
import lombok.Setter;
import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
@Getter
@Setter

public class TrainingSimpleDto {

    private Long id;
    private Date startTime;
    private Date endTime;
    private Long userId;
    private ActivityType activityType;
    private double distance;
    private double averageSpeed;

    public TrainingSimpleDto(Long id, Date startTime, Date endTime, Long userId, ActivityType activityType, double distance, double averageSpeed) {

        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userId = userId;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}
