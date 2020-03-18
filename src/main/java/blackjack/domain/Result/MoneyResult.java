package blackjack.domain.Result;

import blackjack.domain.Rule.BasicRule;
import blackjack.domain.participants.Money;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Player;

public class MoneyResult {
    public static void execute(Participant dealer, Participant player) {
        if (BasicRule.playerWinCondition(dealer, player)) {
            if (BasicRule.isBlackjack((Player) player)) {
                calculateMoney(player, dealer, player.getMoney().multiply(1.5));
                return;
            }
            calculateMoney(player, dealer, player.getMoney());
        }

        // draw의 경우
//        if (BasicRule.playerDrawCondition(dealer, player)) {
//
//        }

        if (BasicRule.playerLoseCondition(dealer, player)) {
            calculateMoney(dealer, player, player.getMoney());
        }
    }

    public static void calculateMoney(Participant winner, Participant loser, Money bettingMoney) {
        winner.make(bettingMoney);
        loser.lose(bettingMoney);
    }
}
