import java.util.Stack;

interface Joueur {
    Pion getPion();

    Stack<Objectif> getObjectifs();

    int getAge();

    void joue();

    void fixerObjectifs(Stack<Objectif> objectifs);

    void recevoirPion(Pion p);
}