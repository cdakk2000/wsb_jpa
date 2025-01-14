package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.OptimisticLockException;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Test
    public void testFindPatientsByLastName() {
        // given
        String lastName = "Nowak";

        // when
        List<PatientEntity> patients = patientDao.findPatientsByLastName(lastName);

        // then
        assertThat(patients).isNotEmpty();
        assertThat(patients.get(0).getLastName()).isEqualTo(lastName);
    }

    @Test
    public void testFindPatientsWithMoreThanXVisits() {
        // given
        int visitCount = 2;

        // when
        List<PatientEntity> patients = patientDao.findPatientsWithMoreThanXVisits(visitCount);

        // then
        assertThat(patients).isNotEmpty();
        assertThat(patients.get(0).getVisits().size()).isGreaterThan(visitCount);
    }

    @Test
    public void testFindPatientsBornBefore() {
        // given
        LocalDate date = LocalDate.of(2000, 1, 1);

        // when
        List<PatientEntity> patients = patientDao.findPatientsBornBefore(date);

        // then
        assertThat(patients).isNotEmpty();
        assertThat(patients.get(0).getDateOfBirth()).isBefore(date);
    }


    @Test
    @Transactional
    public void testOptimisticLockOnPatient() {

        PatientEntity patient = patientDao.findOne(1L);
        patient.setFirstName("NewName");


        PatientEntity anotherPatientInstance = patientDao.findOne(1L);
        anotherPatientInstance.setFirstName("OtherName");
        patientDao.update(anotherPatientInstance);


        assertThrows(OptimisticLockException.class, () -> {
            patientDao.update(patient);
        });
    }

}
