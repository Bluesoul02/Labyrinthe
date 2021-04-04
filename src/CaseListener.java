import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CaseListener implements ActionListener {

    private Couloir couloir;
    private JeuImpl jeu;

    public CaseListener(Couloir couloir, JeuImpl jeu) {
        this.couloir = couloir;
        this.jeu = jeu;
    }

    public void actionPerformed(ActionEvent e) {
        JPanel labyrinthe = (JPanel) ((JButton) couloir).getParent();
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
            // deplacement pion
            Couloir oldCouloir = jeu.getPlateau().getCouloir(jeu.getCurrentPlayer().getPion().getPositionCourante());
            if (!jeu.phaseCouloir) {
                Pion pion = jeu.getCurrentPlayer().getPion();
                Objectif objectif = pion.deplacer(couloir.getPosition());
                if (objectif == jeu.getCurrentPlayer().getObjectifs().peek()) {
                    jeu.getCurrentPlayer().getObjectifs().pop();
                }
                if (oldCouloir != couloir) {
                    // on redessine l'ancien couloir et le nouveau (destination)
                    try {
                        drawCouloir(oldCouloir);
                        drawCouloir(couloir);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                // JOptionPane.showMessageDialog(null,
                // jeu.getCurrentPlayer().getPion().getPositionCourante().x() + ", "
                // + jeu.getCurrentPlayer().getPion().getPositionCourante().y());
            }

            // Pose de couloir
            if (jeu.phaseCouloir) {
                JButton oldSuppl = jeu.getSupplementaire();
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
                ((GameContainer) labyrinthe.getParent()).updateLabyrinthe(labyrinthe, jeu.getPlateau(),
                        jeu.getSupplementaire(), oldSuppl);
            }
            // test
            JOptionPane.showMessageDialog(null,
                    this.couloir.getPosition().x() + "," + this.couloir.getPosition().y() + ", "
                            + this.couloir.toString()
                            + (!this.couloir.getPions().isEmpty() ? this.couloir.getPions().get(0) : "pas de pions"),
                    couloir.getClass().toString(), 1);
        }
    }

    private void drawCouloir(Couloir couloir) throws IOException {
        BufferedImage buf = null;
        BufferedImage deco = null;
        ((JButton) couloir).setIcon(null);
        for (Couleur couleur : Couleur.values()) {
            if (couleur.getPositionInitiale().x() == couloir.getPosition().x()
                    && couleur.getPositionInitiale().y() == couloir.getPosition().y()) {
                deco = ImageIO.read(new File("img/couleurs/" + couleur.toString() + ".png"));
            }
        }
        buf = ImageIO.read(new File("img/formes/" + couloir.getForme().toString() + ".png"));
        buf = GameContainer.rotateNTime(buf, couloir.getOrientation().getRotation());
        if (couloir.getObjectif() != null)
            deco = ImageIO.read(new File("img/objectifs/" + couloir.getObjectif().toString() + ".png"));
        if (deco != null)
            buf = GameContainer.append(buf, deco);
        List<Pion> pions = couloir.getPions();
        Couleur couleurPion = null;
        Position posI;
        BufferedImage player;
        for (Pion pion : pions) {
            posI = pion.getPositionInitiale();
            player = null;
            for (Couleur couleur : Couleur.values()) {
                if (posI.x() == couleur.getPositionInitiale().x() && couleur.getPositionInitiale().y() == posI.y())
                    couleurPion = couleur;
            }
            if (pion.getPositionCourante().x() == couloir.getPosition().x()
                    && pion.getPositionCourante().y() == couloir.getPosition().y()) {
                player = ImageIO.read(new File("img/pion/" + couleurPion.toString() + ".png"));
                if (player != null)
                    buf = GameContainer.append(buf, player);
            }
        }
        ((JButton) couloir).setIcon(new ImageIcon(buf));
        ((JButton) couloir).setDisabledIcon(new ImageIcon(buf));
    }
}
