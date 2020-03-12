package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
    public static void run() {
        OutputView.nameInstruction();
        Deck deck = Deck.create();
        Participants participants = new Participants(new Dealer(), InputView.getInput());
        OutputView.shareFirstPair(participants);
        participants.initialDraw(deck);
        OutputView.participantsStatus(participants);
        for (Participant participant : participants) {
            dealerDrawsMore(deck, participant);
            playersDrawMore(deck, participant);
        }
        // OutputView.resultStatus(participants);
    }

    private static void dealerDrawsMore(final Deck deck, final Participant participant) {
        if (participant.isDealer()) {
            participant.drawMoreCard(deck);
        }
    }

    private static void playersDrawMore(final Deck deck, final Participant participant) {
        if (!participant.isDealer()) {
            playersChooseToDraw(deck, (Player)participant);
        }
    }

    private static void playersChooseToDraw(final Deck deck, final Player player) {
        boolean wantsMoreCard;
        do {
            OutputView.moreCardInstruction(player);
            wantsMoreCard = wantsToDrawMore(deck, player);
            OutputView.participantStatus(player);
        } while (wantsMoreCard && !player.isBusted());
    }

    private static boolean wantsToDrawMore(final Deck deck, final Player player) {
        final boolean wantsMoreCard;
        wantsMoreCard = InputView.yesOrNo();
        if (wantsMoreCard) {
            player.draw(deck);
        }
        return wantsMoreCard;
    }
}
