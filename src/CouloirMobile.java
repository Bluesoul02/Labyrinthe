public class CouloirMobile extends CouloirImpl {
    private boolean posee;
    private Orientation orientation;

    public boolean isPosee() {
        return posee;
    }

    public void setPosee(boolean posee) {
        this.posee = posee;
    }

    @Override
    public void decaler(Orientation orientation) {
        switch (orientation) {
            case NORD:

            case SUD:

            case OUEST:

            case EST:
        }
    }

    public void setOrientation(Orientation oritentation) {
        if(!posee) this.orientation = orientation;
    }

}
