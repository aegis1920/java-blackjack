package blackjack.domain.Result;

import blackjack.domain.participants.Participant;

public interface Rule {
    void set(Participant participant, Rule rule);
}