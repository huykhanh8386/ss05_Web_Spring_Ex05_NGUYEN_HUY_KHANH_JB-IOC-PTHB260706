package com.example.exsession05.Models.Repositories;

import com.example.exsession05.Models.Entities.Intructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Intructor, Long> {
}
