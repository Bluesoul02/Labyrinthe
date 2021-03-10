import java.util.ArrayList;
import java.util.List;

class Plateau {
    private List<Couloir> couloirs;
    private static final Random RAND = new Random();

    Plateau() {
        couloirs = new ArrayList<>();
    }

    protected CouloirMobile modifierCouloirs(PositionInsertion pos, CouloirMobile c) {

    }

    protected Objectif deplacer(Position pos, Pion pion) {

    }

    protected Boolean estAtteignable(Position orig, Position dest) {

    }

    protected void setCouloirFixe() {
        Orientation[] orients = Orientation.values();
        Couleur[] couleurs = Couleur.values();
        for(int i=0; i<4; i++) {
            new CouloirFixe(orients[i], Forme.COUDE, null, couleurs[i].getPositionInitiale());
        }

        Position[] positions = {new Position(2,2), new Position(2,4), new Position(4,2), new Position(4,4)};
        Objectif[] objectifs = Objectif.values();
        for(int i=0; i<4; i++) {
            new CouloirFixe(orients[i], Forme.TE, objectifs[i], positions[i]);
        }

        int[][] values = {{0,2,0,4},{2,0,4,0},{6,2,6,4},{2,6,4,6}};
        for(int i=0; i<4; i++) {
            new CouloirFixe(orients[i], Forme.TE, objectifs[i], new Position(values[i][0],values[i][1]));
            new CouloirFixe(orients[i], Forme.TE, objectifs[i], new Position(values[i][2],values[i][3]));
        }
    }

    public List<Couloir> getCouloirs() {
        return couloirs;
    }
}
