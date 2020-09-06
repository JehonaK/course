package com.course.repository;

import com.course.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, String> {

    @Query(value = "SELECT * FROM user WHERE id IN (?1)", nativeQuery = true)
    List<UserEntity> findBatchOfUsersByIdList(List<String> idList);

}
