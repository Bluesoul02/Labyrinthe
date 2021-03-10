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
            Objectif obj = plateau.deplacer(pos, this);
            positionCourante = pos;
            return obj;
        }
        return null; // Exception
    }

    public void poserA(PositionInsertion posIns) {
        Position pos = posInsToPos(posIns);
        plateau.deplacer(pos, this);
        positionCourante = pos;
    }

    public Position posInsToPos(PositionInsertion posIns) {
        Position pos;
        String en = posIns.toString();
        int x;
        int y;
        int en2 = (int) en.substring(1);
        switch(en.substring(0,1)) {
            case "N":
                x=(en2 * 2) - 1;
                y=0;
                break;
            case "E":
                x=0;
                y=(en2 * 2) - 1;
                break;
            case "O":
                y=6;
                x=(en2 * 2) - 1;
                break;
            case "S":
                x=6;
                y=(en2 * 2) - 1;
                break;
        }
        pos = new Position(x,y);
        return pos;
    }
}
