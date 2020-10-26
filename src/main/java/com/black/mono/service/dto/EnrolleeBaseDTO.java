package com.black.mono.service.dto;

import com.black.mono.config.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * A DTO for the Post Enrollee entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class EnrolleeBaseDTO {

    @NotNull
    @ApiModelProperty(value = "name", allowableValues = "Vladimir")
    protected String name;

    @NotNull
    protected Boolean activationStatus;

    @NotNull
    @PastOrPresent
    @ApiModelProperty(value = "Birth date", allowableValues = "2000-10-25")
    protected LocalDate birthDate;

    @Pattern(regexp = Constants.REGEXP_PHONE)
    @ApiModelProperty(value = "Phone number", allowableValues = "+111 (202) 555-0125")
    protected String phoneNumber;
}
