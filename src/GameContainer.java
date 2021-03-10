import java.awt.*;
import javax.swing.*;

public class GameContainer extends JPanel {

    private static final long serialVersionUID = -1431610534661838728L;

    public GameContainer() {
        this.setLayout(new GridLayout(7, 7));
        for (int i = 0; i < 7; i++) {
            for (int j = 1; j <= 7; j++) {
                JButton b = new JButton("Case " + (i * 7 + j));
                b.setVisible(true);
                this.add(b);
            }
        }
        this.setVisible(true);
    }
}