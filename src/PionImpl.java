class PionImpl implements Pion {
    private Plateau plateau;
    private Position positionInitiale;
    private Position positionCourante;

    PionImpl(Plateau plateau, Position positionInitiale) {
        this.plateau = plateau;
        this.positionInitiale = positionInitiale;
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
        if (plateau.estAtteignable(positionCourante, pos)) {
        }
    }

    public void poserA(PositionInsertion posIns) {
        // Position pos = ;
        // plateau.deplacer(pos, this);
        // positionCourante = pos;
    }
}
