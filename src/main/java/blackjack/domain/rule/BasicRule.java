package blackjack.domain.rule;

import java.util.function.BiPredicate;

import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Player;

public enum BasicRule {
    WIN_BLACK_JACK("승", (Participant dealer, Participant player) ->
        isBlackjack((Player)player) && (isBusted(dealer.score()) || (dealer.score() < player.score()) && !isBusted(
            player.score()))
    ),
    WIN("승", (Participant dealer, Participant player) ->
        isBusted(dealer.score()) || (dealer.score() < player.score()) && !isBusted(player.score())
    ),
    DRAW("무", (Participant dealer, Participant player) ->
        (!isBusted(dealer.score()) && !isBusted(player.score()) && (dealer.score()
            == player.score()))
    ),
    LOSE("패", (Participant dealer, Participant player) ->
        isBusted(player.score()) || dealer.score() > player.score() && !isBusted(dealer.score())
    );

    public static final int BUST_LIMIT = 21;
    public static final int BLACK_JACK_CARD_SIZE = 2;

    private final String value;
    private final BiPredicate<Participant, Participant> condition;

    BasicRule(final String value, final BiPredicate<Participant, Participant> condition) {
        this.value = value;
        this.condition = condition;
    }

    public static boolean isBusted(final int score) {
        return score > BUST_LIMIT;
    }

    public static boolean isBlackjack(Player player) {
        return player.score() == BUST_LIMIT && player.countHand() == BLACK_JACK_CARD_SIZE;
    }

    public static BasicRule getResultOfPlayer(Participant dealer, Participant player) {
        for (BasicRule gameResult : BasicRule.values()) {
            if (gameResult.condition.test(dealer, player)) {
                return gameResult;
            }
        }
        throw new IllegalArgumentException("승무패 중 결정할 수 없습니다.");
    }

    public String getValue() {
        return value;
    }

    // public static void set(Participant participant, BasicRule rule) {
    //     dealerResult.put(rule, dealerResult.getOrDefault(rule, 0) + 1);
    //     playerResults.put(participant, rule);
    // }

}
