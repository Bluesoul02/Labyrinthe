import java.util.ArrayList;
import java.util.List;

class CouloirImpl implements Couloir {
    private Orientation orientation;
    private Forme forme;
    private Objectif objectif;
    private List<Pion> pions;
    private Position position;

    public CouloirImpl(Orientation orientation, Forme forme, Objectif objectif, Position position) {
        this.orientation = orientation;
        this.forme = forme;
        this.objectif = objectif;
        this.position = position;
        this.pions = new ArrayList<Pion>();
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public Forme getForme() {
        return forme;
    }

    @Override
    public Objectif getObjectif() {
        return objectif;
    }

    @Override
    public List<Pion> getPions() {
        return pions;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void addPion(Pion pion) {
        pions.add(pion);
    }

    @Override
    public String toString() {
        return forme + " " + objectif + " " + orientation;
    }

    @Override
    public void removePion(Pion p) {
        pions.remove(p);
    }
}