import java.util.List;

interface Jeu {
  void modifierCouloirs(PositionInsertion pos, Orientation orientation);

  void enregistrer(Joueur joueur, Couleur couleur);
  
  void play();

  List<Couloir> couloirs();
}
