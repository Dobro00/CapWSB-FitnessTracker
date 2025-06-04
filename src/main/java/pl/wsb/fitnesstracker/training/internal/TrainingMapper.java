package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.training.api.TrainingSimpleDto;
import pl.wsb.fitnesstracker.user.api.User;
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

    Training toEntity(TrainingSimpleDto trainingSimpleDto, User user) {
        return new Training(
                user,
                trainingSimpleDto.getStartTime(),
                trainingSimpleDto.getEndTime(),
                trainingSimpleDto.getActivityType(),
                trainingSimpleDto.getDistance(),
                trainingSimpleDto.getAverageSpeed()
        );
    }
}
