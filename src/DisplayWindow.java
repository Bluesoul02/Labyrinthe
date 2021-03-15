import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.io.IOException;

public class DisplayWindow extends JFrame {

    private static final long serialVersionUID = -6870797015186432927L;

    public DisplayWindow(Plateau plateau) throws IOException {
        super("Labyrinthe");

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize().getSize();
        setSize((int)screen.getWidth(), (int)screen.getHeight()-30);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        /*
        getContentPane().setPreferredSize(new Dimension(700,700));
        getContentPane().setMaximumSize(new Dimension(700,700));
        getContentPane().setMinimumSize(new Dimension(700,700));
        
        pack();
        */
        JPanel gc = new GameContainer(plateau);
        gc.setSize(new Dimension(700,700));
        setContentPane(new JPanel());
        getContentPane().add(gc);
        revalidate();
        gc.repaint();
        
        setVisible(true);
    }
}
