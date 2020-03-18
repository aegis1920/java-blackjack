package blackjack.domain.Rule;

import blackjack.domain.participants.Participants;

public interface Rule {
    void judge(Participants participants);
}
