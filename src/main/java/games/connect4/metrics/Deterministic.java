package games.connect4.metrics;

import core.AbstractGameState;
import core.interfaces.IStateKey;

public class Deterministic implements IStateKey {

    // should this be hash code?
    @Override
    public Object getKey(AbstractGameState state, int playerId) {
        return state.toString();
    }
}
