package com.chinguyen.blogdemo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table()
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cateName;

    public Category() {
    }

    public Category(Long id, String cateName) {
        this.id = id;
        this.cateName = cateName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
