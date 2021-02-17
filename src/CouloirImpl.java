import java.util.List;

class CouloirImpl implements Couloir {
    private Orientation orientation;
    private Forme forme;
    private Objectif objectif;
    private List<Pion> pions;

    @Override
    public Orientation GetOrientation() {
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

    public void decaler(Orientation orientation) {
        switch (orientation) {
            case NORD:

            case SUD:

            case OUEST:

            case EST:
        }
    }
}