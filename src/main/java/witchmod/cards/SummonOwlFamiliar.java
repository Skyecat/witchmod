package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.cards.familiar.FamiliarCardEnum;
import witchmod.powers.SummonFamiliarPower;

public class SummonOwlFamiliar extends AbstractWitchCard {
	public static final String ID = "SummonOwlFamiliar";
	public static final	String NAME = "Owl Familiar";
	public static final	String IMG = "cards/summonowl.png";
	public static final	String DESCRIPTION = "At the start of your turn, add an Owl to your hand. NL Removes other Familiar powers.";
	public static final	String DESCRIPTION_UPGRADED = "At the start of your turn, add an upgraded Owl to your hand. Removes other Familiar powers.";

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;

	private static final int POOL = 1;
	private static final int COST = 0;



	public SummonOwlFamiliar() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower(SummonFamiliarPower.POWER_ID)) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, SummonFamiliarPower.POWER_ID));
		}
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SummonFamiliarPower(p, FamiliarCardEnum.OWL, upgraded)));
	}

	public AbstractCard makeCopy() {
		return new SummonOwlFamiliar();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			rawDescription = DESCRIPTION_UPGRADED;
			initializeDescription();
		}
	}
}
