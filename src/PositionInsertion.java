enum PositionInsertion {
    N1(Orientation.NORD), N2(Orientation.NORD), N3(Orientation.NORD), E1(Orientation.EST), E2(Orientation.EST),
    E3(Orientation.EST), S1(Orientation.SUD), S2(Orientation.SUD), S3(Orientation.SUD), O1(Orientation.OUEST),
    O2(Orientation.OUEST), O3(Orientation.OUEST);

    private Orientation orientation;

    PositionInsertion(Orientation orientation) {
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
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
