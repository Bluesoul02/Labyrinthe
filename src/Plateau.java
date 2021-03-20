import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

class Plateau {
    private List<Couloir> couloirs;
    private static final Random RAND = new Random();

    Plateau() {
        couloirs = new ArrayList<Couloir>();
    }

    protected CouloirMobile modifierCouloirs(PositionInsertion posI, CouloirMobile couloirSuppl) {
        Orientation orientation = posI.getOrientation();
        CouloirMobile couloirMobile = (CouloirMobile) getCouloir(posI.getPosition());
        CouloirMobile nextCouloirMobile = null;
        couloirSuppl.setPosee(true);
        couloirSuppl.position = posI.getPosition();
        couloirs.add(couloirSuppl);
        for (int i = 0; i < 6; i++) {
            switch (orientation) {
            case NORD:
                nextCouloirMobile = (CouloirMobile) getCouloir(
                        new Position(couloirMobile.getPosition().x(), couloirMobile.getPosition().y() + 1));
                break;
            case SUD:
                nextCouloirMobile = (CouloirMobile) getCouloir(
                        new Position(couloirMobile.getPosition().x(), couloirMobile.getPosition().y() - 1));
                break;
            case OUEST:
                nextCouloirMobile = (CouloirMobile) getCouloir(
                        new Position(couloirMobile.getPosition().x() + 1, couloirMobile.getPosition().y()));
                break;
            case EST:
                nextCouloirMobile = (CouloirMobile) getCouloir(
                        new Position(couloirMobile.getPosition().x() - 1, couloirMobile.getPosition().y()));
                break;
            }

            System.out.println(couloirMobile);
            System.out.println(couloirMobile.getPosition().x() + ", " + couloirMobile.getPosition().y());
            couloirMobile.decaler(orientation);
            couloirMobile = nextCouloirMobile;
        }
        couloirMobile.setPosee(false);
        couloirs.remove(couloirMobile); // on enleve le couloir qui n'est plus dans le labyrinthe puis on le retourne
        return couloirMobile;
    }

    protected Objectif deplacer(Position pos, Pion pion) {
        if (estAtteignable(pion.getPositionCourante(), pos)) {
            Couloir couloir = getCouloir(pos);
            if (couloir != null)
                couloir.addPion(pion);
            return couloir.getObjectif();
        }
        return null;
    }

    protected Boolean estAtteignable(Position orig, Position dest) {
        List<Position> casesAccessibles = getVoisinsAtteignables(orig);
        int oldSize = 0;
        while (casesAccessibles.size() != oldSize)
            for (int i = oldSize; i < casesAccessibles.size(); i++) {
                oldSize = casesAccessibles.size();
                for (Position position : getVoisinsAtteignables(casesAccessibles.get(i))) {
                    if (!casesAccessibles.contains(position))
                        casesAccessibles.add(position);
                }
            }
        return casesAccessibles.contains(dest);
    }

    // retourne la liste des positions atteignables a 1 de portee a partir de pos
    protected List<Position> getVoisinsAtteignables(Position pos) {
        List<Orientation> sidesCC = openSide(getCouloir(pos));
        List<Position> positions = new ArrayList<>();
        Couloir c = null;
        if (pos.x() - 1 >= 0 && sidesCC.contains(Orientation.OUEST)) {
            c = getCouloir(new Position(pos.x() - 1, pos.y()));
            if (openSide(c).contains(Orientation.EST))
                positions.add(c.getPosition());
        }
        if (pos.x() + 1 <= 6 && sidesCC.contains(Orientation.EST)) {
            c = getCouloir(new Position(pos.x() + 1, pos.y()));
            if (openSide(c).contains(Orientation.OUEST))
                positions.add(c.getPosition());
        }
        if (pos.y() - 1 >= 0 && sidesCC.contains(Orientation.SUD)) {
            c = getCouloir(new Position(pos.x(), pos.y() - 1));
            if (openSide(c).contains(Orientation.NORD))
                positions.add(c.getPosition());
        }
        if (pos.y() + 1 <= 6 && sidesCC.contains(Orientation.NORD)) {
            c = getCouloir(new Position(pos.x(), pos.y() + 1));
            if (openSide(c).contains(Orientation.SUD))
                positions.add(c.getPosition());
        }
        for (Position position : positions) {
            System.out.println(position.x() + "," + position.y());
        }
        return positions;
    }

    // retourne toutes les orientations ouvertes ("sans murs")
    private List<Orientation> openSide(Couloir c) {
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

    protected List<Couloir> getCouloirs() {
        return couloirs;
    }
}
