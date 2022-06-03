package com.company;

public class Floor
{
    private int level;
    private String specialName;
    
    public Floor(int level) {
        setLevel(level);
    }
    
    public String toString() {
        if (this.specialName != null) {
            return this.specialName;
        }
        return "Floor No. " + this.level;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public void setLevel (int level) {
        this.level = level;
    }
    
    public String getSpecialName() {
        return this.specialName;
    }
    
    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }
}
