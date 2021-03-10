import java.awt.*;
import javax.swing.*;

public class GameContainer extends JPanel {

    private static final long serialVersionUID = -1431610534661838728L;

    public GameContainer(Plateau plateau) {
        this.setLayout(new GridLayout(7, 7));
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                JButton b = new JButton(plateau.getCouloir(new Position(i, j)).toString());
                b.setVisible(true);
                this.add(b);
            }
        }
        this.setVisible(true);
    }
}