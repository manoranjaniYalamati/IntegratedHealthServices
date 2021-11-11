package com.sai.api.controllers;

import com.sai.api.dto.RemedyDTO;
import com.sai.api.requests.RemedyRequest;
import com.sai.exception.RemedyNotFoundException;
import com.sai.mapper.RemedyMapper;
import com.sai.model.Remedy;
import com.sai.service.RemedyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/remedy")
public class RemedyController {

    @Autowired
    private RemedyService remedyService;

    private final RemedyMapper remedyMapper;



    @RequestMapping(method = RequestMethod.POST)
    public RemedyDTO create(@RequestBody RemedyRequest remedyRequest) {
        Remedy remedy = remedyMapper.map(remedyRequest);
        return remedyMapper.map(remedyService.create(remedy));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public RemedyDTO update(@PathVariable("id") Long id,
                            @RequestBody RemedyRequest remedyRequest) throws RemedyNotFoundException {//what about rem fields if request accepted what abput mandatory fields if dcotor is taken ?
        log.debug("PUT /Remedy/"+id+"  with request:"+remedyRequest);
        Remedy remedy =this.remedyService.update(id, remedyRequest);
        return remedyMapper.map(remedy);
    }

    @GetMapping("/{id}")
    public RemedyDTO findRemedy(@PathVariable("id") Long id) throws RemedyNotFoundException{
        log.debug("GET /Remedy/"+id);
        Remedy remedy = remedyService.findRemedy(id);
        return remedyMapper.map(remedy);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<RemedyDTO> findAllRemedys(){
        log.debug("GET /Remedy/");
        return remedyMapper.map(remedyService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteRemedy(@PathVariable("id") Long id) throws RemedyNotFoundException{
        log.debug("DELETE /Remedy/"+id);
        remedyService.delete(id);
        return "Remedy with id " + id + " deleted";
    }

    @GetMapping("/disease")
    public RemedyDTO findRemedyByDisease(@RequestParam("disease") String disease) throws RemedyNotFoundException{
        log.debug("GET /Remedy/"+disease);
        Remedy remedy = remedyService.findRemedyByDiseaseName(disease);
        return remedyMapper.map(remedy);
    }
}
