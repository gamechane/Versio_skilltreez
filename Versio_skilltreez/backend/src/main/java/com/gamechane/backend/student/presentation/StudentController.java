package com.gamechane.backend.student.presentation;

import com.gamechane.backend.student.service.StudentService;
import com.gamechane.backend.security.user.presentation.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/")
public class StudentController {
    private final StudentService studentService;
//    private final SkillTreeService skillTreeService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/{username}")
    public UserDTO findStudentByUsername(@PathVariable String username) throws Exception{
        return studentService.getUserByUsername(username);
    }
}
