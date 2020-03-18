package blackjack.domain.Result;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import blackjack.domain.participants.Money;
import blackjack.domain.participants.Participant;

public enum MoneyRule implements Rule {
    WIN_BLACK_JACK_MONEY(basicRule -> basicRule == BasicRule.WIN_BLACK_JACK,
        (player, bettingMoney) -> player.makeBonus(bettingMoney)),
    WIN_MONEY(basicRule -> basicRule == BasicRule.WIN,
        (player, bettingMoney) -> player.make(bettingMoney)),
    LOSE_MONEY(basicRule -> basicRule == BasicRule.LOSE,
        (player, bettingMoney) -> player.lose(bettingMoney));

    private final Predicate<BasicRule> expression;
    private final BiConsumer<Participant, Money> logic;

    MoneyRule(final Predicate<BasicRule> expression, final BiConsumer<Participant, Money> logic) {
        this.expression = expression;
        this.logic = logic;
    }

    public void applyResultOfPlayer(Participant participant, Money money, BasicRule basicRule) {
        Arrays.stream(MoneyRule.values())
            .filter(moneyRule -> moneyRule.expression.test(basicRule))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("없는 값입니다!"))
            .logic
            .accept(participant, money);
    }

    @Override
    public void set(Participant participant, Rule rule) {

    }
}