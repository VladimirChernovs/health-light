package com.black.mono.service.impl;

import com.black.mono.domain.model.Enrollee;
import com.black.mono.domain.repository.EnrolleeRepository;
import com.black.mono.exception.EnrolleeEntityNotFoundException;
import com.black.mono.service.EnrolleeService;
import com.black.mono.service.dto.EnrolleeBaseDTO;
import com.black.mono.service.dto.EnrolleeDTO;
import com.black.mono.service.mapper.EnrolleeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Enrollee}.
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class EnrolleeServiceImpl implements EnrolleeService {

    private final EnrolleeRepository enrolleeRepository;

    private final EnrolleeMapper enrolleeMapper;

    @Override
    public EnrolleeDTO save(EnrolleeBaseDTO enrolleeDTO) {
        log.debug("Request to save Enrollee : {}", enrolleeDTO);
        Enrollee enrollee = enrolleeMapper.toEntity(enrolleeDTO);
        if (enrolleeDTO instanceof EnrolleeDTO) enrollee.setId(((EnrolleeDTO) enrolleeDTO).getId());
        enrollee = enrolleeRepository.save(enrollee);
        return enrolleeMapper.toDto(enrollee);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EnrolleeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Enrollees");
        return enrolleeRepository.findAll(pageable)
                .map(enrolleeMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EnrolleeDTO> findOne(Long id) {
        log.debug("Request to get Enrollee : {}", id);
        return enrolleeRepository.findById(id)
                .map(enrolleeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Enrollee : {}", id);
        try {
            enrolleeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EnrolleeEntityNotFoundException(id);
        }
    }
}