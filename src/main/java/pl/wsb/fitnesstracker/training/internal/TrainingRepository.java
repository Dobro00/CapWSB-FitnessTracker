package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.Date;
import java.util.List;


interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query("SELECT t FROM Training t WHERE t.endTime > :time")
    List<Training> getFinishedTrainings(Date time);

    @Query("SELECT t FROM Training t WHERE t.user.id = :userId")
    List<Training> findAllById(Long userId);

    @Query("SELECT t FROM Training t WHERE t.activityType = :activityType")
    List<Training> getAllTrainingsByActivityType(ActivityType activityType);
}
