package blackjack.domain.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.Result.BasicRule;
import blackjack.domain.Result.Rule;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;

public class BasicResult implements Rusult {
    public static Map<Rule, Integer> dealerResult = new HashMap<>();
    public static Map<Participant, Rule> playerResults = new HashMap<>();

    @Override
    public void judge(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            set(player, BasicRule.getResultOfPlayer(dealer, player));
        }
    }
}
