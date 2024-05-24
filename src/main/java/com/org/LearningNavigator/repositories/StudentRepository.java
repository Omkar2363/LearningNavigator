package com.org.LearningNavigator.repositories;

import com.org.LearningNavigator.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


//@RepositoryRestResource(collectionResourceRel = "students", path = "students")
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
