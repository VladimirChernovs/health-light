package com.black.mono.service.impl;

import com.black.mono.domain.model.Dependent;
import com.black.mono.domain.repository.DependentRepository;
import com.black.mono.exception.DependentEntityNotFoundException;
import com.black.mono.exception.EnrolleeEntityNotFoundException;
import com.black.mono.service.DependentService;
import com.black.mono.service.dto.DependentBaseDTO;
import com.black.mono.service.dto.DependentDTO;
import com.black.mono.service.mapper.DependentMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Dependent}.
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class DependentServiceImpl implements DependentService {

    private final DependentRepository dependentRepository;

    private final DependentMapper dependentMapper;

    @Override
    public DependentDTO save(DependentBaseDTO dependentDTO) {
        log.debug("Request to save Dependent : {}", dependentDTO);
        Dependent dependent = dependentMapper.toEntity(dependentDTO);
        if (dependentDTO instanceof DependentDTO) dependent.setId(((DependentDTO) dependentDTO).getId());
        dependent = dependentRepository.save(dependent);
        return dependentMapper.toDto(dependent);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DependentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Dependents");
        return dependentRepository.findAll(pageable)
                .map(dependentMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DependentDTO> findOne(Long id) {
        log.debug("Request to get Dependent : {}", id);
        return dependentRepository.findById(id)
                .map(dependentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dependent : {}", id);

        try {
            dependentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new DependentEntityNotFoundException(id);
        }

    }
}
