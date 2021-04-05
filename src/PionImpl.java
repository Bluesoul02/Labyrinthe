class PionImpl implements Pion {
    private Plateau plateau;
    private Position positionInitiale;
    private Position positionCourante;

    PionImpl(Plateau plateau, Position positionInitiale) {
        this.plateau = plateau;
        this.positionInitiale = positionInitiale;
        this.positionCourante = positionInitiale;
    }

    @Override
    public Position getPositionCourante() {
        return positionCourante;
    }

    @Override
    public Position getPositionInitiale() {
        return positionInitiale;
    }

    public Objectif deplacer(Position pos) {
        Objectif ob = null;
        try {
            ob = plateau.deplacer(pos, this);
        } catch (NonAtteignableException e) {
            return null;
        }
        this.positionCourante = pos;
        return ob;
    }

    public void poserA(PositionInsertion posIns) {
        Position pos = posIns.getPosition();
        positionCourante = pos;
    }

    @Override
    public void setPositionCourante(Position pos) {
        positionCourante = pos;
    }
}
