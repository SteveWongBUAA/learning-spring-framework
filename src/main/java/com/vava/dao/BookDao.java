package com.vava.dao;

import org.springframework.stereotype.Repository;

/**
 */
@Repository
public class BookDao {
    private String label = "1";

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "label='" + label + '\'' +
                '}';
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
