package witchmod.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import basemod.helpers.TooltipInfo;

public class IllusionOfStrength extends AbstractWitchCard {
	public static final String ID = "IllusionOfStrength";
	public static final	String NAME = "Illusory Strength";
	public static final	String IMG = "cards/placeholder_power.png";
	public static final	String DESCRIPTION = "Gain !M! Strength. Shuffle a Delusion of Strength in your draw pile.";

	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;

	private static final int POOL = 1;

	private static final int COST = 1;

	private static final int POWER = 4;
	private static final int UPGRADE_BONUS = 2;


	public IllusionOfStrength() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseMagicNumber = this.magicNumber = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(AbstractDungeon.player, AbstractDungeon.player, new IllusionOfStrengthCurse(), 1, true, false));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber),magicNumber));
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		List<TooltipInfo> out = new ArrayList<>();
		out.add(new TooltipInfo("Delusion of Strength", "Reduces your Strength at the end of the turn while in hand."));
		return out;
	}

	public AbstractCard makeCopy() {
		return new IllusionOfStrength();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
		}
	}
}
