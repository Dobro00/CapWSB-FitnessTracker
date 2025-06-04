package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;

public class TrainingDto(Long Id, Date startTime, Date endTime, UserDto userDto,
                          ActivityType activityType, double distance, double averageSpeed) {
    public TrainingDto(Long id, Date startTime, Date endTime, pl.wsb.fitnesstracker.user.internal.UserDto dto, ActivityType activityType, double distance, double averageSpeed) {
    }
}
