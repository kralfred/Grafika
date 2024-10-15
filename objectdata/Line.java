package objectdata;

import java.awt.*;
import java.util.ArrayList;


public class Line {

    public ArrayList<Point> points;

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public Line(ArrayList<Point> points) {
      this.points = points;

    }
}







