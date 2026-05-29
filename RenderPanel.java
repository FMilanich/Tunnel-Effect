
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JPanel;


public class RenderPanel extends JPanel {

    private BufferedImage image;
    private int[] pixels;

    // This initializes the panel, this works like a framebuffer
    // I store everything on the array as RGB color pixel data, as I want to just use the CPU
    // The point of this is not using the GPU or modern libraries

    public RenderPanel(int width, int height){

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();     // All this is just to get the raw data of every pixel

        // Of course the test should be with hatsune miku colors lmao
       for (int x=0; x < width; x++){

            for(int y=0; y < height; y++){

                // The screen pixels are in a 1D array, and this formula is the one that converts a 2D to 1D
                int index = x + y * width;

                int red = (0x82*y) / height;
                int green = (0xC8*y) / height;
                int blue = 0xFF;

                // this is just for the format, it's a 32-bit number. each 8 bits is for red, green and blue respectively.
                int color = (red << 16) | (green << 8) | blue;

                pixels[index] = color;
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