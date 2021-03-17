enum PositionInsertion {
    N1(Orientation.NORD, new Position(1, 0)), N2(Orientation.NORD, new Position(3, 0)),
    N3(Orientation.NORD, new Position(5, 0)), E1(Orientation.EST, new Position(6, 1)),
    E2(Orientation.EST, new Position(6, 3)), E3(Orientation.EST, new Position(6, 5)),
    S1(Orientation.SUD, new Position(1, 6)), S2(Orientation.SUD, new Position(3, 6)),
    S3(Orientation.SUD, new Position(5, 6)), O1(Orientation.OUEST, new Position(0, 1)),
    O2(Orientation.OUEST, new Position(0, 3)), O3(Orientation.OUEST, new Position(0, 5));

    private Orientation orientation;
    private Position position;

    PositionInsertion(Orientation orientation, Position position) {
        this.orientation = orientation;
        this.position = position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Position getPosition() {
        return position;
    }

    public PositionInsertion oppose() {
        switch (this) {
        case N1:
            return S1;
        case N2:
            return S2;
        case N3:
            return S3;
        case S1:
            return N1;
        case S2:
            return N2;
        case S3:
            return N3;
        case E1:
            return O1;
        case E2:
            return O2;
        case E3:
            return O3;
        case O1:
            return E1;
        case O2:
            return E2;
        default:
            return E3;
        }
    }
}
