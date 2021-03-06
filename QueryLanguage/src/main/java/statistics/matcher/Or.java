package statistics.matcher;

import statistics.Player;

/**
 * Created by nikkaire on 10.12.2017.
 */
public class Or implements Matcher{
    private Matcher[] matchers;

    public Or(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {
        for (Matcher matcher : matchers) {
            if (matcher.matches(p)) {
                return true;
            }
        }
        return false;
    }

}
