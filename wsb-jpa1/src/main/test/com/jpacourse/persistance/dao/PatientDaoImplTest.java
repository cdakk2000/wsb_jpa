package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.impl.PatientDaoImpl;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PatientDaoImplTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PatientDaoImpl patientDao;

    @Test
    @Transactional
    public void shouldAddVisitToPatient() {
        // given
        Long patientId = 1L;
        Long doctorId = 1L;
        LocalDateTime visitDate = LocalDateTime.of(2023, 12, 15, 9, 0);
        String description = "Konsultacja kontrolna";

        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        assertThat(patient).isNotNull();
        int initialVisitCount = patient.getVisits().size();

        // when
        patientDao.addVisitToPatient(patientId, doctorId, visitDate, description);

        // then
        PatientEntity updatedPatient = entityManager.find(PatientEntity.class, patientId);
        assertThat(updatedPatient.getVisits()).hasSize(initialVisitCount + 1);

        VisitEntity newVisit = updatedPatient.getVisits()
                .stream()
                .filter(visit -> visit.getDescription().equals(description))
                .findFirst()
                .orElse(null);
        assertThat(newVisit).isNotNull();
        assertThat(newVisit.getDoctor().getId()).isEqualTo(doctorId);
        assertThat(newVisit.getTime()).isEqualTo(visitDate);
        assertThat(newVisit.getDescription()).isEqualTo(description);
    }
}
