package pl.wsb.fitnesstracker.training.api;

import java.util.Date;
import java.util.List;

public interface TrainingService {
    List<Training> getFinishedTrainings(Date time);
}
