import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameContainer extends JPanel {

    private static final long serialVersionUID = -1431610534661838728L;
    private static final Dimension dimsScreen = Toolkit.getDefaultToolkit().getScreenSize();

    public GameContainer(Plateau plateau, CouloirMobile suppl) throws IOException {
        //setSize(dimsScreen.width -300, dimsScreen.height-300);
        GridLayout gl = new GridLayout(7, 7);
        JPanel labyrinthe = new JPanel();
        //labyrinthe.setSize(dimsScreen.width-100, dimsScreen.height-100);
        labyrinthe.setLayout(gl);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        BufferedImage deco = null;
        Position pos = null;
        BufferedImage buf = null;
        CouloirImpl couloir = null;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                pos = new Position(j, i);
                deco = null;
                for (Couleur couleur : Couleur.values()) {
                    if (couleur.getPositionInitiale().x() == pos.x() && couleur.getPositionInitiale().y() == pos.y())
                        deco = ImageIO.read(new File("img/couleurs/" + couleur.toString() + ".png"));
                }
                couloir = (CouloirImpl) plateau.getCouloir(pos);
                couloir.setOpaque(false);
                buf = ImageIO.read(new File("img/formes/" + couloir.getForme().toString() + ".png"));
                buf = rotateNTime(buf, couloir.getOrientation().getRotation());
                if (couloir.getObjectif() != null)
                    deco = ImageIO.read(new File("img/objectifs/" + couloir.getObjectif().toString() + ".png"));
                if (deco != null)
                    buf = append(buf, deco, false);
                ImageIcon img = new ImageIcon(buf);
                couloir.setIcon(img);

                deco = new BufferedImage(151,151,BufferedImage.TYPE_INT_ARGB);
                Graphics2D graphics = deco.createGraphics();
                graphics.setColor(new Color(255,255,255,125));
                graphics.fillRect(0, 0, deco.getWidth(), deco.getHeight());

                buf = append(buf, deco, true);
                couloir.setDisabledIcon(new ImageIcon(buf));

                couloir.setBorder(emptyBorder);
                
                gl.addLayoutComponent(couloir.toString(), couloir);
                labyrinthe.add(couloir);
            }
        }
        enableComponents(labyrinthe.getComponents(), false);
        // this.add(new JScrollPane(labyrinthe));
        labyrinthe.setVisible(true);
        labyrinthe.revalidate();
        this.add(labyrinthe);

        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));

        JLabel label = new JLabel("Pièce supplémentaire : ");
        label.setFont(new Font("Comic sans MS", Font.BOLD, 20));
        this.add(label);

        suppl.setBorder(emptyBorder);
        buf = ImageIO.read(new File("img/formes/" + suppl.getForme().toString() + ".png"));
        buf = rotateNTime(buf, suppl.getOrientation().getRotation());
        suppl.setIcon(new ImageIcon(buf));
        suppl.setBounds(suppl.getX() + 100, suppl.getY(), suppl.getWidth(), suppl.getHeight());
        suppl.setEnabled(true);
        this.add(suppl);
        // 7 * i + j
    }

    // mets a jour le positionnements des composants du labyrinthes
    public void updateLabyrinthe(JPanel labyrinthe, Plateau plateau, JButton suppl, JButton oldSuppl) {
        Component[] buttons = labyrinthe.getComponents();
        Position pos = null;
        this.remove(oldSuppl);
        suppl.setBounds(oldSuppl.getBounds());
        suppl.setEnabled(true);
        this.add(suppl);
        labyrinthe.removeAll();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                pos = new Position(j, i);
                Couloir couloir = plateau.getCouloir(pos);
                labyrinthe.add((JButton) couloir);
            }
        }
        labyrinthe.validate();
        enableComponents(buttons, true);
    }

    // active tout les composants, a appeller suite a la fin de la phase couloir
    public static void enableComponents(Component[] comp, Boolean bool) {
        for (Component component : comp) {
            component.setEnabled(bool);
        }
    }

    // active tout les boutons correspondant, avec la position a eviter donnée en
    // param (null si 1er tour) et le plateau (pour l'acces au couloirs)
    public static void enablePositionsInsertions(PositionInsertion positionI, Plateau plateau) {
        for (PositionInsertion positionInser : PositionInsertion.values()) {
            if (positionI == null || positionInser != positionI)
                ((JButton) plateau.getCouloir(positionInser.getPosition())).setEnabled(true);
        }
    }

    // concatene deux images ensemble avec img2 dessine par dessus img1
    public static BufferedImage append(Image img1, Image img2, boolean isDisabled) {
        BufferedImage buf = null;
        if (img1 != null && img2 != null) {
            int w1 = img1.getWidth(null);
            int h1 = img1.getHeight(null);
            int w2 = img2.getWidth(null);
            int h2 = img2.getHeight(null);

            buf = new BufferedImage(w1, h1, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = buf.createGraphics();
            g2.drawImage(img1, 0, 0, null);
            if(isDisabled)
                g2.drawImage(img2, 0, 0, null);
            else
                g2.drawImage(img2, w1 / 2 - w2 / 2, h1 / 2 - h2 / 2, null);
        }
        return buf;
    }

    // appelle n fois la fonction rotate (tourne n fois la piece)
    public static BufferedImage rotateNTime(BufferedImage buf, int r) {
        for (int i = 0; i < r; i++)
            buf = GameContainer.rotate(buf, 90.0d);
        return buf;
    }

    // tourne la piece vers la droite
    private static BufferedImage rotate(BufferedImage image, Double degrees) {
        double radians = Math.toRadians(degrees);
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int newWidth = (int) Math.round(image.getWidth() * cos + image.getHeight() * sin);
        int newHeight = (int) Math.round(image.getWidth() * sin + image.getHeight() * cos);

        BufferedImage rotate = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotate.createGraphics();
        int x = (newWidth - image.getWidth()) / 2;
        int y = (newHeight - image.getHeight()) / 2;
        AffineTransform at = new AffineTransform();
        at.setToRotation(radians, x + (image.getWidth() / 2), y + (image.getHeight() / 2));
        at.translate(x, y);
        g2d.setTransform(at);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return rotate;
    }

}