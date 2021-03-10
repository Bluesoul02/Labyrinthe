public class CouloirMobile extends CouloirImpl {
    private boolean posee;
    private Orientation orientation;

    public CouloirMobile(Orientation orientation, Forme forme, Objectif objectif) {
        super(orientation, forme, objectif);
    }

    public boolean isPosee() {
        return posee;
    }

    public void setPosee(boolean posee) {
        this.posee = posee;
    }

    public void decaler(Orientation orientation) {
        switch (orientation) {
        case NORD:
            // x + 1;
            break;
        case SUD:
            // x - 1;
            break;
        case OUEST:
            // y + 1;
            break;
        case EST:
            // y - 1;
            break;
        }
    }

    public void setOrientation(Orientation orientation) {
        if (!posee) this.orientation = orientation;
    }

}
