package com.anigenero.sandbox.poker.dao.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "story_point")
public class StoryPointEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(nullable = false, updatable = false)
    private Integer value;
    @Column(updatable = false)
    private String symbol;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
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

        StoryPointEntity that = (StoryPointEntity) object;

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
