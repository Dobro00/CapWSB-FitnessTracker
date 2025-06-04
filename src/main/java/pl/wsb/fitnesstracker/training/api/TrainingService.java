package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.TrainingDto;

import java.util.Date;
import java.util.List;

public interface TrainingService {
    List<Training> getFinishedTrainings(Date time);
}
