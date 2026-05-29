
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class RenderPanel extends JPanel {

    private BufferedImage image;
    private int[] pixels;
    private int[] texture;
    private int[] distance;
    private int[] angle;

    // This initializes the panel, this works like a framebuffer
    // I store everything on the array as RGB color pixel data, as I want to just use the CPU
    // The point of this is not using the GPU or modern libraries

    public RenderPanel(int width, int height) throws IOException{

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();     // All this is just to get the raw data of every pixel

 
        // this is for reading the texture file
        // we store everything in a 1D array (the indexation should be x + y * 256)
        texture = new int[256*256];
        try{

            BufferedImage texImage = ImageIO.read(new File("texture.jpg"));
            texImage.getRGB(0, 0, 256, 256, texture, 0, 256);

        } catch(IOException e){
            e.printStackTrace();
        }

        // here we compute the lookup tables
        // basically we calculate the distance and angle from the center beforehand
        // doing this we don't have to calculate sqrt() and atan2() on the animation loop


        distance = new int[width*height];
        angle = new int[width*height];

        for(int x=0; x < width; x++){
            for(int y=0; y < height; y++){

                int index = x + y * width;

                // coeficients of the formulas are mostly random, changing them changes the effect in a way
                // Distance is the distance formula from the center
                distance[index] = (int)(32 * 256 / Math.sqrt((x - width / 2)^2+(y - height / 2)^2)) % 256;
                // Angle is the atan2 function (atan2 is just atan but doesn't mix up positives and negatives)
                angle[index] = (int)(128 * Math.atan2(y - height / 2, x - width / 2) / Math.PI);

            }
        }

    }

    @Override
    protected  void paintComponent(Graphics g){

        super.paintComponent(g);

        g.drawImage(image,0,0,null);

    }

    public int[] getPixels(){
        return pixels;
    }
}