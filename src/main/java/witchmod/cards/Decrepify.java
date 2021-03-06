package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import witchmod.powers.DecrepitPower;

public class Decrepify extends AbstractWitchCard {
	public static final String ID = "Decrepify";
	public static final	String NAME = "Decrepify";
	public static final	String IMG = "cards/decrepify.png";
	public static final	String DESCRIPTION = "Apply !M! Decrepit to a random enemy X times.";
	public static final	String DESCRIPTION_UPGRADED = "Apply !M! Decrepit to a random enemy X+1 times.";

	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.SKILL;

	private static final int POOL = 1;
	private static final int COST = -1;

	private static final int POWER = 2;


	public Decrepify() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseMagicNumber = this.magicNumber = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int energy = EnergyPanel.getCurrentEnergy();
		int counter = upgraded?energy+1:energy;
		while (counter > 0) {
			AbstractMonster monster = AbstractDungeon.getRandomMonster();
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new DecrepitPower(monster, magicNumber, false),magicNumber, true));
			counter--;
		}
		p.energy.use(energy);
	}

	public AbstractCard makeCopy() {
		return new Decrepify();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			rawDescription = DESCRIPTION_UPGRADED;
			initializeDescription();
		}
	}
}
