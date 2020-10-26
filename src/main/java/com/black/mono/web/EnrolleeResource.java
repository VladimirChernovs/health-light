package com.black.mono.web;


import com.black.mono.config.Constants;
import com.black.mono.exception.EnrolleeEntityNotFoundException;
import com.black.mono.service.EnrolleeService;
import com.black.mono.service.dto.EnrolleeBaseDTO;
import com.black.mono.service.dto.EnrolleeDTO;
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
 * REST controller for Enrollee
 */
@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EnrolleeResource {

    private static final String ENTITY_NAME = "enrollee";

    private final EnrolleeService dataService;

    /**
     * {@code POST  /enrollees} : Create a new enrollee.
     *
     * @param enrolleePostDTO the enrolleeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enrolleeDTO,
     * or with status {@code 400 (Bad Request)} if validation error.
     */
    @PostMapping("/enrollee")
    public ResponseEntity<EnrolleeDTO> createEnrollee(final @Valid @RequestBody EnrolleeBaseDTO enrolleePostDTO) {
        log.debug("REST request to save {} : {}", ENTITY_NAME, enrolleePostDTO);
        EnrolleeDTO result = dataService.save(enrolleePostDTO);

       final String createdUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(Constants.BASE_URL + "/")
                .path(result.getId().toString())
                .toUriString();
        final HttpHeaders headers = HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId());

        return ResponseEntity.created(URI.create(createdUrl)).headers(headers).body(result);
    }

    /**
     * {@code PUT  /enrollees} : Updates an existing enrollee.
     *
     * @param enrolleeDTO the enrolleeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enrolleeDTO,
     * or with status {@code 400 (Bad Request)} if the enrolleeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enrolleeDTO couldn't be updated.
     */
    @PutMapping("/enrollees")
    public ResponseEntity<EnrolleeDTO> updateEnrollee(final @Valid @RequestBody EnrolleeDTO enrolleeDTO) {
        log.debug("REST request to update {} : {}", ENTITY_NAME, enrolleeDTO);
        final Long id = enrolleeDTO.getId();
        final HttpHeaders headers = HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, id);

        return dataService.findOne(id)
                .map(i -> dataService.save(enrolleeDTO))
                .map(response -> ResponseEntity.ok().headers(headers).body(response))
                .orElseThrow(() -> new EnrolleeEntityNotFoundException(id));
    }

    /**
     * {@code GET  /enrollees} : get all the enrollees.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enrollees in body.
     */
    @GetMapping("/enrollees")
    public ResponseEntity<List<EnrolleeDTO>> getAllEnrollees(final Pageable pageable) {
        log.debug("REST request to get a page of {}", ENTITY_NAME);

        Page<EnrolleeDTO> page = dataService.findAll(pageable);
        final HttpHeaders headers =
                PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /enrollees/:id} : get the "id" enrollee.
     *
     * @param id the id of the enrolleeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enrolleeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enrollees/{id}")
    public ResponseEntity<EnrolleeDTO> getEnrollee(final @PathVariable Long id) {
        log.debug("REST request to get {} : {}", ENTITY_NAME, id);

        return dataService.findOne(id).map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new EnrolleeEntityNotFoundException(id));
    }

    /**
     * {@code DELETE  /enrollees/:id} : delete the "id" enrollee.
     *
     * @param id the id of the enrolleeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enrollees/{id}")
    public ResponseEntity<Void> deleteEnrollee(final @PathVariable Long id) {
        log.debug("REST request to delete {} : {}", ENTITY_NAME, id);
        dataService.delete(id);

        final HttpHeaders headers = HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id);
        return ResponseEntity.noContent().headers(headers).build();
    }
}
