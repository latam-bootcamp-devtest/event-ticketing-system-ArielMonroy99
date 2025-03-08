package com.example.test_jala.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "events")
@Getter
@Setter
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "event_id")
    private Long id;
    private String name;
    private Date date;
    private Integer availableSeats;
    private Integer status;
}
