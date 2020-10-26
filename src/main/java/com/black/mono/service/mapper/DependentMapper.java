package com.black.mono.service.mapper;


import com.black.mono.domain.model.Dependent;
import com.black.mono.service.dto.DependentBaseDTO;
import com.black.mono.service.dto.DependentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the entity {@link Dependent} and its DTO {@link DependentDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnrolleeMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DependentMapper extends EntityMapper<DependentDTO, Dependent> {

    @Mapping(source = "enrollee.id", target = "enrolleeId")
    DependentDTO toDto(Dependent dependent);

    @Mapping(source = "enrolleeId", target = "enrollee")
    Dependent toEntity(DependentBaseDTO dependentDTO);

    default Dependent fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dependent dependent = new Dependent();
        dependent.setId(id);
        return dependent;
    }
}
