import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaseListener implements ActionListener {

    private Couloir couloir;
    private JeuImpl jeu;

    public CaseListener(Couloir couloir, JeuImpl jeu) {
        this.couloir = couloir;
        this.jeu = jeu;
    }

    public void actionPerformed(ActionEvent e) {
        jeu.getCurrentPlayer().getPion().deplacer(couloir.getPosition());
    }
}
