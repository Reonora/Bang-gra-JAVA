package dzikizachod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StrategiaSzeryfaDomyslna extends StrategiaSzeryfa {

    public boolean strzał(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {
        if (gracz.getWylosowaneAkcje().contains(Akcja.STRZEL)) {

            if (gracz.getAtakujący().isEmpty()) {
                return atakujLosową(gracz, gracze, pulaAkcji);
            }

            else {
                return atakujAtakujących(gracz, gracze, pulaAkcji);
            }
        }

        return false;
    }

    private boolean atakujLosową(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {
        List<Gracz> wZasięgu = gracz.zbierzZasieg(gracze);

        if (wZasięgu.isEmpty())
            return false;

        else {
            Collections.shuffle(wZasięgu);

            gracz.getWylosowaneAkcje().remove(Akcja.STRZEL);
            gracz.getDoWykonania().add(new Ruch(Akcja.STRZEL, wZasięgu.get(0)));
            return true;

        }

    }

    private boolean atakujAtakujących(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {

        List<Gracz> wZasięgu = gracz.zbierzZasieg(gracze);
        List<Gracz> atakującyWZasięgu = new ArrayList<Gracz>();

        for (int i = 0; i <= wZasięgu.size() - 1; i++) {
            if (gracz.getAtakujący().contains(wZasięgu.get(i))) {
                atakującyWZasięgu.add(wZasięgu.get(i));
            }
        }

        if (atakującyWZasięgu.isEmpty())
            return false;
        else {
            Collections.shuffle(atakującyWZasięgu);
            gracz.getWylosowaneAkcje().remove(Akcja.STRZEL);
            gracz.getDoWykonania()
                    .add(new Ruch(Akcja.STRZEL, atakującyWZasięgu.get(0)));
            return true;

        }
    }
}
