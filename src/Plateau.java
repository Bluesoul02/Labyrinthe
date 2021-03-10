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
        getCouloir(pion.getPositionCourante()).removePion(pion);
        if(couloir != null) couloir.addPion(pion);
        return couloir.getObjectif();
    }

    protected Boolean estAtteignable(Position orig, Position dest) {

    }

    protected List<Objectif> setCouloirFixe() {
        List<Objectif> objUtilise = new ArrayList<Objectif>();
        Objectif actual=null;
        Orientation[] orients = Orientation.values();
        Couleur[] couleurs = Couleur.values();
        for (int i = 0; i < 4; i++) {
            couloirs.add((Couloir) new CouloirFixe(orients[i], Forme.COUDE, null, couleurs[i].getPositionInitiale()));
        }

        Position[] positions = { new Position(2, 2), new Position(4, 2), new Position(4, 4), new Position(2, 4) };
        Objectif[] objectifs = Objectif.values();
        for (int i = 0; i < 4; i++) {
            actual = objectifs[RAND.nextInt(objectifs.length)];
            while(objUtilise.contains(actual)) actual = objectifs[RAND.nextInt(objectifs.length)];
            objUtilise.add(actual);
            couloirs.add((Couloir) new CouloirFixe(orients[i], Forme.TE, actual, positions[i]));
        }

        int[][] values = { { 0, 2, 0, 4 }, { 2, 0, 4, 0 }, { 6, 2, 6, 4 }, { 2, 6, 4, 6 } };
        for (int i = 0; i < 4; i++) {
            actual = objectifs[RAND.nextInt(objectifs.length)];
            while(objUtilise.contains(actual)) actual = objectifs[RAND.nextInt(objectifs.length)];
            objUtilise.add(actual);
            couloirs.add((Couloir) new CouloirFixe(orients[i], Forme.TE, actual, new Position(values[i][0],values[i][1])));
            actual = objectifs[RAND.nextInt(objectifs.length)];
            while(objUtilise.contains(actual)) actual = objectifs[RAND.nextInt(objectifs.length)];
            objUtilise.add(actual);
            couloirs.add((Couloir) new CouloirFixe(orients[i], Forme.TE, actual, new Position(values[i][2],values[i][3])));
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
