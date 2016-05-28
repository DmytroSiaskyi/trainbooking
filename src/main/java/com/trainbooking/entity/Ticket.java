package com.trainbooking.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "enable")
    private Boolean enabled;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "date")
    private Date date;
    @Column(name = "price", nullable = false)
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="places_id", nullable = false)
    private Places places;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="start_station_id", nullable = false)
    private Station start;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="end_station_id", nullable = false)
    private Station end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Places getPlaces() {
        return places;
    }

    public Station getStart() {
        return start;
    }

    public void setStart(Station start) {
        this.start = start;
    }

    public Station getEnd() {
        return end;
    }

    public void setEnd(Station end) {
        this.end = end;
    }

    public void setPlaces(Places places) {
        this.places = places;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
