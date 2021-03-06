package witchmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RiteOfAutumnAction extends AbstractGameAction{
	private AbstractPlayer player;
	public static final String TEXT = "Pick any number of cards to exhaust.";
	public RiteOfAutumnAction() {
		this.duration = Settings.ACTION_DUR_FAST;
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.player = AbstractDungeon.player;
	}

	@Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (player.hand.size() == 0) {
                isDone = true;
                return;
            }
            AbstractDungeon.handCardSelectScreen.open(TEXT, player.hand.size(), true, true);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
        	if (AbstractDungeon.handCardSelectScreen.selectedCards.group.size() > 0) {
                for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    player.hand.moveToExhaustPile(c);
                }
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, AbstractDungeon.handCardSelectScreen.selectedCards.group.size()));
        	}
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }
}