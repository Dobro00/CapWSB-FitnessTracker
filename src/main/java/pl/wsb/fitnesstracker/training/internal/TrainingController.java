package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
}
