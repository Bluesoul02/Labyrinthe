class PionImpl implements Pion {
    private Plateau plateau;
    private Position positionInitiale;
    private Position positionCourante;

    PionImpl(Plateau plateau, Position positionInitiale) {
        this.plateau = plateau;
        this.positionInitiale = positionInitiale;
    }

    public Objectif deplacer(Position pos) {
        if (plateau.estAtteignable(positionCourante, pos)) {
        }
    }

<<<<<<< HEAD
    void poserA(Position pos) {
        plateau.deplacer(pos, this);
        positionCourante = pos;
=======
    @Override
    public void poserA(Position pos) {
        if (plateau.estAtteignable(positionCourante, pos)) {
            plateau.deplacer(pos, this);
            positionCourante = pos;
        }
>>>>>>> 8e30dbe48dcd2112432d6faa3474a555484a17b5
    }
}
