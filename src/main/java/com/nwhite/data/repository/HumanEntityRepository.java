package com.nwhite.data.repository;

import com.nwhite.data.entity.HumanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HumanEntityRepository extends JpaRepository<HumanEntity, Integer> {
    @Query("SELECT h FROM HumanEntity h " +
            "WHERE h.birthday BETWEEN :startDate AND :endDate " +
            "AND  h.qualification = 'ENGINEER' " +
             "AND  h.nationality IS NOT NULL"
    )
    List<HumanEntity> findEngineerUsersWithNationality(@Param("startDate") LocalDate startDate,
                                                       @Param("endDate") LocalDate endDate);
}
