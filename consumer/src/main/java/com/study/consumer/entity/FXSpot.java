package com.study.consumer.entity;

//import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "fx_spot")
public class FXSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currencyFrom")
    private String currencyFrom;

    @Column(name = "currencyTo")
    private String currencyTo;

    @Column(name = "fx_rate")
    private BigDecimal fxRate;

    @Column(name = "observed_at")
    private LocalDateTime observedAt;

    @ElementCollection
    @Column(name = "labels")
    private List<String> labels;

    public FXSpot(String currencyFrom, String currencyTo, BigDecimal fxRate, LocalDateTime observedAt, List<String> labels) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.fxRate = fxRate;
        this.observedAt = observedAt;
        this.labels = labels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public BigDecimal getFxRate() {
        return fxRate;
    }

    public void setFxRate(BigDecimal fxRate) {
        this.fxRate = fxRate;
    }

    public LocalDateTime getObservedAt() {
        return observedAt;
    }

    public void setObservedAt(LocalDateTime observedAt) {
        this.observedAt = observedAt;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
