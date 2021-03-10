enum Couleur {
    ROUGE(6, 6), VERT(0, 0), BLEU(6, 0), JAUNE(0, 6);

    private Position pos;

    Couleur(int x, int y) {
        this.pos = new Position(x, y);
    }

    public Position getPositionInitiale() {
        return pos;
    }
}
