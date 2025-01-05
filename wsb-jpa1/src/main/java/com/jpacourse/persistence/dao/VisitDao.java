package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface VisitDao extends Dao<VisitEntity, Long> {
//}

public interface VisitDao extends JpaRepository<VisitEntity, Long> {
}
