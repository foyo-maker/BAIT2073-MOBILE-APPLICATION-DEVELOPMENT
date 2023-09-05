package com.example.bait2073mobileapplicationdevelopment.entities;

public class Symptom {
    String symptomName;
    int image;

    public Symptom(String symptomName, int image) {
        this.symptomName = symptomName;
        this.image = image;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public int getImage() {
        return image;
    }
}
