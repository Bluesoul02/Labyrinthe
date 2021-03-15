import java.io.IOException;
import java.util.*;
import javax.swing.*;

class JeuImpl implements Jeu {
  private CouloirMobile supplementaire;
  private PositionInsertion positionOrigine;
  private List<Joueur> joueurs;
  private Map<Couleur, Pion> pions;
  private List<Objectif> objectifs;
  private Plateau plateau;
  private Joueur currentPlayer;
  private static final Random RAND = new Random();

  JeuImpl() {
    plateau = new Plateau();
    joueurs = new ArrayList<Joueur>();
    objectifs = new ArrayList<>();
    pions = new HashMap<Couleur, Pion>();
    enregistrer(new JoueurImpl(14, this), Couleur.BLEU);
    preparer();
    display();
    setButtonsListener(); // à faire qaund tout les couloirs sont posés
  }

  public Joueur getCurrentPlayer() {
    return currentPlayer;
  }

  @Override
  public void modifierCouloirs(PositionInsertion pos, Orientation orientation) {
    if (pos != positionOrigine) {
      supplementaire.setOrientation(orientation);
      supplementaire = plateau.modifierCouloirs(pos, supplementaire);
      positionOrigine = pos.oppose();
      for (Pion pion : supplementaire.getPions()) {
        pion.poserA(pos);
      }
    }
  }

  @Override
  public void enregistrer(Joueur joueur, Couleur couleur) {
    Pion p = new PionImpl(plateau, couleur.getPositionInitiale());
    pions.put(couleur, p);
    joueur.recevoirPion(p);
    joueurs.add(joueur);
  }

  @Override
  public List<Couloir> couloirs() {
    List<Couloir> couloirs = new ArrayList<Couloir>();
    Random rand = new Random();
    int stepX = 2;
    int x = 1;
    int y = 0;
    for (int i = 0; i < 34; i++) {
      int f = rand.nextInt(3);
      int or = rand.nextInt(4);
      int hasObj = rand.nextInt(2);
      Objectif obj = null;
      int k = -1;
      if (hasObj == 0 && objectifs.size() < 24) {
        Boolean found = false;
        while (!found) {
          k = rand.nextInt(24);
          obj = Objectif.values()[k];
          if (!objectifs.contains(obj)) {
            objectifs.add(obj);
            found = true;
          }
        }
      }
      if (i >= 3 && (i == 3 || i == 13 || i == 23)) {
        x = 0;
        y++;
        stepX = 1;
      } else if (i >= 10 && i % 10 == 0) {
        x = 1;
        y++;
        stepX = 2;
      } else if (i > 0) {
        x += stepX;
      }
      Position pos = new Position(x, y);
      CouloirMobile coul = new CouloirMobile(Orientation.values()[or], Forme.values()[f], obj, pos);
      coul.setPosee(true);

      couloirs.add(coul);
    }
    return couloirs;
  }

  private void preparer() {
    couloirs();
    Objectif[] objectifs = Objectif.values();
    int nb = objectifs.length / joueurs.size();
    for (Joueur j : joueurs) {
      Stack<Objectif> tabObj = new Stack<Objectif>();
      for (int i = 0; i < nb; i++) {
        tabObj.push(objectifs[RAND.nextInt(objectifs.length)]);
      }
      j.fixerObjectifs(tabObj);
    }
    this.objectifs = plateau.setCouloirFixe();
    plateau.addCouloirsMobiles(couloirs());
  }

  private void jouer() {
    Joueur joueur = null;
    do {
      joueur = prochainJoueur();
      joueur.joue();
    } while (!aGagne(joueur));
  }

  private boolean aGagne(Joueur joueur) {
    return (joueur.getObjectifs().empty()
        && joueur.getPion().getPositionInitiale() == joueur.getPion().getPositionCourante());
  }

  protected Joueur prochainJoueur(/* Joueur lastJoueur */) {
    if (currentPlayer == null)
      return joueurs.get(0);
    int index = joueurs.indexOf(currentPlayer);
    if (index == joueurs.size() - 1)
      currentPlayer = joueurs.get(0);
    else
      currentPlayer = joueurs.get(++index);
    return currentPlayer;
  }

  private void display() {
    try {
      new DisplayWindow(plateau);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void setButtonsListener() {
    for (int i = 0; i < plateau.getCouloirs().size(); i++) {
      CouloirImpl c = (CouloirImpl) plateau.getCouloirs().get(i);
      c.addActionListener(new CaseListener(c, this));
    }
  }
}
