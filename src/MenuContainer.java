import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
//import java.io.IOException;

public class MenuContainer extends JFrame {

    private static final long serialVersionUID = -6870797015186432927L;
    private JeuImpl jeu;
    private JPanel panel;

    public MenuContainer() {
        super("Labyrinthe");
        this.jeu = new JeuImpl();
        this.panel = new JPanel();
        establishContainer();
        repaint();
    }

    public void establishContainer() {
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize().getSize();
        setSize((int) screen.getWidth(), (int) screen.getHeight() - 30);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLabel colors = new JLabel("Couleur :");
        JComboBox<String> allColors = new JComboBox<String>();
        JLabel age = new JLabel("Âge :");
        JSpinner putAge = new JSpinner();
        JButton validate = new JButton();
        JButton startGame = new JButton();

        colors.setVisible(true);
        age.setVisible(true);

        allColors.addItem("ROUGE");
        allColors.addItem("VERT");
        allColors.addItem("JAUNE");
        allColors.addItem("BLEU");
        allColors.setVisible(true);
        allColors.setEnabled(true);

        putAge.setModel(new SpinnerNumberModel(18, 7, 100, 1));
        putAge.setVisible(true);
        putAge.setEnabled(true);

        validate.setText("Ajouter");
        validate.setVisible(true);
        validate.setEnabled(true);
        validate.addActionListener((ActionEvent e) -> {
            jeu.enregistrer(new JoueurImpl((Integer) putAge.getValue(), jeu),
                    Couleur.valueOf((String) allColors.getSelectedItem()));
        });

        startGame.setText("Jouer");
        startGame.setVisible(true);
        startGame.setEnabled(true);
        startGame.addActionListener((ActionEvent e) -> {
            dispose();
            /*
             * try { new DisplayWindow(jeu.getPlateau(), jeu.getSupplementaire()); } catch
             * (IOException e1) { e1.printStackTrace(); }
             */
        });

        panel.add(colors);
        panel.add(allColors);
        panel.add(age);
        panel.add(putAge);
        panel.add(validate);
        panel.add(startGame);

        setContentPane(panel);
        setVisible(true);
    }
}