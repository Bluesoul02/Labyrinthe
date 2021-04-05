public class CouloirMobile extends CouloirImpl {
    private static final long serialVersionUID = 8131713857177204137L;
    private boolean posee;

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
            this.position = new Position(this.position.x(), this.position.y() + (this.position.y() >= 6 ? 0 : 1));
            break;
        case SUD:
            this.position = new Position(this.position.x(), this.position.y() - (this.position.y() <= 0 ? 0 : 1));
            break;
        case EST:
            this.position = new Position(this.position.x() - (this.position.x() <= 0 ? 0 : 1), this.position.y());
            break;
        case OUEST:
            this.position = new Position(this.position.x() + (this.position.x() >= 6 ? 0 : 1), this.position.y());
            break;
        }
        if (!pions.isEmpty())
            for (Pion pion : pions) {
                pion.setPositionCourante(this.position);
            }
    }

    public void setOrientation(Orientation orientation) {
        if (!posee)
            this.orientation = orientation;
    }

}
