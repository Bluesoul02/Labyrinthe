import java.util.*;

import javax.swing.JFrame;

class JeuImpl implements Jeu {
  private CouloirMobile supplementaire;
  private PositionInsertion positionOrigine;
  private List<Joueur> joueurs;
  private Map<Couleur, Pion> pions;
  private Objectif[] objectifs;
  private Plateau plateau;
  private static final Random RAND = new Random();

  JeuImpl() {
    plateau = new Plateau();
    joueurs = new ArrayList<Joueur>();
    objectifs = new Objectif[24];
    pions = new HashMap<Couleur, Pion>();
    preparer();
    display();
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
  }

  @Override
  public List<Couloir> couloirs() {
    List<Couloir> couloirs = new ArrayList<Couloir>();
    Random rand = new Random();
    List<Integer> objs = new ArrayList<Integer>();
    int stepX = 2;
    int x = 1;
    int y = 0;
    for (int i = 0; i < 34; i++) {
      int f = rand.nextInt(3);
      int or = rand.nextInt(4);
      int hasObj = rand.nextInt(2);
      Objectif obj = null;
      if (hasObj == 0) {
        while (obj == null || objs.contains(obj)) {
          obj = Objectif.values()[rand.nextInt(24)];
        }
      }
      if (i == 3 || i == 13 || i == 23) {
        x = 0;
        y++;
        stepX = 1;
      } else if (i % 10 == 0) {
        x = 1;
        y++;
        stepX = 2;
      } else {
        x += stepX;
      }
      Position pos = new Position(x, y);

      couloirs.add(new CouloirMobile(Orientation.values()[or], Forme.values()[f], obj, pos));
    }
    return couloirs;
  }

  private void preparer() {
    Objectif[] objectifs = Objectif.values();
    int nb = objectifs.length / joueurs.size();
    for (Joueur j : joueurs) {
      Stack<Objectif> tabObj = new Stack<Objectif>();
      for (int i = 0; i < nb; i++) {
        tabObj.push(objectifs[RAND.nextInt(objectifs.length)]);
      }
      j.fixerObjectifs(tabObj);
    }
    plateau.setCouloirFixe();
  }

  private void jouer() {
    Joueur joueur = null;
    do {
      joueur = prochainJoueur(joueur);
      joueur.joue();
    } while (!aGagne(joueur));
  }

  private boolean aGagne(Joueur joueur) {
    return (joueur.getObjectifs().empty()
        && joueur.getPion().getPositionInitiale() == joueur.getPion().getPositionCourante());
  }

  private Joueur prochainJoueur(Joueur lastJoueur) {
    if (lastJoueur == null)
      return joueurs.get(0);
    int index = joueurs.indexOf(lastJoueur);
    if (index == joueurs.size() - 1)
      return joueurs.get(0);
    else
      return joueurs.get(++index);
  }

  public void display() {
    new DisplayWindow(plateau);
  }
}
