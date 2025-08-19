package games.poker.metrics;

import core.AbstractGameState;
import core.components.FrenchCard;
import core.interfaces.IStateKey;
import games.poker.PokerGameState;

import java.util.Comparator;
import java.util.List;

public class AllKnownCardsKey implements IStateKey{

        @Override
        public Object getKey(AbstractGameState state, int playerId) {
            PokerGameState pg = (PokerGameState) state;
            List<FrenchCard> community = new java.util.ArrayList<>();
            List<FrenchCard> playerdeck = new java.util.ArrayList<>();
            int playerDeckLength = pg.getPlayerDecks().get(playerId).getSize();
            int communityDeckLength = pg.getCommunityCards().getSize();

            for(int i = 0; i < communityDeckLength; i++) {
                community.add(pg.getCommunityCards().get(i));
            }
            for(int i = 0; i < playerDeckLength; i++) {
                playerdeck.add(pg.getPlayerDecks().get(playerId).get(i));
            }

            community.sort(Comparator.comparingInt(d -> d.suite.ordinal()*20+d.number));
            playerdeck.sort(Comparator.comparingInt(d -> d.suite.ordinal()*20+d.number));
            community.addAll(playerdeck);
            List<Integer> sortedDeck = community.stream().map(x-> x.number).toList();
            // convert to single number
            int sortedRet = 0;
            for(int i = 0; i < sortedDeck.size(); i++) {
                sortedRet += sortedDeck.get(i)*(20+sortedDeck.size()+i);
            }
            return sortedRet;
        }

}