package com.black.mono.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

/**
 * A DTO for the Post Dependent entity.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DependentBaseDTO {

    @NotNull
    protected String name;

    @NotNull
    @PastOrPresent
    @ApiModelProperty(value = "Birth date", allowableValues = "2000-10-25")
    protected LocalDate birthDate;

    protected Long enrolleeId;
}
