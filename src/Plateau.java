import java.util.ArrayList;
import java.util.List;

class Plateau {
    private List<Couloir> couloirs;

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
        couloirs.push(new CouloirFixe(EST, forme, objectif, ));
    }

    public List<Couloir> getCouloirs() {
        return couloirs;
    }
}
