package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientDao extends Dao<PatientEntity, Long> {
    void addVisitToPatient(Long patientId, Long doctorId, java.time.LocalDateTime visitDate, String description);

    @Query("SELECT p FROM PatientEntity p WHERE p.lastName = :lastName")
    List<PatientEntity> findPatientsByLastName(@Param("lastName") String lastName);

    @Query("SELECT p FROM PatientEntity p WHERE SIZE(p.visits) > :visitCount")
    List<PatientEntity> findPatientsWithMoreThanXVisits(@Param("visitCount") int visitCount);

    @Query("SELECT p FROM PatientEntity p WHERE p.dateOfBirth < :date")
    List<PatientEntity> findPatientsBornBefore(@Param("date") LocalDate date);
}
