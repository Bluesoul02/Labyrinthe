import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Plateau {
    private List<Couloir> couloirs;
    private static final Random RAND = new Random();

    Plateau() {
        couloirs = new ArrayList<Couloir>();
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
        if (couloir != null)
            couloir.addPion(pion);
        return couloir.getObjectif();
    }

    protected Boolean estAtteignable(Position orig, Position dest) {

    }

    protected Position[] getVoisinsAtteignables(Position pos) {
        List<Orientation> sidesCC = openSide(getCouloir(pos));
        Couloir c = null;
        if (pos.x() - 1 >= 0) {
            c = getCouloir(new Position(pos.x() - 1, pos.y()));
            sidesCC.retainAll(openSide(c));
        }
        if (pos.x() + 1 <= 6) {
            c = getCouloir(new Position(pos.x() + 1, pos.y()));
        }
        if (pos.y() - 1 >= 0) {
            c = getCouloir(new Position(pos.x(), pos.y() - 1));
        }
        if (pos.y() + 1 <= 6) {
            c = getCouloir(new Position(pos.x(), pos.y() + 1));
        }
    }

    // retourne toutes les orientations ouvertes ("sans murs")
    protected List<Orientation> openSide(Couloir c) {
        List<Orientation> orientations = new ArrayList<>();
        switch (c.getForme()) {
        case TE:
            switch (c.getOrientation()) {
            case EST:
                orientations.add(Orientation.EST);
                orientations.add(Orientation.OUEST);
                orientations.add(Orientation.SUD);
                break;
            case OUEST:
                orientations.add(Orientation.OUEST);
                orientations.add(Orientation.NORD);
                orientations.add(Orientation.EST);
                break;
            case NORD:
                orientations.add(Orientation.EST);
                orientations.add(Orientation.SUD);
                orientations.add(Orientation.NORD);
                break;
            case SUD:
                orientations.add(Orientation.NORD);
                orientations.add(Orientation.OUEST);
                orientations.add(Orientation.SUD);
                break;
            }
            break;
        case COUDE:
            switch (c.getOrientation()) {
            case EST:
                orientations.add(Orientation.EST);
                orientations.add(Orientation.SUD);
                break;
            case OUEST:
                orientations.add(Orientation.NORD);
                orientations.add(Orientation.OUEST);
                break;
            case NORD:
                orientations.add(Orientation.EST);
                orientations.add(Orientation.NORD);
                break;
            case SUD:
                orientations.add(Orientation.OUEST);
                orientations.add(Orientation.SUD);
                break;
            }
            break;
        case DROITE:
            if (c.getOrientation() == Orientation.EST || c.getOrientation() == Orientation.OUEST) {
                orientations.add(Orientation.EST);
                orientations.add(Orientation.OUEST);
            } else {
                orientations.add(Orientation.NORD);
                orientations.add(Orientation.SUD);
            }
            break;
        }
        return orientations;
    }

    protected List<Objectif> setCouloirFixe() {
        List<Objectif> objUtilise = new ArrayList<Objectif>();
        Objectif actual = null;
        Orientation[] orients = Orientation.values();
        Couleur[] couleurs = Couleur.values();
        for (int i = 0; i < 4; i++) {
            couloirs.add((Couloir) new CouloirFixe(orients[i], Forme.COUDE, null, couleurs[i].getPositionInitiale()));
        }

        Position[] positions = { new Position(2, 2), new Position(4, 2), new Position(4, 4), new Position(2, 4) };
        Objectif[] objectifs = Objectif.values();
        for (int i = 0; i < 4; i++) {
            actual = objectifs[RAND.nextInt(objectifs.length)];
            while (objUtilise.contains(actual))
                actual = objectifs[RAND.nextInt(objectifs.length)];
            objUtilise.add(actual);
            couloirs.add((Couloir) new CouloirFixe(orients[i], Forme.TE, actual, positions[i]));
        }

        int[][] values = { { 0, 2, 0, 4 }, { 2, 0, 4, 0 }, { 6, 2, 6, 4 }, { 2, 6, 4, 6 } };
        for (int i = 0; i < 4; i++) {
            actual = objectifs[RAND.nextInt(objectifs.length)];
            while (objUtilise.contains(actual))
                actual = objectifs[RAND.nextInt(objectifs.length)];
            objUtilise.add(actual);
            couloirs.add(
                    (Couloir) new CouloirFixe(orients[i], Forme.TE, actual, new Position(values[i][0], values[i][1])));
            actual = objectifs[RAND.nextInt(objectifs.length)];
            while (objUtilise.contains(actual))
                actual = objectifs[RAND.nextInt(objectifs.length)];
            objUtilise.add(actual);
            couloirs.add(
                    (Couloir) new CouloirFixe(orients[i], Forme.TE, actual, new Position(values[i][2], values[i][3])));
        }
        return objUtilise;
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
