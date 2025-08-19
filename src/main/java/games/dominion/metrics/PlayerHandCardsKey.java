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

public class PlayerHandCardsKey implements IStateKey {

    @Override
    public Object getKey(AbstractGameState state, int playerId) {
        DominionGameState dg = (DominionGameState) state;
        List<DominionCard> playerCards = new ArrayList<>();
        List<DominionCard> playedCards = new ArrayList<>();
        List<Integer> allCards = new ArrayList<>();
        Map<CardType,Integer> cardsInGame = dg.getCardsIncludedInGame();

        for(DominionCard card : dg.getDeck(DominionConstants.DeckType.HAND,playerId)) {
            playerCards.add(card);
        }
        List<Integer> playerSorted = new ArrayList<>(playerCards.stream().map(x -> x.cardType().ordinal()).toList());
        playerSorted.sort(Integer::compare);
        allCards.addAll(playerSorted);

        return allCards;
    }
}
