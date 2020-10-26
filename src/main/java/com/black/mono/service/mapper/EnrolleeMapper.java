package com.black.mono.service.mapper;


import com.black.mono.domain.model.Enrollee;
import com.black.mono.service.dto.EnrolleeBaseDTO;
import com.black.mono.service.dto.EnrolleeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the entity {@link Enrollee} and its DTO {@link EnrolleeDTO}.
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnrolleeMapper extends EntityMapper<EnrolleeDTO, Enrollee> {

    @Mapping(target = "dependents", ignore = true)
    Enrollee toEntity(EnrolleeBaseDTO enrolleeDTO);

    default Enrollee fromId(Long id) {
        if (id == null) {
            return null;
        }
        Enrollee enrollee = new Enrollee();
        enrollee.setId(id);
        return enrollee;
    }

}
