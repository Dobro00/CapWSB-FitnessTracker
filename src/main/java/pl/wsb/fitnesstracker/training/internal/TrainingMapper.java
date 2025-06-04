package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.internal.UserMapper;

@Component
public class TrainingMapper {

    private final UserMapper userMapper;

    public TrainingMapper(UserMapper userMapper1) {
        this.userMapper = userMapper1;
    }
    TrainingDto toDto(Training training) {
        return new TrainingDto(training.getId(),
                training.getStartTime(),
                training.getEndTime(),
                userMapper.toDto(training.getUser()),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }
}
