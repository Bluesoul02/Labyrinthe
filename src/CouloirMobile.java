public class CouloirMobile extends CouloirImpl {
    private boolean posee;
    private Orientation orientation;
    private Position position;

    public CouloirMobile(Orientation orientation, Forme forme, Objectif objectif, Position position) {
        super(orientation, forme, objectif, position);
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
            this.position = new Position(this.position.x(), this.position.y() + 1);
            break;
        case SUD:
            this.position = new Position(this.position.x(), this.position.y() - 1);
            break;
        case OUEST:
            this.position = new Position(this.position.x() - 1, this.position.y());
            break;
        case EST:
            this.position = new Position(this.position.x() + 1, this.position.y());
            break;
        }
    }

    public void setOrientation(Orientation orientation) {
        if (!posee)
            this.orientation = orientation;
    }

}
