package com.tp1.TP1.Controllers;

import com.tp1.TP1.Repositories.ProfessorRepository;
import com.tp1.TP1.Repositories.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfessorController {
    private ProfessorRepository professorRepository;

    public ProfessorController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @GetMapping("/professor")
    public String getProfessorsList(Model model){
        model.addAttribute("professors", professorRepository.findAll());
        return "professor";
    }
}
