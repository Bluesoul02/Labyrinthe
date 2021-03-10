interface Pion {
    Objectif deplacer(Position pos);

    void poserA(PositionInsertion posIns);

    Position getPositionInitiale();

    Position getPositionCourante();

    Position posInsToPos(PositionInsertion posIns);
}
