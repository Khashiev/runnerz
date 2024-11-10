package com.nazirka.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private final List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream()
                .filter(run -> run.id().equals(id))
                .findFirst();
    }

    void create(Run run) {
        runs.add(run);
    }

    void update(Run run, Integer id) {
        Optional<Run> existingRun = findById(id);

        existingRun.ifPresent(value -> runs.set(runs.indexOf(value), run));
    }

    void delete(Integer id) {
        runs.removeIf(run -> run.id().equals(id));
    }

    @PostConstruct
    public void init() {
        runs.add(new Run(
                1,
                "first",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                3, Location.INDOOR)
        );

        runs.add(new Run(
                2,
                "second",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(60),
                6, Location.INDOOR)
        );
    }
}
