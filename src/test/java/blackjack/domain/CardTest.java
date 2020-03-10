package blackjack.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("createCards() 메서드가 필요한 카드들을 생성하는지 테스트")
    @Test
    void createCardsTest() {
        List<Card> cards = Card.createCards();
        Assertions.assertThat(cards.size()).isEqualTo(52);
    }
}
