/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.touhoupixel.touhoupixeldungeon.actors.hero;

import com.touhoupixel.touhoupixeldungeon.Assets;
import com.touhoupixel.touhoupixeldungeon.Badges;
import com.touhoupixel.touhoupixeldungeon.Challenges;
import com.touhoupixel.touhoupixeldungeon.Dungeon;
import com.touhoupixel.touhoupixeldungeon.QuickSlot;
import com.touhoupixel.touhoupixeldungeon.actors.mobs.Eiki;
import com.touhoupixel.touhoupixeldungeon.items.BrokenSeal;
import com.touhoupixel.touhoupixeldungeon.items.Item;
import com.touhoupixel.touhoupixeldungeon.items.KogasaPrayer;
import com.touhoupixel.touhoupixeldungeon.items.MokouRibbon;
import com.touhoupixel.touhoupixeldungeon.items.Prayer;
import com.touhoupixel.touhoupixeldungeon.items.RenkoMemo;
import com.touhoupixel.touhoupixeldungeon.items.SpCard;
import com.touhoupixel.touhoupixeldungeon.items.Waterskin;
import com.touhoupixel.touhoupixeldungeon.items.YukariMemo;
import com.touhoupixel.touhoupixeldungeon.items.armor.ClothArmor;
import com.touhoupixel.touhoupixeldungeon.items.armor.PlateArmor;
import com.touhoupixel.touhoupixeldungeon.items.armor.PoppinPartyArmor;
import com.touhoupixel.touhoupixeldungeon.items.armor.RumiaArmor;
import com.touhoupixel.touhoupixeldungeon.items.armor.YuyukoArmor;
import com.touhoupixel.touhoupixeldungeon.items.artifacts.CloakOfShadows;
import com.touhoupixel.touhoupixeldungeon.items.artifacts.DriedRose;
import com.touhoupixel.touhoupixeldungeon.items.bags.ArcaneHolder;
import com.touhoupixel.touhoupixeldungeon.items.bags.FoodHolder;
import com.touhoupixel.touhoupixeldungeon.items.bags.MagicalHolster;
import com.touhoupixel.touhoupixeldungeon.items.bags.PotionBandolier;
import com.touhoupixel.touhoupixeldungeon.items.bags.ScrollHolder;
import com.touhoupixel.touhoupixeldungeon.items.bags.TailsmanHolder;
import com.touhoupixel.touhoupixeldungeon.items.bags.VelvetPouch;
import com.touhoupixel.touhoupixeldungeon.items.food.Cucumber;
import com.touhoupixel.touhoupixeldungeon.items.food.Food;
import com.touhoupixel.touhoupixeldungeon.items.food.TwoSoySauce;
import com.touhoupixel.touhoupixeldungeon.items.giftcard.BirthdayCard;
import com.touhoupixel.touhoupixeldungeon.items.potions.PotionOfBerserk;
import com.touhoupixel.touhoupixeldungeon.items.potions.PotionOfDoublespeed;
import com.touhoupixel.touhoupixeldungeon.items.potions.PotionOfExperience;
import com.touhoupixel.touhoupixeldungeon.items.potions.PotionOfHaste;
import com.touhoupixel.touhoupixeldungeon.items.potions.PotionOfHealing;
import com.touhoupixel.touhoupixeldungeon.items.potions.PotionOfInvisibility;
import com.touhoupixel.touhoupixeldungeon.items.potions.PotionOfLevitation;
import com.touhoupixel.touhoupixeldungeon.items.potions.PotionOfLightHealing;
import com.touhoupixel.touhoupixeldungeon.items.potions.PotionOfLiquidFlame;
import com.touhoupixel.touhoupixeldungeon.items.potions.PotionOfMight;
import com.touhoupixel.touhoupixeldungeon.items.potions.PotionOfMindVision;
import com.touhoupixel.touhoupixeldungeon.items.potions.PotionOfPurity;
import com.touhoupixel.touhoupixeldungeon.items.potions.elixirs.ElixirOfAquaticRejuvenation;
import com.touhoupixel.touhoupixeldungeon.items.potions.elixirs.ElixirOfHoneyedHealing;
import com.touhoupixel.touhoupixeldungeon.items.potions.exotic.PotionOfCleansing;
import com.touhoupixel.touhoupixeldungeon.items.potions.exotic.PotionOfYomi;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.ScrollOfIdentify;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.ScrollOfLullaby;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.ScrollOfRage;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.ScrollOfRecharging;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.ScrollOfRouteChange;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.ScrollOfSilence;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.ScrollOfSlowness;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.exotic.ScrollOfSirensSong;
import com.touhoupixel.touhoupixeldungeon.items.spells.KogasaHammer;
import com.touhoupixel.touhoupixeldungeon.items.spells.Recycle;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfCorrosion;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfHealWounds;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfMagicMissile;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfPurityBeam;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfReverseGravity;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfStableness;
import com.touhoupixel.touhoupixeldungeon.items.weapon.SpiritBow;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.BlazingStar;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.ButterflyFan;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.Dagger;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.DeadBeacon;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.EikiHammer;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.Gloves;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.Greatshield;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.HandAxe;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.HinaRibbon;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.JeweledBranch;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.LunaClock;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.MagesStaff;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.MurasaDipper;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.NitoriRod;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.RunicBlade;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.SmallSeiranHammer;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.TenkyuuCloak;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.TurnaboutCloak;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.WornShortsword;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.YachieHorn;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.YuyukoFoldingFan;
import com.touhoupixel.touhoupixeldungeon.items.weapon.missiles.ThrowingKnife;
import com.touhoupixel.touhoupixeldungeon.items.weapon.missiles.ThrowingStone;
import com.touhoupixel.touhoupixeldungeon.messages.Messages;
import com.watabou.utils.DeviceCompat;

import java.util.Calendar;

public enum HeroClass {

	WARRIOR( HeroSubClass.BERSERKER, HeroSubClass.GLADIATOR ),
	MAGE( HeroSubClass.BATTLEMAGE, HeroSubClass.WARLOCK ),
	ROGUE( HeroSubClass.ASSASSIN, HeroSubClass.FREERUNNER ),
	HUNTRESS( HeroSubClass.SNIPER, HeroSubClass.WARDEN ),
	REISENPLAYER( HeroSubClass.MOONRABBIT, HeroSubClass.DESERTER ),
	NITORIPLAYER( HeroSubClass.KAPPA, HeroSubClass.ENGINEER ),
	YUYUKOPLAYER( HeroSubClass.GOURMET, HeroSubClass.DEATHGHOST ),
	MURASAPLAYER( HeroSubClass.CAPTAIN, HeroSubClass.SHIPGHOST ),
	HINAPLAYER( HeroSubClass.SPINGOD, HeroSubClass.CURSEGOD ),
	KAGUYAPLAYER( HeroSubClass.TIMESTOP, HeroSubClass.TIMEMOVE ),
	KOGASAPLAYER( HeroSubClass.SLOWGIRL, HeroSubClass.HORRORGIRL ),
	YUKARIPLAYER( HeroSubClass.GAPMASTER, HeroSubClass.BORDERMASTER ),
	JUNKOPLAYER( HeroSubClass.PURITYGOD, HeroSubClass.PUREGOD ),
	RENKOPLAYER( HeroSubClass.STARSEEKER, HeroSubClass.LUNARSEEKER ),
	SEIJAPLAYER( HeroSubClass.TURNMASTER, HeroSubClass.GRAVMASTER ),
	TENKYUUPLAYER( HeroSubClass.SOUCOLLECTOR, HeroSubClass.MISCCOLLECTOR );

	private HeroSubClass[] subClasses;

	HeroClass( HeroSubClass...subClasses ) {
		this.subClasses = subClasses;
	}

	public void initHero( Hero hero ) {

		hero.heroClass = this;
		Talent.initClassTalents(hero);

		Item i = new ClothArmor().identify();
		hero.belongings.armor = (ClothArmor)i;

		i = new Food();
		i.collect();

		new ArcaneHolder().collect();
		new FoodHolder().collect();
		new MagicalHolster().collect();
		new PotionBandolier().collect();
		new ScrollHolder().collect();
		new TailsmanHolder().collect();
		new VelvetPouch().collect();

		Waterskin waterskin = new Waterskin();
		waterskin.collect();

		KogasaHammer kh = new KogasaHammer();
		kh.quantity(3).collect();

		//test zone//

		//test zone//

		//birthday present zone//
		BirthdayCard card = new BirthdayCard();

		final Calendar calendar = Calendar.getInstance();
		switch(calendar.get(Calendar.MONTH)) {
			case Calendar.JANUARY:
				if (Dungeon.hero.heroClass == WARRIOR && calendar.get(Calendar.DATE) == 6) {
					card.collect();
				}
			case Calendar.FEBRUARY:
				if (Dungeon.hero.heroClass == NITORIPLAYER && calendar.get(Calendar.DATE) == 10) {
					card.collect();
				}
				if (Dungeon.hero.heroClass == YUYUKOPLAYER && calendar.get(Calendar.DATE) == 23) {
					card.collect();
				}
			case Calendar.MARCH:
				if (Dungeon.hero.heroClass == HINAPLAYER && calendar.get(Calendar.DATE) == 3) {
					card.collect();
				}
				if (Dungeon.hero.heroClass == HUNTRESS && calendar.get(Calendar.DATE) == 7) {
					card.collect();
				}
				if (Dungeon.hero.heroClass == MURASAPLAYER && calendar.get(Calendar.DATE) == 7) {
					card.collect();
				}
			case Calendar.MAY:
				if (Dungeon.hero.heroClass == ROGUE && calendar.get(Calendar.DATE) == 14) {
					card.collect();
				}
			case Calendar.JUNE:
				if (Dungeon.hero.heroClass == JUNKOPLAYER && calendar.get(Calendar.DATE) == 5) {
					card.collect();
				}
			case Calendar.JULY:
				if (Dungeon.hero.heroClass == MAGE && calendar.get(Calendar.DATE) == 7) {
					card.collect();
				}
			case Calendar.AUGUST:
				if (Dungeon.hero.heroClass == REISENPLAYER && calendar.get(Calendar.DATE) == 2) {
					card.collect();
				}
				if (Dungeon.hero.heroClass == SEIJAPLAYER && calendar.get(Calendar.DATE) == 8) {
					card.collect();
				}
				if (Dungeon.hero.heroClass == YUKARIPLAYER && calendar.get(Calendar.DATE) == 9) {
					card.collect();
				}
			case Calendar.SEPTEMBER:
				if (Dungeon.hero.heroClass == KAGUYAPLAYER && calendar.get(Calendar.DATE) == 8) {
					card.collect();
				}
				if (Dungeon.hero.heroClass == KOGASAPLAYER && calendar.get(Calendar.DATE) == 19) {
					card.collect();
				}
			case Calendar.OCTOBER:
				if (Dungeon.hero.heroClass == TENKYUUPLAYER && calendar.get(Calendar.DATE) == 9) {
					card.collect();
				}
			case Calendar.NOVEMBER:
				if (Dungeon.hero.heroClass == RENKOPLAYER && calendar.get(Calendar.DATE) == 5) {
					card.collect();
				}
		}
		//birthday present zone//

		if (!Dungeon.isChallenged(Challenges.ATHEISM)) {
			Prayer pray = new Prayer();
			pray.collect();
			Dungeon.quickslot.setSlot(3, pray);
		}

		if (Dungeon.isChallenged(Challenges.CHERRY_BLOSSOM_BLOOM)) {
			SpCard spcard = new SpCard();
			spcard.collect();
		}

		if (Dungeon.isChallenged(Challenges.TENSHI_EARTHQUAKE)) {
			PotionOfLevitation pol = new PotionOfLevitation();
			pol.quantity(5).identify().collect();
		}

		PotionOfHealing poh = new PotionOfHealing();
		poh.quantity(3).collect();

		new PotionOfHealing().identify();
		new ScrollOfRouteChange().identify();

		switch (this) {
			case WARRIOR:
				initWarrior( hero );
				break;

			case MAGE:
				initMage( hero );
				break;

			case ROGUE:
				initRogue( hero );
				break;

			case HUNTRESS:
				initHuntress( hero );
				break;

			case REISENPLAYER:
				initReisenplayer( hero );
				break;

			case NITORIPLAYER:
				initNitoriplayer( hero );
				break;

			case YUYUKOPLAYER:
				initYuyukoplayer( hero );
				break;

			case MURASAPLAYER:
				initMurasaplayer( hero );
				break;

			case HINAPLAYER:
				initHinaplayer( hero );
				break;

			case KAGUYAPLAYER:
				initKaguyaplayer( hero );
				break;

			case KOGASAPLAYER:
				initKogasaplayer( hero );
				break;

			case YUKARIPLAYER:
				initYukariplayer( hero );
				break;

			case JUNKOPLAYER:
				initJunkoplayer( hero );
				break;

			case RENKOPLAYER:
				initRenkoplayer( hero );
				break;

			case SEIJAPLAYER:
				initSeijaplayer( hero );
				break;

			case TENKYUUPLAYER:
				initTenkyuuplayer( hero );
				break;
		}

		for (int s = 0; s < QuickSlot.SIZE; s++){
			if (Dungeon.quickslot.getItem(s) == null){
				Dungeon.quickslot.setSlot(s, waterskin);
				break;
			}
		}

	}

	private static void initWarrior( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();
		ThrowingStone stones = new ThrowingStone();
		stones.quantity(5).collect();

		PotionOfLightHealing LightHealing = new PotionOfLightHealing();
		LightHealing.quantity(3).collect();

		Dungeon.quickslot.setSlot(0, stones);

		if (hero.belongings.armor != null){
			hero.belongings.armor.affixSeal(new BrokenSeal());
		}

		new PotionOfLightHealing().identify();
		new PotionOfHealing().identify();
		new ScrollOfRage().identify();
	}

	private static void initMage( Hero hero ) {
		MagesStaff staff;

		staff = new MagesStaff(new WandOfMagicMissile());

		(hero.belongings.weapon = staff).identify();
		hero.belongings.weapon.activate(hero);

		Dungeon.quickslot.setSlot(0, staff);

		new ScrollOfTransmutation().identify();
		new PotionOfLiquidFlame().identify();
	}

	private static void initRogue( Hero hero ) {
		(hero.belongings.weapon = new Dagger()).identify();

		CloakOfShadows cloak = new CloakOfShadows();
		(hero.belongings.artifact = cloak).identify();
		hero.belongings.artifact.activate( hero );

		ThrowingKnife knives = new ThrowingKnife();
		knives.quantity(5).collect();

		Dungeon.quickslot.setSlot(0, cloak);
		Dungeon.quickslot.setSlot(1, knives);

		new ScrollOfMagicMapping().identify();
		new PotionOfInvisibility().identify();
	}

	private static void initHuntress( Hero hero ) {

		(hero.belongings.weapon = new Gloves()).identify();
		SpiritBow bow = new SpiritBow();
		bow.identify().collect();

		Dungeon.quickslot.setSlot(0, bow);

		new PotionOfMindVision().identify();
		new ScrollOfLullaby().identify();
	}

	private static void initReisenplayer( Hero hero ) {
		(hero.belongings.weapon = new SmallSeiranHammer()).identify();

		new ScrollOfSilence().identify();
		new PotionOfMight().identify();
	}

	private static void initNitoriplayer( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();

		Cucumber cm = new Cucumber();
		cm.quantity(5).collect();

		Recycle rc = new Recycle();
		rc.quantity(3).collect();

		WandOfStableness wos = new WandOfStableness();
		wos.identify().collect();

		Dungeon.quickslot.setSlot(0, wos);
		Dungeon.quickslot.setSlot(1, cm);

		new ScrollOfTeleportation().identify();
		new PotionOfBerserk().identify();
	}

	private static void initYuyukoplayer( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();

		TwoSoySauce soy = new TwoSoySauce();
		soy.quantity(3).collect();

		PotionOfYomi yomi = new PotionOfYomi();
		yomi.quantity(3).collect();

		Dungeon.quickslot.setSlot(0, soy);
		Dungeon.quickslot.setSlot(1, yomi);

		new ScrollOfSlowness().identify();
		new PotionOfLightHealing().identify();
	}

	private static void initMurasaplayer( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();

		PotionOfHealing Healing = new PotionOfHealing();
		Healing.quantity(27).collect();

		ElixirOfAquaticRejuvenation eoar = new ElixirOfAquaticRejuvenation();
		eoar.quantity(5).collect();

		Dungeon.quickslot.setSlot(0, eoar);

		new ScrollOfMagicMapping().identify();
		new PotionOfHaste().identify();
	}

	private static void initHinaplayer( Hero hero ) {
		(hero.belongings.weapon = new HinaRibbon()).identify();

		PotionOfCleansing soy2 = new PotionOfCleansing();
		soy2.quantity(3).collect();

		ScrollOfRemoveCurse yomi2 = new ScrollOfRemoveCurse();
		yomi2.quantity(3).collect();

		Dungeon.quickslot.setSlot(0, soy2);
		Dungeon.quickslot.setSlot(1, yomi2);

		new ScrollOfRemoveCurse().identify();
		new PotionOfPurity().identify();
	}

	private static void initKaguyaplayer( Hero hero ) {
		(hero.belongings.weapon = new JeweledBranch()).identify();

		ScrollOfSirensSong soss = new ScrollOfSirensSong();
		soss.quantity(3).collect();

		WandOfHealWounds wohw = new WandOfHealWounds();
		wohw.identify().collect();

		Dungeon.quickslot.setSlot(0, wohw);

		new ScrollOfLullaby().identify();
		new PotionOfLightHealing().identify();
	}

	private static void initKogasaplayer( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();

		Food food = new Food();
		food.quantity(9).collect();

		KogasaPrayer kogasapray = new KogasaPrayer();
		kogasapray.collect();
		Dungeon.quickslot.setSlot(0, kogasapray);

		new PotionOfHaste().identify();
		new PotionOfBerserk().identify();
		new PotionOfDoublespeed().identify();
	}

	private static void initYukariplayer( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();

		YukariMemo ym = new YukariMemo();
		ym.collect();
		Dungeon.quickslot.setSlot(0, ym);
	}

	private static void initJunkoplayer( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();

		WandOfPurityBeam wopb = new WandOfPurityBeam();
		wopb.identify().collect();
		Dungeon.quickslot.setSlot(0, wopb);
	}

	private static void initRenkoplayer( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();

		RenkoMemo rm = new RenkoMemo();
		rm.collect();
		Dungeon.quickslot.setSlot(0, rm);
	}

	private static void initSeijaplayer( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();

		WandOfReverseGravity worg = new WandOfReverseGravity();
		worg.identify().collect();
		Dungeon.quickslot.setSlot(0, worg);
	}

	private static void initTenkyuuplayer( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();
	}

	public String title() {
		return Messages.get(HeroClass.class, name());
	}

	public String desc(){
		return Messages.get(HeroClass.class, name()+"_desc");
	}

	public HeroSubClass[] subClasses() {
		return subClasses;
	}

	public String spritesheet() {
		switch (this) {
			case WARRIOR: default:
				return Assets.Sprites.WARRIOR;
			case MAGE:
				return Assets.Sprites.MAGE;
			case ROGUE:
				return Assets.Sprites.ROGUE;
			case HUNTRESS:
				return Assets.Sprites.HUNTRESS;
			case REISENPLAYER:
				return Assets.Sprites.REISENPLAYER;
			case NITORIPLAYER:
				return Assets.Sprites.NITORIPLAYER;
			case YUYUKOPLAYER:
				return Assets.Sprites.YUYUKOPLAYER;
			case MURASAPLAYER:
				return Assets.Sprites.MURASAPLAYER;
			case HINAPLAYER:
				return Assets.Sprites.HINAPLAYER;
			case KAGUYAPLAYER:
				return Assets.Sprites.KAGUYAPLAYER;
			case KOGASAPLAYER:
				return Assets.Sprites.KOGASAPLAYER;
			case YUKARIPLAYER:
				return Assets.Sprites.YUKARIPLAYER;
			case JUNKOPLAYER:
				return Assets.Sprites.JUNKOPLAYER;
			case RENKOPLAYER:
				return Assets.Sprites.RENKOPLAYER;
			case SEIJAPLAYER:
				return Assets.Sprites.SEIJAPLAYER;
			case TENKYUUPLAYER:
				return Assets.Sprites.TENKYUUPLAYER;
		}
	}

	public String splashArt(){
		switch (this) {
			case WARRIOR: default:
				return Assets.Splashes.WARRIOR;
			case MAGE:
				return Assets.Splashes.MAGE;
			case ROGUE:
				return Assets.Splashes.ROGUE;
			case HUNTRESS:
				return Assets.Splashes.HUNTRESS;
			case REISENPLAYER:
				return Assets.Splashes.REISENPLAYER;
			case NITORIPLAYER:
				return Assets.Splashes.NITORIPLAYER;
			case YUYUKOPLAYER:
				return Assets.Splashes.YUYUKOPLAYER;
			case MURASAPLAYER:
				return Assets.Splashes.MURASAPLAYER;
			case HINAPLAYER:
				return Assets.Splashes.HINAPLAYER;
			case KAGUYAPLAYER:
				return Assets.Splashes.KAGUYAPLAYER;
			case KOGASAPLAYER:
				return Assets.Splashes.KOGASAPLAYER;
			case YUKARIPLAYER:
				return Assets.Splashes.YUKARIPLAYER;
			case JUNKOPLAYER:
				return Assets.Splashes.JUNKOPLAYER;
			case RENKOPLAYER:
				return Assets.Splashes.RENKOPLAYER;
			case SEIJAPLAYER:
				return Assets.Splashes.SEIJAPLAYER;
			case TENKYUUPLAYER:
				return Assets.Splashes.TENKYUUPLAYER;
		}
	}

	public String[] perks() {
		switch (this) {
			case WARRIOR: default:
				return new String[]{
						Messages.get(HeroClass.class, "warrior_perk1"),
						Messages.get(HeroClass.class, "warrior_perk2"),
						Messages.get(HeroClass.class, "warrior_perk3"),
						Messages.get(HeroClass.class, "warrior_perk4"),
						Messages.get(HeroClass.class, "warrior_perk5"),
				};
			case MAGE:
				return new String[]{
						Messages.get(HeroClass.class, "mage_perk1"),
						Messages.get(HeroClass.class, "mage_perk2"),
						Messages.get(HeroClass.class, "mage_perk3"),
						Messages.get(HeroClass.class, "mage_perk4"),
						Messages.get(HeroClass.class, "mage_perk5"),
				};
			case ROGUE:
				return new String[]{
						Messages.get(HeroClass.class, "rogue_perk1"),
						Messages.get(HeroClass.class, "rogue_perk2"),
						Messages.get(HeroClass.class, "rogue_perk3"),
						Messages.get(HeroClass.class, "rogue_perk4"),
						Messages.get(HeroClass.class, "rogue_perk5"),
				};
			case HUNTRESS:
				return new String[]{
						Messages.get(HeroClass.class, "huntress_perk1"),
						Messages.get(HeroClass.class, "huntress_perk2"),
						Messages.get(HeroClass.class, "huntress_perk3"),
						Messages.get(HeroClass.class, "huntress_perk4"),
						Messages.get(HeroClass.class, "huntress_perk5"),
				};
			case REISENPLAYER:
				return new String[]{
						Messages.get(HeroClass.class, "reisenplayer_perk1"),
						Messages.get(HeroClass.class, "reisenplayer_perk2"),
						Messages.get(HeroClass.class, "reisenplayer_perk3"),
						Messages.get(HeroClass.class, "reisenplayer_perk4"),
						Messages.get(HeroClass.class, "reisenplayer_perk5"),
				};
			case NITORIPLAYER:
				return new String[]{
						Messages.get(HeroClass.class, "nitoriplayer_perk1"),
						Messages.get(HeroClass.class, "nitoriplayer_perk2"),
						Messages.get(HeroClass.class, "nitoriplayer_perk3"),
						Messages.get(HeroClass.class, "nitoriplayer_perk4"),
						Messages.get(HeroClass.class, "nitoriplayer_perk5"),
				};
			case YUYUKOPLAYER:
				return new String[]{
						Messages.get(HeroClass.class, "yuyukoplayer_perk1"),
						Messages.get(HeroClass.class, "yuyukoplayer_perk2"),
						Messages.get(HeroClass.class, "yuyukoplayer_perk3"),
						Messages.get(HeroClass.class, "yuyukoplayer_perk4"),
						Messages.get(HeroClass.class, "yuyukoplayer_perk5"),
				};
			case MURASAPLAYER:
				return new String[]{
						Messages.get(HeroClass.class, "murasaplayer_perk1"),
						Messages.get(HeroClass.class, "murasaplayer_perk2"),
						Messages.get(HeroClass.class, "murasaplayer_perk3"),
						Messages.get(HeroClass.class, "murasaplayer_perk4"),
						Messages.get(HeroClass.class, "murasaplayer_perk5"),
				};
			case HINAPLAYER:
				return new String[]{
						Messages.get(HeroClass.class, "hinaplayer_perk1"),
						Messages.get(HeroClass.class, "hinaplayer_perk2"),
						Messages.get(HeroClass.class, "hinaplayer_perk3"),
						Messages.get(HeroClass.class, "hinaplayer_perk4"),
						Messages.get(HeroClass.class, "hinaplayer_perk5"),
				};
			case KAGUYAPLAYER:
				return new String[]{
						Messages.get(HeroClass.class, "kaguyaplayer_perk1"),
						Messages.get(HeroClass.class, "kaguyaplayer_perk2"),
						Messages.get(HeroClass.class, "kaguyaplayer_perk3"),
						Messages.get(HeroClass.class, "kaguyaplayer_perk4"),
						Messages.get(HeroClass.class, "kaguyaplayer_perk5"),
				};
			case KOGASAPLAYER:
				return new String[]{
						Messages.get(HeroClass.class, "kogasaplayer_perk1"),
						Messages.get(HeroClass.class, "kogasaplayer_perk2"),
						Messages.get(HeroClass.class, "kogasaplayer_perk3"),
						Messages.get(HeroClass.class, "kogasaplayer_perk4"),
						Messages.get(HeroClass.class, "kogasaplayer_perk5"),
				};
			case YUKARIPLAYER:
				return new String[]{
						Messages.get(HeroClass.class, "yukariplayer_perk1"),
						Messages.get(HeroClass.class, "yukariplayer_perk2"),
						Messages.get(HeroClass.class, "yukariplayer_perk3"),
						Messages.get(HeroClass.class, "yukariplayer_perk4"),
						Messages.get(HeroClass.class, "yukariplayer_perk5"),
				};
			case JUNKOPLAYER:
				return new String[]{
						Messages.get(HeroClass.class, "junkoplayer_perk1"),
						Messages.get(HeroClass.class, "junkoplayer_perk2"),
						Messages.get(HeroClass.class, "junkoplayer_perk3"),
						Messages.get(HeroClass.class, "junkoplayer_perk4"),
						Messages.get(HeroClass.class, "junkoplayer_perk5"),
				};
			case RENKOPLAYER:
				return new String[]{
						Messages.get(HeroClass.class, "renkoplayer_perk1"),
						Messages.get(HeroClass.class, "renkoplayer_perk2"),
						Messages.get(HeroClass.class, "renkoplayer_perk3"),
						Messages.get(HeroClass.class, "renkoplayer_perk4"),
						Messages.get(HeroClass.class, "renkoplayer_perk5"),
				};
			case SEIJAPLAYER:
				return new String[]{
						Messages.get(HeroClass.class, "seijaplayer_perk1"),
						Messages.get(HeroClass.class, "seijaplayer_perk2"),
						Messages.get(HeroClass.class, "seijaplayer_perk3"),
						Messages.get(HeroClass.class, "seijaplayer_perk4"),
						Messages.get(HeroClass.class, "seijaplayer_perk5"),
				};
			case TENKYUUPLAYER:
				return new String[]{
						Messages.get(HeroClass.class, "tenkyuuplayer_perk1"),
						Messages.get(HeroClass.class, "tenkyuuplayer_perk2"),
						Messages.get(HeroClass.class, "tenkyuuplayer_perk3"),
						Messages.get(HeroClass.class, "tenkyuuplayer_perk4"),
						Messages.get(HeroClass.class, "tenkyuuplayer_perk5"),
				};
		}
	}

	public boolean isUnlocked(){
		//always unlock on debug builds
		if (DeviceCompat.isDebug()) return true;

		switch (this){
			case WARRIOR:
			default:
				return true;
			case MAGE:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_MAGE);
			case ROGUE:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_ROGUE);
			case HUNTRESS:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_HUNTRESS);
			case REISENPLAYER:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_REISENPLAYER);
			case NITORIPLAYER:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_NITORIPLAYER);
			case YUYUKOPLAYER:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_YUYUKOPLAYER);
			case MURASAPLAYER:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_MURASAPLAYER);
			case HINAPLAYER:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_HINAPLAYER);
			case KAGUYAPLAYER:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_KAGUYAPLAYER);
			case KOGASAPLAYER:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_KOGASAPLAYER);
			case YUKARIPLAYER:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_YUKARIPLAYER);
			case JUNKOPLAYER:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_JUNKOPLAYER);
			case RENKOPLAYER:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_RENKOPLAYER);
			case SEIJAPLAYER:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_SEIJAPLAYER);
			case TENKYUUPLAYER:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_TENKYUUPLAYER);
		}
	}

	public String unlockMsg() {
		switch (this){
			case WARRIOR:
			default:
				return "";
			case MAGE:
				return Messages.get(HeroClass.class, "mage_unlock");
			case ROGUE:
				return Messages.get(HeroClass.class, "rogue_unlock");
			case HUNTRESS:
				return Messages.get(HeroClass.class, "huntress_unlock");
			case REISENPLAYER:
				return Messages.get(HeroClass.class, "reisenplayer_unlock");
			case NITORIPLAYER:
				return Messages.get(HeroClass.class, "nitoriplayer_unlock");
			case YUYUKOPLAYER:
				return Messages.get(HeroClass.class, "yuyukoplayer_unlock");
			case MURASAPLAYER:
				return Messages.get(HeroClass.class, "murasaplayer_unlock");
			case HINAPLAYER:
				return Messages.get(HeroClass.class, "hinaplayer_unlock");
			case KAGUYAPLAYER:
				return Messages.get(HeroClass.class, "kaguyaplayer_unlock");
			case KOGASAPLAYER:
				return Messages.get(HeroClass.class, "kogasaplayer_unlock");
			case YUKARIPLAYER:
				return Messages.get(HeroClass.class, "yukariplayer_unlock");
			case JUNKOPLAYER:
				return Messages.get(HeroClass.class, "junkoplayer_unlock");
			case RENKOPLAYER:
				return Messages.get(HeroClass.class, "renkoplayer_unlock");
			case SEIJAPLAYER:
				return Messages.get(HeroClass.class, "seijaplayer_unlock");
			case TENKYUUPLAYER:
				return Messages.get(HeroClass.class, "tenkyuuplayer_unlock");
		}
	}
}