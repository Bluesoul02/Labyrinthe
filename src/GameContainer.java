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

    public GameContainer(Plateau plateau) throws IOException {
        GridLayout gl = new GridLayout(7, 7);
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
                buf = ImageIO.read(new File("img/formes/" + couloir.getForme().toString() + ".png"));
                buf = rotateNTime(buf, couloir.getOrientation().getRotation());
                if (couloir.getObjectif() != null)
                    deco = ImageIO.read(
                            new File("img/objectifs/" + /* couloir.getObjectif().toString() */"PLACEHOLDER" + ".png"));
                if (deco != null)
                    buf = append(buf, deco);
                ImageIcon img = new ImageIcon(buf);
                // JButton b = new JButton(img);
                couloir.setIcon(img);
                couloir.setDisabledIcon(img);
                couloir.setBorder(emptyBorder);
                this.add(couloir);
            }
        }
        this.setVisible(true);
        this.setLayout(gl);
        // 7 * i + j
        enableComponents(true);
    }

    public void enableComponents(boolean enable) {
        Component[] components = this.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
        }
    }

    private BufferedImage append(Image img1, Image img2) {
        BufferedImage buf = null;
        if (img1 != null && img2 != null) {
            int w1 = img1.getWidth(null);
            int h1 = img1.getHeight(null);
            int w2 = img2.getWidth(null);
            int h2 = img2.getHeight(null);
            int hMax;
            int wMax;

            hMax = (h1 >= h2) ? h1 : h2;
            wMax = w1 + w2;
            buf = new BufferedImage(wMax, hMax, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = buf.createGraphics();
            g2.drawImage(img1, 0, 0, null);
            g2.drawImage(img2, w1 / 2, h1 / 2, null);
        }
        return buf;
    }

    private BufferedImage rotateNTime(BufferedImage buf, int r) {
        for (int i = 0; i < r; i++)
            buf = rotate(buf, 90.0d);
        return buf;
    }

    public BufferedImage rotate(BufferedImage image, Double degrees) {
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