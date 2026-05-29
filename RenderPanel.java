
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

        // a quick test for coloring the screen with the middle pixel of the texture (should be beige)
        for(int i=0; i < pixels.length; i++){

            pixels[i] = texture[128 + 128 * 256];
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