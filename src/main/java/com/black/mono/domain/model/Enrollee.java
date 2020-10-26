package com.black.mono.domain.model;

import com.black.mono.config.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "enrollee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Enrollee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "activation_status", nullable = false)
    private Boolean activationStatus;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Pattern(regexp = Constants.REGEXP_PHONE)
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "enrollee", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Dependent> dependents = new HashSet<>();

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Enrollee)) {
            return false;
        }
        return id != null && id.equals(((Enrollee) o).id);
    }
}
