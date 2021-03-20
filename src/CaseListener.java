import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class CaseListener implements ActionListener {

    private Couloir couloir;
    private JeuImpl jeu;

    public CaseListener(Couloir couloir, JeuImpl jeu) {
        this.couloir = couloir;
        this.jeu = jeu;
    }

    public void actionPerformed(ActionEvent e) {
        // si couloirSuppl
        if (couloir.getClass() == CouloirMobile.class && ((CouloirMobile) couloir).isPosee() == false) {
            int rot = couloir.getOrientation().getRotation();
            ((CouloirMobile) couloir).setOrientation(Orientation.values()[rot < 3 ? rot + 1 : 0]);
            // partie graphique
            ImageIcon icon = (ImageIcon) ((JButton) couloir).getIcon();
            ((JButton) couloir).setIcon(null);
            BufferedImage buf = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = buf.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            buf = GameContainer.rotateNTime(buf, 1);
            ((JButton) couloir).setIcon(new ImageIcon(buf));
            // JOptionPane.showMessageDialog(null, couloir.getOrientation().toString(),
            // "CouloirSuppl", 1);
        } else {
            // test
            JOptionPane.showMessageDialog(null,
                    this.couloir.getPosition().x() + "," + this.couloir.getPosition().y() + ", "
                            + this.couloir.toString()
                            + (this.couloir.getPions().size() != 0 ? this.couloir.getPions().get(0) : "pas de pions"),
                    couloir.getClass().toString(), 1);

            // deplacement pion
            if (!jeu.phaseCouloir) {
                Objectif objectif = jeu.getCurrentPlayer().getPion().deplacer(couloir.getPosition());
                if (objectif == jeu.getCurrentPlayer().getObjectifs().peek()) {
                    jeu.getCurrentPlayer().getObjectifs().pop();
                }
                JOptionPane.showMessageDialog(null, jeu.getCurrentPlayer().getPion().getPositionCourante().x() + ", "
                        + jeu.getCurrentPlayer().getPion().getPositionCourante().y());
            }

            // Pose de couloir
            if (jeu.phaseCouloir) {
                PositionInsertion positionInsertion = null;
                for (PositionInsertion posI : PositionInsertion.values()) {
                    if (posI.getPosition().x() == couloir.getPosition().x()
                            && posI.getPosition().y() == couloir.getPosition().y())
                        positionInsertion = posI;
                }
                jeu.modifierCouloirs(positionInsertion, jeu.getSupplementaire().getOrientation());
                ((GameContainer) ((JButton) couloir).getParent().getParent())
                        .enableComponents(((JButton) couloir).getParent().getComponents(), true);
                jeu.phaseCouloir = false;
            }
        }
    }
}
