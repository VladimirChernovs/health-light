package com.black.mono.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Dependent.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "dependent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dependent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @ManyToOne(optional = false)
    @NotNull
    private Enrollee enrollee;

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dependent)) {
            return false;
        }
        return id != null && id.equals(((Dependent) o).id);
    }
}
