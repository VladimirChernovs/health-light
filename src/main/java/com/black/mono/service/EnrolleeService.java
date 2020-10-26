package com.black.mono.service;

import com.black.mono.service.dto.EnrolleeBaseDTO;
import com.black.mono.service.dto.EnrolleeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.black.mono.domain.model.Enrollee}.
 */
public interface EnrolleeService {

    /**
     * Save a enrollee.
     *
     * @param enrolleeDTO the entity to save.
     * @return the persisted entity.
     */
    EnrolleeDTO save(EnrolleeBaseDTO enrolleeDTO);

    /**
     * Get all the enrollees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnrolleeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enrollee.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnrolleeDTO> findOne(Long id);

    /**
     * Delete the "id" enrollee.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
