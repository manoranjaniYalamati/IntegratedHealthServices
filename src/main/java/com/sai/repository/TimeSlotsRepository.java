package com.sai.repository;

import com.sai.model.TimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotsRepository extends JpaRepository<TimeSlots, Long> {

    public List<TimeSlots> findTimeSlotsBySlotId(Long id);

}
