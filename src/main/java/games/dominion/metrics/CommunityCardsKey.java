package games.dominion.metrics;

import core.AbstractGameState;
import core.interfaces.IStateKey;
import games.dominion.DominionConstants;
import games.dominion.DominionGameState;
import games.dominion.cards.CardType;
import games.dominion.cards.DominionCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommunityCardsKey implements IStateKey {

    @Override
    public Object getKey(AbstractGameState state, int playerId) {
        DominionGameState dg = (DominionGameState) state;
        List<DominionCard> communityCards = new ArrayList<>();
        List<DominionCard> playedCards = new ArrayList<>();
        List<Integer> allCards = new ArrayList<>();
        Map<CardType,Integer> cardsInGame = dg.getCardsIncludedInGame();
        for(int i = 0; i < dg.getNPlayers(); i++) {
            for(DominionCard card : dg.getDeck(DominionConstants.DeckType.TABLE,i)) {
                playedCards.add(card);
            }
        }
        List<Integer> playedSorted = new ArrayList<>(playedCards.stream().map(x -> x.cardType().ordinal()).toList());
        playedSorted.sort(Integer::compare);
        allCards.addAll(playedSorted);
//        for(CardType type : cardsInGame.keySet()) {
//            for(int i = 0; i < cardsInGame.get(type); i++) {
//                communityCards.add(DominionCard.create(type));
//            }
//        }
//        List<Integer> communitySorted = new ArrayList<>(communityCards.stream().map(x -> x.cardType().ordinal()).toList());
//        communitySorted.sort(Integer::compare);
//        allCards.addAll(communitySorted);

        return allCards;
    }
}
