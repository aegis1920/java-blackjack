package blackjack.domain.Result;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

import blackjack.domain.Rule.MoneyResult;
import blackjack.domain.participants.Money;
import blackjack.domain.participants.Participant;

public enum MoneyRule {
    WIN_BLACK_JACK_MONEY(basicRule -> basicRule == BasicRule.WIN_BLACK_JACK,
        (player, bettingMoney) -> MoneyResult.makeBonus(player, bettingMoney)),
    WIN_MONEY(basicRule -> basicRule == BasicRule.WIN,
        (player, bettingMoney) -> MoneyResult.make(player, bettingMoney)),
    LOSE_MONEY(basicRule -> basicRule == BasicRule.LOSE,
        (player, bettingMoney) -> MoneyResult.lose(player, bettingMoney));

    private final Predicate<BasicRule> expression;
    private final BiConsumer<Participant, Money> logic;

    MoneyRule(final Predicate<BasicRule> expression, final BiConsumer<Participant, Money> logic) {
        this.expression = expression;
        this.logic = logic;
    }

    public static boolean isNotDraw(BasicRule basicRule) {
        return basicRule == BasicRule.DRAW;
    }

    public static void applyResultOfPlayer(Participant participant, Money money, BasicRule basicRule) {
        System.out.println(participant.getName() + " : " + money + " : " + basicRule.getValue());

        for (MoneyRule moneyRule : MoneyRule.values()) {
            if (moneyRule.expression.test(basicRule)) {
                moneyRule.logic.accept(participant, money);
            }
        }

        // Arrays.stream(MoneyRule.values())
        //     .filter(moneyRule -> moneyRule.isNotDraw(basicRule))
        //     .filter(moneyRule -> moneyRule.expression.test(basicRule))
        //     .findFirst()
        //     .orElseThrow(() -> new IllegalArgumentException("없는 값입니다!"))
        //     .logic
        //     .accept(participant, money);
    }
}