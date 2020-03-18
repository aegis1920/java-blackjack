package blackjack.domain.Result;

import blackjack.domain.Rule.BasicRule;
import blackjack.domain.participants.Participant;

public enum GameResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    NONE("해당하는 값이 없습니다");

    private final String value;

    GameResult(final String value) {
        this.value = value;
    }

    public static GameResult resultOfPlayer(Participant dealer, Participant player) {
        if (BasicRule.playerWinCondition(dealer, player)) {
            return WIN;
        }

        if (BasicRule.playerDrawCondition(dealer, player)) {
            return DRAW;
        }

        if(BasicRule.playerLoseCondition(dealer, player)) {
            return LOSE;
        }
        return NONE;
    }
}
