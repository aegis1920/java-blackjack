package blackjack.domain.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.Result.BasicRule;
import blackjack.domain.Result.MoneyRule;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Money;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;

public class MoneyResult implements Rusult {
    private static Money dealerMoney = new Money(0);
    private static Map<Participant, Money> playersMoney = new HashMap<>();

    public static void initPlayerMoney(Player player, String money) {
        playersMoney.put(player, new Money(money));
    }

    public static void makeBonus(Participant participant, Money money) {
        playersMoney.put(participant, playersMoney.getOrDefault(participant, new Money(0)).multiply(1.5));
        dealerMoney = dealerMoney.subtract(money);
    }

    public static void make(Participant participant, Money money) {
        playersMoney.put(participant, playersMoney.getOrDefault(participant, new Money(0)).add(money));
        dealerMoney = dealerMoney.subtract(money);
    }

    public static void lose(Participant participant, Money money) {
        playersMoney.put(participant, playersMoney.getOrDefault(participant, new Money(0)).subtract(money));
        dealerMoney = dealerMoney.add(money);
    }

    public static Map<Participant, Money> getPlayersMoney() {
        return playersMoney;
    }

    public static Money getDealerMoney() {
        return dealerMoney;
    }

    public void judge(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            MoneyRule.applyResultOfPlayer(player, playersMoney.get(player),
                BasicRule.getResultOfPlayer(dealer, player));
        }
    }
}
