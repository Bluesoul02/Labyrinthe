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

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0,0,screen.width, screen.height);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(new GameContainer(jeu.getPlateau(), suppl));
        this.add(jeu.getInfosJoueurs());
        setVisible(true);
    }
}
