interface Pion {
    Objectif deplacer(Position pos);

    void poserA(PositionInsertion posIns);

    Position getPositionInitiale();

    Position getPositionCourante();

    void setPositionCourante(Position pos);
}
