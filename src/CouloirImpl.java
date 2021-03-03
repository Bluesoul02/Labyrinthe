import java.util.ArrayList;
import java.util.List;

class CouloirImpl implements Couloir {
    protected Orientation orientation;
    private Forme forme;
    private Objectif objectif;
    private List<Pion> pions;

    public CouloirImpl(Orientation orientation, Forme forme, Objectif objectif) {
        this.orientation = orientation;
        this.forme = forme;
        this.objectif = objectif;
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
}