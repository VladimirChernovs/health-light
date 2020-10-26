package com.black.mono.service;

import com.black.mono.service.dto.DependentBaseDTO;
import com.black.mono.service.dto.DependentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.black.mono.domain.model.Dependent}.
 */
public interface DependentService {

    /**
     * Save a dependent.
     *
     * @param dependentDTO the entity to save.
     * @return the persisted entity.
     */
    DependentDTO save(DependentBaseDTO dependentDTO);

    /**
     * Get all the dependents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DependentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dependent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DependentDTO> findOne(Long id);

    /**
     * Delete the "id" dependent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
