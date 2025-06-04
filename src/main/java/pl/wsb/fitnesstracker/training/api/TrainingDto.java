package pl.wsb.fitnesstracker.training.api;

import lombok.Getter;
import lombok.Setter;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;
@Getter
@Setter

public class TrainingDto {

    private Long id;
    private Date startTime;
    private Date endTime;
    private UserDto user;
    private ActivityType activityType;
    private double distance;
    private double averageSpeed;

    public TrainingDto(Long id, Date startTime, Date endTime, pl.wsb.fitnesstracker.user.api.UserDto user, ActivityType activityType, double distance, double averageSpeed) {

        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}
