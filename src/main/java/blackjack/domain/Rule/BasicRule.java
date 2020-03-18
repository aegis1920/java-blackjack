package blackjack.domain.Rule;

import java.util.List;

import blackjack.domain.Result.GameResult;
import blackjack.domain.participants.*;

public class BasicRule implements Rule {
    public static final int BLACK_JACK = 21;

    public static boolean isBusted(final int score) {
        return score > BLACK_JACK;
    }

    public static boolean isBlackjack(Player player) {
        return player.score() == 21 && player.countHand() == 2;
    }

    public static boolean playerLoseCondition(Participant dealer, Participant player) {
        return BasicRule.isBusted(player.score()) || (dealer.score() > player.score() && !BasicRule.isBusted(dealer.score()));
    }

    public static boolean playerDrawCondition(Participant dealer, Participant player) {
        return (!BasicRule.isBusted(dealer.score()) && !BasicRule.isBusted(player.score()) && (dealer.score() == dealer.score()));
    }

    public static boolean playerWinCondition(Participant dealer, Participant player) {
        return BasicRule.isBusted(dealer.score()) || (dealer.score() < player.score()) && !BasicRule.isBusted(player.score());
    }

    @Override
    public void judge(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            GameResult.resultOfPlayer(dealer, player);
        }
    }
}
