package com.microservice.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.course.client.StudentClient;
import com.microservice.course.dto.StudentDTO;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.model.Course;
import com.microservice.course.repository.ICourseRepository;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService{

    @Autowired
    private ICourseRepository iCourseRepository;

    @Autowired
    private StudentClient studentClient;

    @Override
    public List<Course> findAll() {
        return (List<Course>) iCourseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return iCourseRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
        iCourseRepository.save(course);
    }

    @Override
    public void deleteById(Long id){
        iCourseRepository.deleteById(id);
    }

    @Override
    public StudentByCourseResponse findStudentsByIdCourse(Long idCourse){
        //Consultar el curso
        //Porque devuelve un optional
        Course course = iCourseRepository.findById(idCourse).orElse(new Course());

        //Obtener los estudiantes que estan en el curso obtenido
        List<StudentDTO> studentDTOList = studentClient.findAllStudentByCourse(idCourse);


        return StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .studentDTOList(studentDTOList)
                .build();
    }
}
