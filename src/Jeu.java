import java.util.List;

interface Jeu {
  void modifierCouloirs(PositionInsertion pos, Orientation orientation);

  void enregistrer(Joueur joueur, Couleur couleur);

  List<Couloir> couloirs();
}
