package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import witchmod.WitchMod;
import witchmod.cards.familiar.BatFamiliar;
import witchmod.cards.familiar.CatFamiliar;
import witchmod.cards.familiar.FamiliarCardEnum;
import witchmod.cards.familiar.OwlFamiliar;
import witchmod.cards.familiar.RatFamiliar;
import witchmod.cards.familiar.RavenFamiliar;
import witchmod.cards.familiar.ToadFamiliar;

public class SummonFamiliarPower extends AbstractWitchPower {
	public static final String POWER_ID = "SummonFamiliar";
	public static final String NAME = "Summon Familiar";
	public static final String[] DESCRIPTIONS = new String[]{ "At the start of your turn, add ", "  to your hand" };
	public static final String IMG = "powers/summonfamiliar.png";
	private FamiliarCardEnum card;
	private boolean upgraded;
	public SummonFamiliarPower(AbstractCreature owner, FamiliarCardEnum card, boolean upgraded) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.upgraded = upgraded;
		this.card = card;
		this.updateDescription();
		this.img = new Texture(WitchMod.getResourcePath(IMG));
		this.type = PowerType.BUFF;

	}

	@Override
	public void updateDescription() {
		description = DESCRIPTIONS[0]+getCardName(card)+DESCRIPTIONS[1];
	}

	@Override
	public void atStartOfTurn() {
		if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
			flash();
			AbstractCard toCreate = familiarFactory(card);
			if (upgraded) {
				toCreate.upgrade();
				toCreate.upgraded = true;
			}
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(toCreate, 1, false));
		}
	}

	private AbstractCard familiarFactory(FamiliarCardEnum card){
		switch (card) {
		case BAT: return new BatFamiliar();
		case CAT: return new CatFamiliar();
		case OWL: return new OwlFamiliar();
		case RAT: return new RatFamiliar();
		case TOAD: return new ToadFamiliar();
		case RAVEN: return new RavenFamiliar();
		}
		
		return new CatFamiliar();
	}
	
	private String getCardName(FamiliarCardEnum card){
		switch (card) {
		case BAT: return upgraded?"an upgraded Bat":"a Bat";
		case CAT: return upgraded?"an upgraded Cat":"a Cat";
		case OWL: return upgraded?"an upgraded Owl":"an Owl";
		case RAT: return upgraded?"an upgraded Rat":"a Rat";
		case TOAD: return upgraded?"an upgraded Toad":"a Toad";
		case RAVEN: return upgraded?"an upgraded Raven":"a Raven";
		}
		
		return "MISSING CARD "+card.toString();
	}
	
	public static AbstractCard getRandomFamiliarCard(){		
		switch ((int)(Math.random()*6)) {
		case 0: return new BatFamiliar();
		case 1: return new RatFamiliar();
		case 2: return new OwlFamiliar();
		case 3: return new ToadFamiliar();
		case 4: return new RavenFamiliar();
		default: return new CatFamiliar();
		}
	}
}

