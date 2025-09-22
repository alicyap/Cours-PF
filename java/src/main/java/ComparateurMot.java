package org.example;

import java.util.Comparator;

public class ComparateurMot implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        // ordre décroissant o2 > o1
        return Integer.compare(Main.score((String) o2), Main.score((String) o1));
    }
}
