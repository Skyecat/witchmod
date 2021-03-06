package witchmod.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.WitchMod;

public class TwistedMindPower extends AbstractWitchPower {
    public static final String POWER_ID = "TwistedMindPower";
    public static final String NAME = "Twisted Mind";
    public static final String[] DESCRIPTIONS = new String[]{ "When you play the first card each turn, all enemies lose health equal to the cost of that card",
    		" times.",
    		" NL Used for this turn."};
    public static final String IMG = "powers/twistedmind.png";
    private boolean used = false;
    public TwistedMindPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.updateDescription();
        this.img = new Texture(WitchMod.getResourcePath(IMG));
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.isTurnBased = false;
    }

    @Override
    public void updateDescription() {
    	description = DESCRIPTIONS[0];
    	if (amount > 1) {
    		description += " "+amount+DESCRIPTIONS[1];
    	} else {
    		description += ".";
    	}
    	if (used) {
    		renderColor = Color.GRAY;
    		description += DESCRIPTIONS[2];
    	} else {
    		renderColor = null;
    	}
    }
    
    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
    	if (used) {
    		return;
    	}
    	if (card.type == CardType.ATTACK || card.type == CardType.SKILL || card.type == CardType.POWER) {
    		int finalDamage;
    		if (card.cost == -2) { //X
    			finalDamage = AbstractDungeon.player.energy.energy;
    		} else if (card.cost == -1) {
    			finalDamage = 0;
    		} else {
    			finalDamage = card.cost;
    		}
    		finalDamage *= amount;
    		if (finalDamage > 0) {
    			AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(finalDamage, true), DamageType.HP_LOSS, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
        		flash();	
    		}
    		used = true;
    		updateDescription();
    	}
    }
    
    @Override
    public void atStartOfTurn(){
    	used = false;
    	updateDescription();
    }
    
}

