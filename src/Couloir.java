import java.util.List;

interface Couloir {
    Orientation GetOrientation();

    Forme getForme();

    Objectif getObjectif();

    List<Pion> getPions();
}