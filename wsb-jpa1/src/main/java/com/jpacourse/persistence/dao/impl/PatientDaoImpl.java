package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PatientDaoImpl implements PatientDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addVisitToPatient(Long patientId, Long doctorId, java.time.LocalDateTime visitDate, String description) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient not found with ID: " + patientId);
        }


        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor not found with ID: " + doctorId);
        }


        VisitEntity visit = new VisitEntity();
        visit.setDoctor(doctor);
        visit.setPatient(patient);
        visit.setTime(visitDate);
        visit.setDescription(description);


        patient.getVisits().add(visit);


        entityManager.merge(patient);
    }

    @Override
    public PatientEntity save(PatientEntity entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }
    }


    @Override
    public PatientEntity getOne(Long id) {
        return null;
    }

    @Override
    public PatientEntity findOne(Long id) {
        return entityManager.find(PatientEntity.class, id);
    }


    @Override
    public List<PatientEntity> findAll() {
        return entityManager.createQuery("SELECT p FROM PatientEntity p", PatientEntity.class).getResultList();
    }


    @Override
    public PatientEntity update(PatientEntity entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Cannot update entity without ID");
        }
        return entityManager.merge(entity);
    }


    @Override
    public void delete(PatientEntity entity) {
        if (entity != null && entity.getId() != null) {
            PatientEntity managedEntity = entityManager.merge(entity);
            entityManager.remove(managedEntity);
        } else {
            throw new IllegalArgumentException("Cannot delete null or transient entity");
        }
    }


    @Override
    public void delete(Long id) {
        PatientEntity patient = entityManager.find(PatientEntity.class, id);
        if (patient != null) {
            entityManager.remove(patient);
        } else {
            throw new IllegalArgumentException("Patient not found with ID: " + id);
        }
    }


    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM PatientEntity").executeUpdate();
    }


    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(p) FROM PatientEntity p", Long.class).getSingleResult();
    }


    @Override
    public boolean exists(Long id) {
        return entityManager.find(PatientEntity.class, id) != null;
    }

}
