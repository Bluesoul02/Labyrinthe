import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

class CouloirImpl extends JButton implements Couloir {
    private static final long serialVersionUID = -2494896132839690178L;
    protected Orientation orientation;
    private Forme forme;
    private Objectif objectif;
    private List<Pion> pions;
    private Position position;

    public CouloirImpl(Orientation orientation, Forme forme, Objectif objectif, Position position) {
        this.orientation = orientation;
        this.forme = forme;
        this.objectif = objectif;
        this.position = position;
        this.pions = new ArrayList<Pion>();
        setVisible(true);
        setSize(100, 100);
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public Forme getForme() {
        return forme;
    }

    @Override
    public Objectif getObjectif() {
        return objectif;
    }

    @Override
    public List<Pion> getPions() {
        return pions;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void addPion(Pion pion) {
        pions.add(pion);
    }

    @Override
    public String toString() {
        return forme + " " + objectif + " " + orientation;
    }

    @Override
    public void removePion(Pion p) {
        pions.remove(p);
    }
}