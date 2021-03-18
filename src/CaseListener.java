import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
            ImageIcon icon = (ImageIcon) ((JButton) couloir).getIcon();
            ((JButton) couloir).setIcon(null);
            BufferedImage buf = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = buf.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            buf = GameContainer.rotateNTime(buf, couloir.getOrientation().getRotation());
            ((JButton) couloir).setIcon(new ImageIcon(buf));
            // JOptionPane.showMessageDialog(null, "rotation", "CouloirSuppl", 1);
        } else {

            jeu.getCurrentPlayer().getPion().deplacer(couloir.getPosition());
            // possibilité de faire une variable (Boolean) pour savoir dans quel partie du
            // tour (on minimise les listeners sur les boutons) nous nous trouvons, créable
            // dans joueur ou dans jeu (plus opti je pense)

            // test
            JOptionPane.showMessageDialog(null, this.couloir.getPosition().x() + "," + this.couloir.getPosition().y(),
                    couloir.getClass().toString(), 1);

        }
    }
}
