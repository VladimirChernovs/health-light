package com.black.mono.domain.repository;

import com.black.mono.domain.model.Enrollee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Enrollee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnrolleeRepository extends JpaRepository<Enrollee, Long> {
}
