package Data;

import java.io.Serializable;

public class Flight implements Serializable {
    private String flightCode;
    private String planeCode;

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    private String endStation;

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getPlaneCode() {
        return planeCode;
    }

    public void setPlaneCode(String planeCode) {
        this.planeCode = planeCode;
    }

    public Flight(String flightCode, String planeCode, String endStation) {
        this.flightCode = flightCode;
        this.planeCode = planeCode;
        this.endStation = endStation;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightCode='" + flightCode + '\'' +
                ", planeCode='" + planeCode + '\'' +
                ", endStation='" + endStation + '\'' +
                '}';
    }
}
