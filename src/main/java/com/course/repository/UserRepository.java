package com.course.repository;

import com.course.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<User, String> {

    @Query(value = "SELECT * FROM user WHERE id IN (?1)", nativeQuery = true)
    List<User> findBatchOfUsersByIdList(List<String> idList);

}
