package com.tp1.TP1.Controllers;

import com.tp1.TP1.Repositories.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student")
    public String getStudentsList(Model model){
        model.addAttribute("students", studentRepository.findAll());
        return "student";
    }
}
