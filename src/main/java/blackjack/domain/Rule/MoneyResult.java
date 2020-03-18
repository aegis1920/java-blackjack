package blackjack.domain.Rule;

import java.util.List;
import java.util.Map;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Money;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;

public class MoneyResult implements Rusult {
    Map<Participant, Money>

    @Override

    public void judge(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        for (Player player : players) {

        }
    }
}
