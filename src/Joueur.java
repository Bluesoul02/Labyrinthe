interface Joueur {
    int getAge();
    void joue();
    Pile<Objectif> fixerObjectifs();
    Pion recevoirPion();
}