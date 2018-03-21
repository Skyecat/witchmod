/*
 * Decompiled with CFR.
 */
package witchmod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BlackCat extends AbstractWitchRelic {
    public static final String ID = "BlackCat";
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final String IMG = "blackcat.png";
    private static final LandingSound SOUND = LandingSound.FLAT;

    public BlackCat() {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(AbstractDungeon.player,null,AbstractDungeon.returnRandomCurse(),1,true,true));
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.player.getRelic(ID).flash();
    }
    
    @Override
    public void onCardDraw(AbstractCard drawnCard) {
    	if (drawnCard.type == CardType.CURSE){
    		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
    		AbstractDungeon.player.getRelic(ID).flash();
    	}
    	
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BlackCat();
    }
}
