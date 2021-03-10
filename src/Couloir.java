import java.util.List;

interface Couloir {
    Orientation getOrientation();

    Forme getForme();

    Objectif getObjectif();

    List<Pion> getPions();

    Position getPosition();

    void addPion(Pion p);
}