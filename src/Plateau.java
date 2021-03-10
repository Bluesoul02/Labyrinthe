import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Plateau {
    private List<Couloir> couloirs;
    private static final Random RAND = new Random();

    Plateau() {
        couloirs = new ArrayList<>();
    }

    protected CouloirMobile modifierCouloirs(PositionInsertion pos, CouloirMobile c) {
        Orientation orientation = pos.getOrientation();
        c.decaler(orientation);
        for (int i = 1; i < 7; i++) {
            switch (orientation) {
            case NORD:
                c = (CouloirMobile) getCouloir(new Position(c.getPosition().x(), c.getPosition().y() + i));
                break;
            case SUD:
                c = (CouloirMobile) getCouloir(new Position(c.getPosition().x(), c.getPosition().y() - i));
                break;
            case EST:
                c = (CouloirMobile) getCouloir(new Position(c.getPosition().x() + i, c.getPosition().y()));
                break;
            case OUEST:
                c = (CouloirMobile) getCouloir(new Position(c.getPosition().x() - i, c.getPosition().y()));
                break;
            }
            c.decaler(orientation);
        }
        return c;
    }

    protected Objectif deplacer(Position pos, Pion pion) {
        Couloir couloir = getCouloir(pos);
        if(couloir != null) couloir.addPion(pion);
        return couloir.getObjectif();
    }

    protected Boolean estAtteignable(Position orig, Position dest) {

    }

    protected void setCouloirFixe() {
        Orientation[] orients = Orientation.values();
        Couleur[] couleurs = Couleur.values();
        for (int i = 0; i < 4; i++) {
            couloirs.add(new CouloirFixe(orients[i], Forme.COUDE, null, couleurs[i].getPositionInitiale()));
        }

        Position[] positions = { new Position(2, 2), new Position(4, 2), new Position(4, 4), new Position(2, 4) };
        Objectif[] objectifs = Objectif.values();
        for (int i = 0; i < 4; i++) {
            couloirs.add(new CouloirFixe(orients[i], Forme.TE, objectifs[RAND.nextInt(objectifs.length)], positions[i]));
        }

        int[][] values = { { 0, 2, 0, 4 }, { 2, 0, 4, 0 }, { 6, 2, 6, 4 }, { 2, 6, 4, 6 } };
        for (int i = 0; i < 4; i++) {
            couloirs.add(new CouloirFixe(orients[i], Forme.TE, objectifs[RAND.nextInt(objectifs.length)], new Position(values[i][0],values[i][1])));
            couloirs.add(new CouloirFixe(orients[i], Forme.TE, objectifs[RAND.nextInt(objectifs.length)], new Position(values[i][2],values[i][3])));
        }
    }

    protected void addCouloirsMobiles(List<Couloir> couloirs) {
        this.couloirs.addAll(couloirs);
    }

    protected Couloir getCouloir(Position pos) {
        for (int i = 0; i < couloirs.size(); i++) {
            if (couloirs.get(i).getPosition().x() == pos.x() && couloirs.get(i).getPosition().y() == pos.y())
                if (couloirs.get(i).getClass() != CouloirMobile.class || ((CouloirMobile) couloirs.get(i)).isPosee())
                    return couloirs.get(i);
        }
        return null; // faire une exception?
    }
}
