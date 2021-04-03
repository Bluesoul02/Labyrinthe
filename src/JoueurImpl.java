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
        PositionInsertion pi = null;
        while (!valide) {
            System.out.println("Entrez l'endroit où vous voulez insérer le couloir (N1,S2,E3...) :");
            try {
                pi = PositionInsertion.valueOf(sc.nextLine());
                valide = true;
            } catch (IllegalArgumentException e) {
                valide = false;
                System.err.println(e.getMessage());
            }
        }
        sc.close();
        return pi;
    }

    protected Position choisirPositionPion() {
        Scanner sc = new Scanner(System.in);
        Boolean valide = false;
        int x = -1;
        int y = -1;
        while (!valide) {
            System.out.println("Entrez la position où vous voulez déplacer votre pion (x y) :");
            String[] s = sc.nextLine().split(" ");
            try {
                x = Integer.parseInt(s[0]);
                y = Integer.parseInt(s[1]);
                if (x >= 0 && x <= 6 && y >= 0 && y <= 6) {
                    valide = true;
                }
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        }
        sc.close();
        return new Position(x, y);
    }

    protected Orientation choisirOrientationCouloir() {
        Orientation o = null;
        Scanner sc = new Scanner(System.in);
        Boolean valide = false;
        while (!valide) {
            System.out.println("Entrez l'orientation du couloir (NORD, SUD, EST, OUEST) :");
            try {
                o = Orientation.valueOf(sc.nextLine());
                valide = true;
            } catch (IllegalArgumentException e) {
                valide = false;
                System.err.println(e.getMessage());
            }
        }
        sc.close();
        return o;
    }

    public Stack<Objectif> getObjectifs() {
        return objectifs;
    }

    public Pion getPion() {
        return pion;
    }
}