package com.renatobonfim.projectmanagerbackend.domain;


import com.renatobonfim.projectmanagerbackend.util.Constants;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@NamedQuery(name = "Project.findAllProjectsByClient", query = "SELECT p FROM Project p WHERE p.client =: client")

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_project")
public class Project {

    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @Column(name = "id", unique = true, updatable = false)
    private String id;

    String name;
    String description;
    private Date startDate;
    private Date endDate;
    private Constants.STATUS status = Constants.STATUS.NEW;

    @ManyToOne
    @JoinColumn(name = "client_fk", nullable = false)
    Client client;

    //@OneToMany
    //List<Activity> activityList = new ArrayList<>();
}
