package objectdata;

import java.util.ArrayList;

public class LineUtilities {

    //pro rasterizaci usečky jsem použil Bresenhamův algoritmus kterej je rychlej, jednoduchej a nevyužívá moc paměti ale za to má limitované Převzorkování (antialiasing) jelikož umí
    //pouze s int čísly a proto není moc ufektivní k vykreslování oblouků.
    public Line createLine(Point p1, Point p2, int pixelSize) {
        int x1 = p1.getX();
        int y1 = p1.getY();

        int x2 = p2.getX();
        int y2 = p2.getY();

        if (x1 > x2) {
            int save = x2;
            x2 = x1;
            x1 = save;
            int saveY = y2;
            y2 = y1;
            y1 = saveY;
        }

        x1 = Math.round(x1 / pixelSize) * pixelSize;
        x2 = Math.round(x2 / pixelSize) * pixelSize;


        float k = (float) (y2 - y1) / (x2 - x1);
        float q = y1 - k * x1;

        ArrayList<Point> pointsArrayList = new ArrayList();
        if (x2 == x1) {
            for (int y = y1; y <= y2; y++) {
                Point verticalPoint = new Point(x1, y);
                pointsArrayList.add(verticalPoint);
            }

        } else {

            for (int x = x1; x <= x2; x++) {

                int y = (int) (k * x + q);

                Point pointInstance = new Point(x,y);
                pointsArrayList.add(pointInstance);

            }
        }
        Line line = new Line(pointsArrayList);

        return line;
    }}


