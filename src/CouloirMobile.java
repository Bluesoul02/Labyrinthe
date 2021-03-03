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

            case SUD:

            case OUEST:

            case EST:
        }
    }

    public void setOrientation(Orientation oritentation) {
        if (!posee)
            this.orientation = orientation;
    }

}
