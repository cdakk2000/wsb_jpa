package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.OptimisticLockException;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
public class VisitDaoTest {

    @Autowired
    private VisitDao visitDao;

    @Test
    public void testFindVisitsByPatientId() {
        // given
        Long patientId = 1L;

        // when
        List<VisitEntity> visits = visitDao.findVisitsByPatientId(patientId);

        // then
        assertThat(visits).isNotEmpty();
        assertThat(visits.get(0).getPatient().getId()).isEqualTo(patientId);
    }

    @Test
    @Transactional
    public void testOptimisticLockOnVisit() {

        VisitEntity visit = visitDao.findById(1L).orElseThrow();
        visit.setDescription("Updated Description");

        VisitEntity anotherVisitInstance = visitDao.findById(1L).orElseThrow();
        anotherVisitInstance.setDescription("Conflicting Description");
        visitDao.save(anotherVisitInstance);

        assertThrows(OptimisticLockException.class, () -> {
            visitDao.save(visit);
        });
    }

}

