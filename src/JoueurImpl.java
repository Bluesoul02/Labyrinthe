class JoueurImpl implements Joueur {
    private int age;
    private Pile<Objectif> objectifs;
    Jeu jeu;
    Pion pion;

    public int getAge() {
        return age;
    }

    public void joue() {

    }

    @Override
    public void fixerObjectifs(Pile<Objectif> objectifs) {
        // TODO Auto-generated method stub

    }

    @Override
    public void recevoirPion(Pion p) {
        // TODO Auto-generated method stub

    }

    protected PositionInsertion choisirPositionInsertionCouloir() {

    }

    protected Position choisirPositionPion() {

    }

    protected Orientation choisirOrientationCouloir() {

    }
}