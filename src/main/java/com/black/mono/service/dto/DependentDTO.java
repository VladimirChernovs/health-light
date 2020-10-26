package com.black.mono.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the Dependent entity.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public final class DependentDTO extends DependentBaseDTO {

    @NotNull
    Long id;

}
