package games.poker.metrics;

import core.AbstractGameState;
import core.components.FrenchCard;
import core.interfaces.IStateKey;
import games.poker.PokerGameState;

import java.util.Comparator;
import java.util.List;

public class HashCodeKey implements IStateKey {
    @Override
    public Object getKey(AbstractGameState state, int playerId) {
        return state.hashCode();
    }
}
