package com.sparta.schedule.repository;

import com.sparta.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule ,Long> {
     List<Schedule> findAllByOrderByModifiedAtDesc();

     Optional<Schedule> findByManager(String manager);
}
