package kr.ac.mjc.ict2007261051.dto;

public class BookLocationDto {

    private String callNo;
    private CirculationState circulationState;
    private LocationDto location;

    public String getCallNo() {
        return callNo;
    }

    public void setCallNo(String callNo) {
        this.callNo = callNo;
    }

    public CirculationState getCirculationState() {
        return circulationState;
    }

    public void setCirculationState(CirculationState circulationState) {
        this.circulationState = circulationState;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }
}
