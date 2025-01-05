package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private DoctorDao doctorDao;

    @Test
    @Transactional
    public void shouldDeletePatientAndCascadeVisitsWithoutDeletingDoctors() {
        // given
        PatientEntity patient = patientDao.findOne(1L);
        assertThat(patient).isNotNull();
        int initialVisitCount = visitDao.findAll().size();
        int initialDoctorCount = doctorDao.findAll().size();

        // when
        patientService.deletePatient(1L);

        // then
        assertThat(patientDao.findOne(1L)).isNull();
        assertThat(visitDao.findAll().size()).isEqualTo(initialVisitCount - patient.getVisits().size());
        assertThat(doctorDao.findAll().size()).isEqualTo(initialDoctorCount);
    }

    @Test
    @Transactional
    public void shouldRetrievePatientWithValidTO() {
        // given
        Long patientId = 1L;

        // when
        PatientTO patientTO = patientService.findPatientById(patientId);

        // then
        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getVisits()).isNotEmpty();
        assertThat(patientTO.getVisits().get(0).getDoctor().getFirstName()).isEqualTo("Jan");
        assertThat(patientTO.getIsAdult()).isTrue();
    }
}
