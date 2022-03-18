package com.kjwon.myblog.course.repository;

import com.kjwon.myblog.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<List<Course>> findByCategoryeeId(long categoryId);
    List<Course> findByWriter(String writer);

    List<Course> findByCategoryeeIdAndWriter(long categoryId, String writer);

    @Query("SELECT distinct c FROM Course c join fetch c.category")
    public List<Course> findAllBlogAndCategory();

}
