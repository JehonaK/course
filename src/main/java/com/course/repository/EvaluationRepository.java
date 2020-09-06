package com.course.repository;

import com.course.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends BaseRepository<EvaluationEntity, String>{

    @Query(value = "SELECT * FROM evaluation WHERE student_id = ?1 AND activity_id IN (?2)", nativeQuery = true)
    List<EvaluationEntity> findEvaluationsByStudentIdAndActivitiesIdList(String studentId, List<String> activityIdList);

}
