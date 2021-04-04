import java.util.Set;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfosJoueurs extends JPanel{

    private final String baseTexte = "Tour du joueur ";

    private JLabel tour;
    private JLabel objectif;

    public InfosJoueurs(JeuImpl jeu){
        this.tour = new JLabel(baseTexte);
        Set<Entry<Couleur,Pion>> mapPions = jeu.getPions().entrySet();
        Pion pionPremierJoueur = jeu.getCurrentPlayer().getPion();
        for(Entry<Couleur,Pion> e : mapPions){
            if(e.getValue()==pionPremierJoueur){
                this.tour.setText(baseTexte+e.getKey());
            }
        }
        ImageIcon imageObjectif = new ImageIcon("img/objectifs"+jeu.getCurrentPlayer().getObjectifs().peek()+".png");
        objectif = new JLabel(imageObjectif);
        this.add(tour);
        this.add(objectif);
    }    
}
