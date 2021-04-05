import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

class CouloirImpl extends JButton implements Couloir {
    private static final long serialVersionUID = -2494896132839690178L;
    private static final Dimension dimsScreen = Toolkit.getDefaultToolkit().getScreenSize();
    protected Orientation orientation;
    private Forme forme;
    private Objectif objectif;
    protected List<Pion> pions;
    protected Position position;

    public CouloirImpl(Orientation orientation, Forme forme, Objectif objectif, Position position) {
        this.orientation = orientation;
        this.forme = forme;
        this.objectif = objectif;
        this.position = position;
        this.pions = new ArrayList<Pion>();
        setVisible(true);
        setSize(dimsScreen.width / 10, dimsScreen.height / 10);
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