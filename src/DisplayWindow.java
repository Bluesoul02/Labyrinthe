import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.io.IOException;

public class DisplayWindow extends JFrame {

    private static final long serialVersionUID = -6870797015186432927L;

    public DisplayWindow(JeuImpl jeu, CouloirMobile suppl) throws IOException {
        super("Labyrinthe");

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize().getSize();
        setSize((int) screen.getWidth(), (int) screen.getHeight() - 30);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //getContentPane().setBackground(new Color(232, 164, 130));

        setContentPane(new GameContainer(jeu.getPlateau(), suppl));
        this.add(new InfosJoueurs(jeu));
        setVisible(true);
    }
}
