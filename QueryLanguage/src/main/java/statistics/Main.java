package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));

        Matcher m = new Not( new HasAtLeast(1, "goals") );

        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
    }
}
