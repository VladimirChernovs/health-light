package com.black.mono.exception;

import com.black.mono.domain.model.Enrollee;

public class EnrolleeEntityNotFoundException extends EntityNotFoundException{

    public EnrolleeEntityNotFoundException(Long id) {
        super(Enrollee.class, "id", id.toString());
    }
}
