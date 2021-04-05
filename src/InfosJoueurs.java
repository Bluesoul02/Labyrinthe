import java.util.Set;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfosJoueurs extends JPanel {

    private static final long serialVersionUID = -4680045514310383985L;

    private final static String baseTexte = "Tour du joueur ";

    private JeuImpl jeu;
    private JLabel tour;
    private JLabel objectif;

    public InfosJoueurs(JeuImpl jeu) {
        this.jeu = jeu;
        this.tour = new JLabel(baseTexte);
        Set<Entry<Couleur, Pion>> mapPions = jeu.getPions().entrySet();
        Pion pionPremierJoueur = jeu.getCurrentPlayer().getPion();
        for (Entry<Couleur, Pion> e : mapPions) {
            if (e.getValue() == pionPremierJoueur) {
                this.tour.setText(baseTexte + e.getKey());
            }
        }
        String cheminImage = "img/objectifs/" + jeu.getCurrentPlayer().getObjectifs().peek() + ".png";
        ImageIcon imageObjectif = new ImageIcon(cheminImage);
        objectif = new JLabel(imageObjectif);
        this.add(tour);
        this.add(objectif);
    }

    public void update() {
        Set<Entry<Couleur, Pion>> mapPions = jeu.getPions().entrySet();
        Pion pionPremierJoueur = jeu.getCurrentPlayer().getPion();
        Objectif objectifPile = jeu.getCurrentPlayer().getObjectifs().peek();
        if(objectifPile == null){
            for (Entry<Couleur, Pion> e : mapPions) {
                if (e.getValue() == pionPremierJoueur) {
                    this.tour.setText(e.getKey()+" doit rentrer à sa base");
                }
            }
            objectif = null;
        }else{
            for (Entry<Couleur, Pion> e : mapPions) {
                if (e.getValue() == pionPremierJoueur) {
                    this.tour.setText(baseTexte + e.getKey());
                }
            }
            objectif.setIcon(new ImageIcon("img/objectifs/" + objectifPile + ".png"));
        }
        
        this.repaint();
    }
}
