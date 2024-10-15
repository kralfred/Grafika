import javax.swing.*;


public class Canvas {

    private int width = 1200;
    private int height = 800;



    Canvas(){

        RasterPanel rasterPanel = new RasterPanel(width,height);


        JFrame mainFrame = new JFrame();
        mainFrame.setVisible(true);
        mainFrame.setSize(width,height);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainFrame.add(rasterPanel);
        mainFrame.setResizable(false);
    }

}
