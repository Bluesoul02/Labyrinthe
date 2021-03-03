interface Joueur {
    int getAge();

    void joue();

    void fixerObjectifs(Pile<Objectif> objectifs);

    void recevoirPion(Pion p);
}