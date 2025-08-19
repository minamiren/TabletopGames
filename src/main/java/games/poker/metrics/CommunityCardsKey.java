package games.poker.metrics;

import core.AbstractGameState;
import core.components.FrenchCard;
import core.interfaces.IStateKey;
import games.poker.PokerGameState;
import games.sushigo.SGGameState;

import java.util.Comparator;
import java.util.List;

public class CommunityCardsKey implements IStateKey{

        @Override
        public Object getKey(AbstractGameState state, int playerId) {
            PokerGameState pg = (PokerGameState) state;
            List<FrenchCard> community = new java.util.ArrayList<>();
            int communityDeckLength = pg.getCommunityCards().getSize();

            for(int i = 0; i < communityDeckLength; i++) {
                community.add(pg.getCommunityCards().get(i));
            }

            community.sort(Comparator.comparingInt(d -> d.suite.ordinal()*20+d.number));
            List<Integer> sortedDeck = community.stream().map(x-> x.number).toList();
            // convert to single number
            int sortedRet = 0;
            for(int i = 0; i < sortedDeck.size(); i++) {
                sortedRet += sortedDeck.get(i)*(20+sortedDeck.size()+i);
            }
            return sortedRet;
        }

}