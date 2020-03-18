package blackjack.domain.Rule;

import blackjack.domain.Result.MoneyResult;
import blackjack.domain.participants.*;

import java.util.List;

public class MoneyRule implements Rule {
    @Override
    public void judge(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            MoneyResult.execute(dealer, player);
        }
    }
}
