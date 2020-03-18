package blackjack.domain;

import static blackjack.domain.participants.HandTest.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.Rule.BasicResult;
import blackjack.domain.Rule.Rusult;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;

public class BasicResultTest {

    @DisplayName("decideWinner()이 참가자들의 Result를 올바르게 바꾸는지")
    @Test
    void judgeTest() {
        Dealer dealer = new Dealer();
        Player pobi = new Player("pobi");
        Player jason = new Player("jason");

        CARDS_21_ACE_AS_ELEVEN.forEach(dealer::draw);
        CARDS_8.forEach(pobi::draw);
        CARDS_22_BUSTED.forEach(jason::draw);

        List<Player> players = Arrays.asList(pobi, jason);

        Participants participants = new Participants(dealer, players);
        Rusult rusult = new BasicResult();
        rusult.judge(participants);

        assertThat(pobi.getGameResult().getValue()).isEqualTo(LOSE.getValue());
        assertThat(jason.getGameResult().getValue()).isEqualTo(LOSE.getValue());
        assertThat(dealer.getResult(WIN)).isEqualTo(2);
        assertThat(dealer.getResult(LOSE)).isEqualTo(0);
    }
}
