enum PositionInsertion {
    N1, N2, N3, E1, E2, E3, S1, S2, S3, O1, O2, O3;

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
