package com.black.mono.exception;

import com.black.mono.domain.model.Dependent;

public class DependentEntityNotFoundException extends EntityNotFoundException{

    public DependentEntityNotFoundException(Long id) {
        super(Dependent.class, "id", id.toString());
    }
}
