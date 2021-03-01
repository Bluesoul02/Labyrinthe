import java.util.*;

class JeuImpl implements Jeu{
  private CouloirMobile supplementaire;
  private PositionInsertion positionOrigine;
  private List<Joueur> joueurs;
  private Map<Couleur,Pion> pions;
  private Objectif[24] objectifs;
  private Plateau plateau;


  JeuImpl(){
    plateau = new Plateau();
    joueurs = new ArrayList<Joueur>();
  }


@Override
public void modifierCouloirs(PositionInsertion pos, Orientation orientation) {
	if(pos != positionOrigine) {
    supplementaire = plateau.modifierCouloirs(pos, supplementaire);
    positionOrigine = pos.oppose();
    for(Pion pion : supplementaire) {
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
public List<Couloirs> couloirs() {
	// TODO Auto-generated method stub
	return null;
}

private void preparer(){
  // TODO distribuer les objectifs aux joueurs, mettre les couloirs mobiles sur le plateau
}

private void jouer(){
  // Pour chaque houeur tour à tour joueur.joue();
}

private Joueur prochainJoueur();

}
