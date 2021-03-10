import java.awt.*;
import javax.swing.*;
import java.util.List;

public class GameContainer extends JPanel {

    private static final long serialVersionUID = -1431610534661838728L;

    public GameContainer(Plateau plateau) {
        List<Couloir> couloirs = plateau.getCouloirs();
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