package com.renatobonfim.projectmanagerbackend.domain;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "_client")
public class Client {

    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @Column(name = "id", unique = true, updatable = false)
    private String id;

    String name;
    String description;

    //@OneToMany( fetch = FetchType.LAZY )
    //List<Project> projectList = new ArrayList<>();
}
