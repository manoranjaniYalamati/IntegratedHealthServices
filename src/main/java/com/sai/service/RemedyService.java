package com.sai.service;

import com.sai.api.requests.RemedyRequest;
import com.sai.exception.RemedyNotFoundException;
import com.sai.mapper.RemedyMapper;
import com.sai.model.Remedy;
import com.sai.repository.RemedyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemedyService {

    @Autowired
    private RemedyRepository remedyRepository;

    @Autowired
    private RemedyMapper remedyMapper;

    public Remedy create(Remedy remedy){
        return remedyRepository.save(remedy);
    }

    public Remedy update(Long id, RemedyRequest remedyRequest) throws RemedyNotFoundException {
        Remedy remedy = findRemedy(id);//why this.find
        remedyMapper.merge(remedyRequest, remedy);
        return remedyRepository.save(remedy);
    }

    public Remedy findRemedy(Long id) throws RemedyNotFoundException {
        return remedyRepository.findById(id).orElseThrow(() -> new RemedyNotFoundException("Remedy not found with id : " + id));
    }

    public List<Remedy> findAll(){
        return remedyRepository.findAll();
    }

    public void delete(Long id) throws RemedyNotFoundException{
        Remedy remedy = findRemedy(id);
        if(remedy != null){//fetch empty util files from company db
            remedyRepository.delete(remedy);
        }
        else{
            throw new RemedyNotFoundException("Remedy Not Found with id " + id);
        }
    }

    public Remedy findRemedyByDiseaseName(String disease) throws RemedyNotFoundException{
        Remedy remedy = remedyRepository.findRemedyByDiseaseName(disease);
        if(remedy != null){
            return remedy;
        }
        else{
            throw new RemedyNotFoundException("Remedy Not Found with disease " + disease);//cant we get that error code to be in postman
        }
    }
}
