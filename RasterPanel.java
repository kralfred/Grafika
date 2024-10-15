import objectdata.Line;
import objectdata.LineUtilities;
import objectdata.Point;
import rasterop.StatPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Optional;





public class RasterPanel extends JPanel {
    private int width;
    private int height;
    private int[][] pixels;
    private ArrayList<objectdata.Point> draggedVertexPoints = new ArrayList<>();
    private ArrayList<objectdata.Line> lineArrayList = new ArrayList<>();
    private ArrayList<Optional<Line>> polygonLineArrayList = new ArrayList<Optional<Line>>();
    private ArrayList<objectdata.Point> polygonVerteciesArrayList = new ArrayList<>();
    private LineUtilities lineUtilities = new LineUtilities();
    private Optional<Line> lineInstance = Optional.empty();
    private Optional<Line> polygonLineInstance = Optional.empty();
    StatPanel statPanel = new StatPanel();
    private int pixelSize = 9;

    InputHandler inputHandler = new InputHandler(this, polygonVerteciesArrayList, lineArrayList, lineUtilities, pixelSize);

     public RasterPanel(int width, int height){
         this.width = width;
         this.height = height;
         this.pixels = new int[width][height];


         setFocusable(true);
         requestFocus();
         setLayout(null);
         add(statPanel);



         addKeyListener(new KeyAdapter() {
             @Override
             public void keyTyped(KeyEvent e) {
                 super.keyTyped(e);
                 if(e.getKeyChar() == '+' && pixelSize < 14){
                     pixelSize += 4;
                     statPanel.updatePixelSize(pixelSize);
                     repaint();
                 }
                 if(e.getKeyChar() == '-' && pixelSize > 1){
                     pixelSize -= 4;
                     statPanel.updatePixelSize(pixelSize);
                     repaint();

                 }
                 if(e.getKeyChar() == 'c'){
                     clear();
                 }
             }
         });



         addMouseMotionListener(new MouseMotionAdapter() {
             @Override
             public void mouseMoved(MouseEvent e) {
                 super.mouseMoved(e);
                 statPanel.updateMousePosition(e.getX()/pixelSize, e.getY()/pixelSize);
             }
         });


     }

   @Override
    public void paintComponent(Graphics g){
         super.paintComponent(g);
         Graphics2D g2d = (Graphics2D) g;


         for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                int color = pixels[x][y];
                g2d.setColor(new Color(color));
                g2d.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
            }
         }


   }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setPixel(int x, int y, int color){
         if(x >= 0 && x < width && y >= 0 && y < height){
             pixels[x/pixelSize][y/pixelSize] = color;
             repaint();
         }

   }
     public void clear(){
     for(int x = 0; x < width; x++){
         for(int y = 0; y < height; y++){
             pixels[x][y] = 0x000000;
         }
     }
     polygonLineArrayList.clear();
     polygonVerteciesArrayList.clear();
     draggedVertexPoints.clear();
     statPanel.updatePointCount(0);
     repaint();
     }
     public void clearTemporaryLines(Optional<Line> line){
         if(line.isPresent()){


         for(Point point : line.get().getPoints()){
             int x = point.getX();
             int y = point.getY();
             setPixel(x, y, 0x000000);
         }
         repaint();
     }
     }




}
