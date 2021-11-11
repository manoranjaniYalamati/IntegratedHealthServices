package com.sai.mapper;

import com.sai.api.dto.AppointmentSlotsDTO;
import com.sai.api.dto.RemedyDTO;
import com.sai.api.requests.AppointmentSlotsRequest;
import com.sai.api.requests.RemedyRequest;
import com.sai.model.AppointmentSlots;
import com.sai.model.Remedy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RemedyMapper {

    Remedy map(RemedyRequest remedyRequest);

    RemedyDTO map(Remedy remedy);

    void merge(RemedyRequest remedyRequest, @MappingTarget Remedy remedy);

    List<RemedyDTO> map(List<Remedy> remedy);
}
