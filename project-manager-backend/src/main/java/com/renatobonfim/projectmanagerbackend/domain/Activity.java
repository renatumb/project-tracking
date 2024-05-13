package com.renatobonfim.projectmanagerbackend.domain;

import com.renatobonfim.projectmanagerbackend.util.Constants;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@NamedQuery(name = "Activity.findAllActivityByProject", query = "SELECT a FROM Activity a WHERE a.project =: project")

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_activity")
public class Activity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String name;
    private String description;
    private LocalDate creationDate = LocalDate.now();
    private LocalDate startDate;
    private LocalDate endDate;
    private Constants.STATUS status = Constants.STATUS.NEW;

    @ManyToOne
    @JoinColumn(name = "project_fk", nullable = false)
    private Project project;
}
