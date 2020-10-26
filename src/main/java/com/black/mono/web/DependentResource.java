package com.black.mono.web;

import com.black.mono.config.Constants;
import com.black.mono.exception.DependentEntityNotFoundException;
import com.black.mono.service.DependentService;
import com.black.mono.service.dto.DependentBaseDTO;
import com.black.mono.service.dto.DependentDTO;
import com.black.mono.web.util.HeaderUtil;
import com.black.mono.web.util.PaginationUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * REST controller for managing {@link com.black.mono.domain.model.Dependent}.
 */
@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class DependentResource {

    private static final String ENTITY_NAME = "dependent";

    private final DependentService dataService;

    /**
     * {@code POST  /dependents} : Create a new dependent.
     *
     * @param dependentPostDTO the dependentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dependentDTO, or with status {@code 400 (Bad Request)} if the dependent has already an ID.
     */
    @PostMapping("/dependents")
    public ResponseEntity<DependentDTO> createDependent(final @Valid @RequestBody DependentBaseDTO dependentPostDTO) {
        log.debug("REST request to save {} : {}", ENTITY_NAME, dependentPostDTO);
        DependentDTO result = dataService.save(dependentPostDTO);

        final String createdUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(Constants.BASE_URL + "/")
                .path(result.getId().toString())
                .toUriString();
        final HttpHeaders headers = HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId());

        return ResponseEntity.created(URI.create(createdUrl)).headers(headers).body(result);
    }

    /**
     * {@code PUT  /dependents} : Updates an existing dependent.
     *
     * @param dependentDTO the dependentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dependentDTO,
     * or with status {@code 400 (Bad Request)} if the dependentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dependentDTO couldn't be updated.
     */
    @PutMapping("/dependents")
    public ResponseEntity<DependentDTO> updateDependent(@Valid @RequestBody DependentDTO dependentDTO) {
        log.debug("REST request to update {} : {}", ENTITY_NAME, dependentDTO);
        final Long id = dependentDTO.getId();
        final HttpHeaders headers = HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, id);

        return dataService.findOne(id)
                .map(i -> dataService.save(dependentDTO))
                .map(response -> ResponseEntity.ok().headers(headers).body(response))
                .orElseThrow(() -> new DependentEntityNotFoundException(id));
    }

    /**
     * {@code GET  /dependents} : get all the dependents.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dependents in body.
     */
    @GetMapping("/dependents")
    public ResponseEntity<List<DependentDTO>> getAllDependents(Pageable pageable) {
        log.debug("REST request to get a page of {}", ENTITY_NAME);

        Page<DependentDTO> page = dataService.findAll(pageable);
        final HttpHeaders headers =
                PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dependents/:id} : get the "id" dependent.
     *
     * @param id the id of the dependentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dependentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dependents/{id}")
    public ResponseEntity<DependentDTO> getDependent(@PathVariable Long id) {
        log.debug("REST request to get {} : {}", ENTITY_NAME, id);

        return dataService.findOne(id).map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new DependentEntityNotFoundException(id));
    }

    /**
     * {@code DELETE  /dependents/:id} : delete the "id" dependent.
     *
     * @param id the id of the dependentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dependents/{id}")
    public ResponseEntity<Void> deleteDependent(@PathVariable Long id) {
        log.debug("REST request to delete {} : {}", ENTITY_NAME, id);
        dataService.delete(id);

        final HttpHeaders headers = HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id);
        return ResponseEntity.noContent().headers(headers).build();
    }
}
