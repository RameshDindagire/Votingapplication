package com.example.demo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;

@Document(collection = "dynamicCollections")
@Data
public class DynamicData {
    @Id
    private String id;
    private String fieldName;
    private String fieldValue;

    // Getters and setters
}