import java.util.*;

class JeuImpl implements Jeu{
  private CouloirMobile supplementaire;
  private positionOrigine PositionInsertion;
  private List<Joueur> joueurs;
  private Map<Couleur,Pion> pions;
  private Objectif[24] objectifs;
  private Plateau plateau;


  JeuImpl(){

  }


@Override
public void modifierCouloirs(PositionInsertion pos, Orientation orientation) {
	// TODO Auto-generated method stub
	
}


@Override
public void enregistrer(Joueur joueur, Couleur couleur) {
	// TODO Auto-generated method stub
	
}


@Override
public List<Couloirs> couloirs() {
	// TODO Auto-generated method stub
	return null;
}

private preparer();

private jouer();

private Joueur prochainJoueur();

}
