import java.util.*;

class JeuImpl implements Jeu {
  private CouloirMobile supplementaire;
  private PositionInsertion positionOrigine;
  private List<Joueur> joueurs;
  private Map<Couleur, Pion> pions;
  private Objectif[] objectifs;
  private Plateau plateau;

  JeuImpl() {
    plateau = new Plateau();
    joueurs = new ArrayList<Joueur>();
    objectifs = new Objectif[24];
  }

  @Override
  public void modifierCouloirs(PositionInsertion pos, Orientation orientation) {
    if (pos != positionOrigine) {
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
    // TODO Auto-generated method stub
    // Doit créer les couloirs mobiles
    return null;
  }

  private void preparer() {
    // TODO distribuer les objectifs aux joueurs, attribuer les pions aux joueurs,
    // mettre les couloirs fixe par le biais du plateau
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
}
