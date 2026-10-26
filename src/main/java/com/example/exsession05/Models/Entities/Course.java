package com.example.exsession05.Models.Entities;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", length = 100, nullable = false)
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CourseStatus status;
    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Intructor instructor;
}