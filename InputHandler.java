import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import objectdata.*;
import objectdata.Point;
import rasterop.StatPanel;

public class InputHandler {
    private List<Point> polygonVertexPoints;
    private ArrayList<Point> draggedVertexPoints = new ArrayList<>();
    private List<Line> lineArrayList;
    private LineUtilities lineUtilities;
    private int pixelSize;
    private Point startPoint;
    private RasterPanel rasterPanel;
    StatPanel statPanel = new StatPanel();
    private int vertexCount = 0;
    private Optional<Line> lineInstance = Optional.empty();

    public InputHandler(RasterPanel rasterPanel, List<Point> polygonVertecesList, List<Line> lineArrayList, LineUtilities lineUtilities, int pixelSize) {
        this.rasterPanel = rasterPanel;
        this.polygonVertexPoints = polygonVertecesList;
        this.lineArrayList = lineArrayList;
        this.lineUtilities = lineUtilities;
        this.pixelSize = pixelSize;

        rasterPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseReleased(e);
                statPanel.updatePointCount(vertexCount + polygonVertexPoints.size());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClicked(e);
            }
        });

        rasterPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                handleMouseMoved(e);
            }
        });

        rasterPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                handleKeyTyped(e);
            }
        });
    }

    private void handleMousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());
    }

    private void handleMouseReleased(MouseEvent e) {
        objectdata.Point endPoint = new objectdata.Point(e.getX(), e.getY());

        if (startPoint.getY() != endPoint.getY() && startPoint.getX() != endPoint.getX()) {
            draggedVertexPoints.add(startPoint);
            draggedVertexPoints.add(endPoint);

            Line lineInstance = lineUtilities.createLine(startPoint, endPoint, pixelSize);
            lineArrayList.add(lineInstance);
            for (int i = 0; i < lineInstance.getPoints().size(); i++) {
                int x = lineInstance.getPoints().get(i).getX();
                int y = lineInstance.getPoints().get(i).getY();
                rasterPanel.setPixel(x, y, Color.yellow.getRGB());
            }
        } else {
            draggedVertexPoints.add(startPoint);
        }

        rasterPanel.setPixel(startPoint.getX(), startPoint.getY(), Color.red.getRGB());
        rasterPanel.setPixel(endPoint.getX(), endPoint.getY(), Color.red.getRGB());


    }


    private void handleMouseClicked(MouseEvent e) {
        Point currentPoint = new Point(e.getX(), e.getY());

        int index = polygonVertexPoints.size() - 1;
        Optional<Point> lastPoint = index >= 0 ? Optional.of(polygonVertexPoints.get(index)) : Optional.empty();
        polygonVertexPoints.add(currentPoint);
        if (lastPoint.isPresent()) {
            lineInstance = Optional.of(lineUtilities.createLine(currentPoint, lastPoint.get(), pixelSize));

            if (lineInstance.isPresent()) {

                for (Point point : lineInstance.get().getPoints()) {

                    int x = point.getX();
                    int y = point.getY();

                    rasterPanel.setPixel(x,y, Color.green.getRGB());
                }
                lineArrayList.add(lineInstance.get());
                lineInstance = Optional.empty();
            }

        } else {
            System.out.println("nefunguje");
        }
      rasterPanel.statPanel.updatePointCount(vertexCount + polygonVertexPoints.size());


    }

    private void handleMouseDragged(MouseEvent e) {
        Point potentialEndPoint = new Point(e.getX(), e.getY());

        rasterPanel.clearTemporaryLines(lineInstance);
        lineInstance = Optional.of(lineUtilities.createLine(startPoint, potentialEndPoint, pixelSize));


        if (lineInstance.isPresent()) {
            clearTemporaryLine(lineInstance.get());
            for (Point point : lineInstance.get().getPoints()) {
                int x = point.getX();
                int y = point.getY();
                rasterPanel.setPixel(x, y, Color.blue.getRGB());
            }
        }
    }

    private void handleMouseMoved(MouseEvent e) {

    }

    private void handleKeyTyped(KeyEvent e) {

    }

    private void clearTemporaryLine(Line line) {
        if (line != null) {
            for (Point point : line.getPoints()) {
                int x = point.getX();
                int y = point.getY();
                rasterPanel.setPixel(x, y, 0x000000);
            }
        }
    }
}
