package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.training.api.TrainingSimpleDto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {
    private final TrainingServiceImpl trainingsService;
    private final TrainingMapper trainingMapper;

    @GetMapping("/finished/{time}")
    public List<TrainingDto> getFinishedTrainings(@PathVariable String time){
        LocalDate localDate = LocalDate.parse(time);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return trainingsService.getFinishedTrainings(date)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping()
    public List<TrainingDto> getAllTrainings(){
        return trainingsService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("{userId}")
    public List<TrainingDto> getTraining(@PathVariable Long userId){
        return trainingsService.getTrainingsForUser(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivityType(@RequestParam ActivityType activityType){
        return trainingsService.getAllTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<TrainingDto> addTraining (@RequestBody TrainingSimpleDto trainingSimpleDto) {
        Training createdTraining = trainingsService.createTraining(trainingSimpleDto);
        TrainingDto responseDto = trainingMapper.toDto(createdTraining);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("{trainingId}")
    public ResponseEntity<TrainingDto> updateTraining (@PathVariable Long trainingId, @RequestBody TrainingSimpleDto trainingSimpleDto) {
        Optional<Training> updatedTraining = trainingsService.updateTraining(trainingId, trainingSimpleDto);
        TrainingDto responseDto = trainingMapper.toDto(updatedTraining.get());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
