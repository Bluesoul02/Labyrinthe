enum Orientation {
    NORD(0), EST(1), SUD(2), OUEST(3);

    private int rotation;

    Orientation(int rotation) {
        this.rotation = rotation;
    }

    public int getRotation() {
        return rotation;
    }

}
