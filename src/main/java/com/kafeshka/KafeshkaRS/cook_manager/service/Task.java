package com.kafeshka.KafeshkaRS.cook_manager.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Task {
    private String requiredExpertise;
    private String description;
    private int complexity; // Represented by a numeric value (e.g., 1 to 10)
    private boolean urgent;
}

