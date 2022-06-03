package com.company;

public class ExternalRequest implements Request
{
    private Direction directionToGo;
    private int sourceFloor;
    
    public ExternalRequest(Direction directionToGo, int sourceFloor) {
        setDirectionToGo(directionToGo);
        setSourceFloor(sourceFloor);
    }
    
    public void setDirectionToGo(Direction directionToGo) {
        this.directionToGo = directionToGo;
    }
    
    public Direction getDirectionToGo() {
        return this.directionToGo;
    }
    
    public void setSourceFloor(int sourceFloor) {
        this.sourceFloor = sourceFloor;
    }
    
    public int getSourceFloor() {
        return this.sourceFloor;
    }
    
    public String toString() {
        return ("Request: " + this.directionToGo
        + " from " + this.sourceFloor + " floor");
    }
}
