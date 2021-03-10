import java.util.Stack;
import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);
        Boolean valide = false;
        PositionInsertion pi;
        while (!valide){
            System.out.println("Entrez l'endroit où vous voulez insérer le couloir (N1,S2,E3...) : ");
            try{
                pi = PositionInsertion.valueOf(sc.nextLine());
                valide = true;
            }catch(IllegalArgumentException e){
                valide = false;
                System.err.println("Entrez une position valide.");
            }
        }
        sc.close();
        return pi;

    }

    protected Position choisirPositionPion() {
        Scanner sc = new Scanner(System.in);
        Boolean valide = false;
        PositionInsertion pi;
        while (!valide){
            System.out.println("Entrez l'endroit où vous voulez insérer le couloir (N1,S2,E3...) : ");
            try{
                pi = PositionInsertion.valueOf(sc.nextLine());
                valide = true;
            }catch(IllegalArgumentException e){
                valide = false;
                System.err.println("Entrez une position valide.");
            }
        }
        sc.close();
        return pi;
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