package com.backend.management.model;

import java.util.List;

public class Category {
    private String id;
    private String name;
    private List<Category> subCategories;

    public Category(String id, String name, List<Category> subCategories) {
        this.id = id;
        this.name = name;
        this.subCategories = subCategories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }
}
