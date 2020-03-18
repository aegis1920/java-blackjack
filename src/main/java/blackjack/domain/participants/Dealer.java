package blackjack.domain.participants;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public class Dealer implements Participant {
    public static final int DEALER_DRAW_CRITERIA = 17;

    private Hand hand;
    private Money money;

    public Dealer() {
        this.hand = new Hand();
        this.money = new Money(0);
    }

    public int countAddedCard() {
        return hand.countAddedCard();
    }

    @Override
    public void draw(Card card) {
        hand.add(card);
    }

    @Override
    public int score() {
        return hand.calculate();
    }

    @Override
    public void drawMoreCard(final Deck deck) {
        while (needsMoreCard()) {
            draw(deck.pop());
        }
    }

    public boolean needsMoreCard() {
        return hand.calculate() < DEALER_DRAW_CRITERIA;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public String handStatus() {
        return hand.toString();
    }

    @Override
    public String getName() {
        return "딜러";
    }

    @Override
    public void make(Money opponentMoney) {
        this.money = this.money.add(opponentMoney);
    }

    @Override
    public void lose(Money opponentMoney) {
        this.money = this.money.subtract(opponentMoney);
    }

    @Override
    public Money getMoney() {
        return money;
    }


}
