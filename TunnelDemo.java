import javax.swing.JFrame;

public class TunnelDemo{

    public static void main(String[] args){

        // It could be bigger but idk
        int width = 800;
        int height = 600;

        // This is for initializing the window
        JFrame window = new JFrame ("Tunnel Effect");

        RenderPanel panel = new RenderPanel(width, height);
        window.add(panel);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(width, height);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}