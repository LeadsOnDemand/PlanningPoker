package com.anigenero.sandbox.poker.dao.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "poker_card")
public class PokerCardEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(nullable = false, updatable = false)
    private Double value;
    @Column(updatable = false)
    private String symbol;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        } else if (object == null || getClass() != object.getClass()) {
            return false;
        }

        PokerCardEntity that = (PokerCardEntity) object;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        } else if (value != null ? !value.equals(that.value) : that.value != null) {
            return false;
        }

        return symbol != null ? symbol.equals(that.symbol) : that.symbol == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
