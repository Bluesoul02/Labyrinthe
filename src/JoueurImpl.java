import java.util.Stack;

class JoueurImpl implements Joueur {
    private int age;
    private Stack<Objectif> objectifs;
    Jeu jeu;
    Pion pion;

    public JoueurImpl(int age, Jeu jeu) {
        this.age = age;
        this.jeu = jeu;
    }

    public int getAge() {
        return age;
    }

    public void joue() {
        jeu.modifierCouloirs(choisirPositionInsertionCouloir(), choisirOrientationCouloir());
        Objectif objectif = pion.deplacer(choisirPositionPion());
        if (objectif == objectifs.peek()) {
            objectifs.pop();
        }
    }

    @Override
    public void fixerObjectifs(Stack<Objectif> objectifs) {
        this.objectifs = objectifs;
    }

    @Override
    public void recevoirPion(Pion p) {
        pion = p;
    }

    protected PositionInsertion choisirPositionInsertionCouloir() {

    }

    protected Position choisirPositionPion() {

    }

    protected Orientation choisirOrientationCouloir() {

    }

    public Stack<Objectif> getObjectifs() {
        return objectifs;
    }

    public Pion getPion() {
        return pion;
    }
}