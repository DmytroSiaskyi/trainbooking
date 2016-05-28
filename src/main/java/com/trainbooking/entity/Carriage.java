package com.trainbooking.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "carriage")
public class Carriage implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="number", nullable=false)
    private Integer number;
    @Column(name ="train_number",nullable=false)
    private Integer trainNumber;
    @Column(name="type", length = 24, nullable=false)
    private String type;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(Integer trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
