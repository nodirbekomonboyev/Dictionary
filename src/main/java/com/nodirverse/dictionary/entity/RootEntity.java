package com.nodirverse.dictionary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "roots")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RootEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private UserEntity owner;
    private String uz;
    private String eng;
}
