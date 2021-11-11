package com.sai.api.controllers;

import com.sai.api.dto.TimeSlotsDTO;
import com.sai.api.requests.TimeSlotsRequest;
import com.sai.mapper.TimeSlotsMapper;
import com.sai.model.TimeSlots;
import com.sai.service.TimeSlotsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/timeslot")
public class TimeSlotsController {

    @Autowired
    TimeSlotsService timeSlotsService;

    private  final TimeSlotsMapper timeSlotsMapper;

    @RequestMapping(method = RequestMethod.POST)
    public TimeSlotsDTO create(@RequestBody TimeSlotsRequest timeSlotsRequest) {
        TimeSlots timeSlots = timeSlotsMapper.map(timeSlotsRequest);
        return timeSlotsMapper.map(timeSlotsService.create(timeSlots));
    }

}
