package com.company;

public class Door
{
    private final int id;
    private boolean isOpened = true;
    
    public Door(int id) 
    {
        this.id = id;
    }
    
    public void open() {
        System.out.println("> Opening the door...");
        setIsOpened(true);
    }
    
    public void close() {
        System.out.println("> Closing the door...");
        setIsOpened(false);
    }

    public void setIsOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }
    
    public boolean isOpened() {
        return this.isOpened;
    }

    public int getId() {
        return id;
    }
}
