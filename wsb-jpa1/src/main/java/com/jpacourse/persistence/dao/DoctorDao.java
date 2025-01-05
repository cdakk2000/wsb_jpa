package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorDao extends JpaRepository<DoctorEntity, Long> {
}
