import javax.swing.*;
import java.awt.event.*;

public class DisplayWindow extends JFrame {

    private static final long serialVersionUID = -6870797015186432927L;

    public DisplayWindow() {
        super("labyrinthe");

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        addWindowListener(l);
        setSize(200, 100);
        setVisible(true);
    }
}
