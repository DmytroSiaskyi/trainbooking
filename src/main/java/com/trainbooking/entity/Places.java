package com.trainbooking.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "places")
public class Places implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private Integer number;
    @Column(name="tier", length = 24, nullable=false)
    private String tier;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="carriage_id", nullable = false)
    private Carriage carriage;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public Carriage getCarriage() {
        return carriage;
    }

    public void setCarriage(Carriage carriage) {
        this.carriage = carriage;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }
}
