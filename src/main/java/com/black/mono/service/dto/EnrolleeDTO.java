package com.black.mono.service.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the Enrollee entity.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public final class EnrolleeDTO extends EnrolleeBaseDTO {

    @NotNull
    private Long id;

}

