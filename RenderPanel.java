
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
        for(int i = 0; i < pixels.length; i++){

            pixels[i] = 0x82C8E5;
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