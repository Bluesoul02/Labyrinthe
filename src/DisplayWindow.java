import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

public class DisplayWindow extends JFrame {

    private static final long serialVersionUID = -6870797015186432927L;

    public DisplayWindow(Plateau plateau) {
        super("labyrinthe");

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);
        JPanel gameContainer = new GameContainer(plateau);
        setContentPane(gameContainer);
        setSize(Toolkit.getDefaultToolkit().getScreenSize().getSize());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }
}
