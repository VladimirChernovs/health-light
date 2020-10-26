package com.black.mono.domain.repository;

import com.black.mono.domain.model.Dependent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Dependent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DependentRepository extends JpaRepository<Dependent, Long> {
}
