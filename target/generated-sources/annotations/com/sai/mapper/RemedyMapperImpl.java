package com.sai.mapper;

import com.sai.api.dto.RemedyDTO;
import com.sai.api.requests.RemedyRequest;
import com.sai.model.Remedy;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-07T09:45:09+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Amazon.com Inc.)"
)
@Component
public class RemedyMapperImpl implements RemedyMapper {

    @Override
    public Remedy map(RemedyRequest remedyRequest) {
        if ( remedyRequest == null ) {
            return null;
        }

        Remedy remedy = new Remedy();

        remedy.setDiseaseName( remedyRequest.getDiseaseName() );
        remedy.setNaturalRemedy( remedyRequest.getNaturalRemedy() );

        return remedy;
    }

    @Override
    public RemedyDTO map(Remedy remedy) {
        if ( remedy == null ) {
            return null;
        }

        RemedyDTO remedyDTO = new RemedyDTO();

        remedyDTO.setDiseaseName( remedy.getDiseaseName() );
        remedyDTO.setNaturalRemedy( remedy.getNaturalRemedy() );

        return remedyDTO;
    }

    @Override
    public void merge(RemedyRequest remedyRequest, Remedy remedy) {
        if ( remedyRequest == null ) {
            return;
        }

        remedy.setDiseaseName( remedyRequest.getDiseaseName() );
        remedy.setNaturalRemedy( remedyRequest.getNaturalRemedy() );
    }

    @Override
    public List<RemedyDTO> map(List<Remedy> remedy) {
        if ( remedy == null ) {
            return null;
        }

        List<RemedyDTO> list = new ArrayList<RemedyDTO>( remedy.size() );
        for ( Remedy remedy1 : remedy ) {
            list.add( map( remedy1 ) );
        }

        return list;
    }
}
