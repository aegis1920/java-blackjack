package blackjack.domain;

import static blackjack.domain.participants.Result.*;

import java.util.List;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;

public class BasicRule implements Rule {
    public static final int BLACK_JACK = 21;

    public static boolean isBusted(final Participant participant) {
        return participant.score() > BLACK_JACK;
    }

    private static void decideWinner(final Participant dealer, final Participant player) {
        if (isBusted(player) || isBusted(dealer)) {
            decideWhenBusted(dealer, player);
            return;
        }
        decideWhenNotBusted(dealer, player);
    }

    private static void decideWhenBusted(final Participant dealer, final Participant player) {
        if (isBusted(player)) {
            player.set(LOSE);
            dealer.set(WIN);
        }

        if (!isBusted(player) && isBusted(dealer)) {
            player.set(WIN);
            dealer.set(LOSE);
        }
    }

    private static void decideWhenNotBusted(final Participant dealer, final Participant player) {
        if (player.score() == dealer.score()) {
            player.set(DRAW);
            dealer.set(DRAW);
        }

        if (player.score() > dealer.score()) {
            player.set(WIN);
            dealer.set(LOSE);
        }

        if (player.score() < dealer.score()) {
            player.set(LOSE);
            dealer.set(WIN);
        }
    }

    @Override
    public void judge(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            BasicRule.decideWinner(dealer, player);
        }
    }
}
