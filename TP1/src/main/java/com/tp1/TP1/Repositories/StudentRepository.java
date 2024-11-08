package com.tp1.TP1.Repositories;

import com.tp1.TP1.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
