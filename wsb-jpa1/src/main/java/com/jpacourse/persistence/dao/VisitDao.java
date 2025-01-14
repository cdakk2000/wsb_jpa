package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


@Repository

public interface VisitDao extends JpaRepository<VisitEntity, Long> {

    @Query("SELECT v FROM VisitEntity v WHERE v.patient.id = :patientId")
    List<VisitEntity> findVisitsByPatientId(@Param("patientId") Long patientId);
}
