package com.nodirverse.dictionary.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.time.LocalDate;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @CreationTimestamp
    protected LocalDate createdDate;

    @UpdateTimestamp
    protected LocalDate updatedDate;

}
