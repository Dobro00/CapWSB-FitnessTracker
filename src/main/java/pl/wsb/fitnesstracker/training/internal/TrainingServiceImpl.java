package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

// TODO: Provide Implementation and correct the return type of the method getTraining
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;
    private final TrainingMapper trainingMapper;

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    public List<Training> getFinishedTrainings(final Date time) {
        return trainingRepository.getFinishedTrainings(time);
    }

    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    public List<Training> getTrainingsForUser(final Long userId) {
        return trainingRepository.findAllById(userId);
    }

    public List<Training> getAllTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.getAllTrainingsByActivityType(activityType);
    }

    public Training createTraining(final TrainingSimpleDto trainingSimpleDto) {
        Long userId = trainingSimpleDto.getUserId();

        User user = userRepository.findById(userId).orElse(null);

        Training training = trainingMapper.toEntity(trainingSimpleDto, user);
        return trainingRepository.save(training);
    }

    public Optional<Training> updateTraining(final Long trainingId, final TrainingSimpleDto trainingSimpleDto) {
        Optional<Training> training = trainingRepository.findById(trainingId);
        training.ifPresent(t -> {
            t.setStartTime(trainingSimpleDto.getStartTime());
            t.setEndTime(trainingSimpleDto.getEndTime());
            t.setActivityType(trainingSimpleDto.getActivityType());
            t.setAverageSpeed(trainingSimpleDto.getAverageSpeed());
            t.setDistance(trainingSimpleDto.getDistance());
            t.setUser(userRepository.findById(trainingSimpleDto.getUserId()).orElse(null));
            trainingRepository.save(t);
        });
        return training;
    }
}
