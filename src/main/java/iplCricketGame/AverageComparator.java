package iplCricketGame;

import java.util.Comparator;

public class AverageComparator implements Comparator<CricketCsvDto> {

    @Override
    public int compare(CricketCsvDto p1, CricketCsvDto p2) {
        int i= ((int)(p1.average+p1.average));
        return i;
    }
}