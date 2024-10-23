package com.nodirverse.dictionary.repository;

import com.nodirverse.dictionary.entity.RootEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface RootRepository extends JpaRepository<RootEntity, UUID> {

    List<RootEntity> findDistinctByOwnerIdOrderByCreatedDate(UUID ownerId);
    List<RootEntity> findRootEntitiesByCreatedDate(LocalDate createdDate);

    @Query(value = "SELECT * FROM roots ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<RootEntity> findRandomRoots(@Param("limit") int limit);

}
