package com.timur.AWS.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "events")
public class Event{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created")
    private String created;

    @Column(name = "updated")
    private String updated;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ToString.Exclude
    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "file_id")
    private File file;
}
