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
    if(pos != positionOrigine) {
      supplementaire = plateau.modifierCouloirs(pos, supplementaire);
      positionOrigine = pos.oppose();
      for(Pion pion : supplementaire.getPions()) {
        pion.poserA(pos);
      }
    }
  }

  @Override
  public void enregistrer(Joueur joueur, Couleur couleur) {
    // TODO Auto-generated method stub
    // donner un pion au joueur

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
    Joueur joueur;
    do {
      joueur = prochainJoueur();
      joueur.joue();
    } while (!aGagne(joueur));
  }

  private boolean aGagne(Joueur joueur) {
    return (joueur.objectifs.length() == 0 && joueur.pion.positionInitiale == joueur.pion.PositionCourante);
  }

  private Joueur prochainJoueur() {

  }

}
