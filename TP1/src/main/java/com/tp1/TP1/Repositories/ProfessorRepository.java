package com.tp1.TP1.Repositories;

import com.tp1.TP1.Entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
