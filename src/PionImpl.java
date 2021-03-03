class PionImpl implements Pion{
    private Plateau plateau;
    private Position positionInitiale;
    private Position positionCourante;


    PionImpl(){

    }

    public Objectif deplacer(Position pos) {
        
    }


    void poserA(Position pos) {
        if(plateau.estAtteignable(positionCourante, pos)) {
            plateau.deplacer(pos, this);
            positionCourante = pos;
        }
    }
}
