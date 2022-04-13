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

package com.touhoupixel.touhoupixeldungeon.items.scrolls;

import com.touhoupixel.touhoupixeldungeon.Challenges;
import com.touhoupixel.touhoupixeldungeon.Dungeon;
import com.touhoupixel.touhoupixeldungeon.Statistics;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.ArisastarRank1;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.ArisastarRank2;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.ArisastarRank3;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Bless;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Blindness;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.FireBrandBuff;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.FrostBrandBuff;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Incompetence;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Invisibility;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.MagicImmune;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Poison;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.ScrollEmpower;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Silence;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.WandZeroDamage;
import com.touhoupixel.touhoupixeldungeon.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeon.actors.hero.Talent;
import com.touhoupixel.touhoupixeldungeon.items.Generator;
import com.touhoupixel.touhoupixeldungeon.items.Item;
import com.touhoupixel.touhoupixeldungeon.items.ItemStatusHandler;
import com.touhoupixel.touhoupixeldungeon.items.Recipe;
import com.touhoupixel.touhoupixeldungeon.items.artifacts.UnstableSpellbook;
import com.touhoupixel.touhoupixeldungeon.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeon.items.potions.PotionOfStrength;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.exotic.ScrollOfAntiMagic;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.exotic.ScrollOfEnchantment;
import com.touhoupixel.touhoupixeldungeon.items.stones.Runestone;
import com.touhoupixel.touhoupixeldungeon.items.stones.StoneOfFear;
import com.touhoupixel.touhoupixeldungeon.items.stones.StoneOfAggression;
import com.touhoupixel.touhoupixeldungeon.items.stones.StoneOfAugmentation;
import com.touhoupixel.touhoupixeldungeon.items.stones.StoneOfBlast;
import com.touhoupixel.touhoupixeldungeon.items.stones.StoneOfBlink;
import com.touhoupixel.touhoupixeldungeon.items.stones.StoneOfClairvoyance;
import com.touhoupixel.touhoupixeldungeon.items.stones.StoneOfDeepSleep;
import com.touhoupixel.touhoupixeldungeon.items.stones.StoneOfDisarming;
import com.touhoupixel.touhoupixeldungeon.items.stones.StoneOfEnchantment;
import com.touhoupixel.touhoupixeldungeon.items.stones.StoneOfFlock;
import com.touhoupixel.touhoupixeldungeon.items.stones.StoneOfIntuition;
import com.touhoupixel.touhoupixeldungeon.items.stones.StoneOfShock;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.FireBrand;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.FrostBrand;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.MurasaDipper;
import com.touhoupixel.touhoupixeldungeon.journal.Catalog;
import com.touhoupixel.touhoupixeldungeon.messages.Messages;
import com.touhoupixel.touhoupixeldungeon.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeon.sprites.HeroSprite;
import com.touhoupixel.touhoupixeldungeon.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.security.Identity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Scroll extends Item {

	public static final String AC_READ	= "READ";

	protected static final float TIME_TO_READ	= 1f;

	private static final HashMap<String, Integer> runes = new HashMap<String, Integer>() {
		{
			put("KAUNAN",ItemSpriteSheet.SCROLL_KAUNAN);
			put("SOWILO",ItemSpriteSheet.SCROLL_SOWILO);
			put("LAGUZ",ItemSpriteSheet.SCROLL_LAGUZ);
			put("YNGVI",ItemSpriteSheet.SCROLL_YNGVI);
			put("GYFU",ItemSpriteSheet.SCROLL_GYFU);
			put("RAIDO",ItemSpriteSheet.SCROLL_RAIDO);
			put("ISAZ",ItemSpriteSheet.SCROLL_ISAZ);
			put("MANNAZ",ItemSpriteSheet.SCROLL_MANNAZ);
			put("NAUDIZ",ItemSpriteSheet.SCROLL_NAUDIZ);
			put("BERKANAN",ItemSpriteSheet.SCROLL_BERKANAN);
			put("ODAL",ItemSpriteSheet.SCROLL_ODAL);
			put("TIWAZ",ItemSpriteSheet.SCROLL_TIWAZ);
			put("GENSOKYO",ItemSpriteSheet.SCROLL_GENSOKYO);
			put("ZUN",ItemSpriteSheet.SCROLL_ZUN);
			put("DANMAKU",ItemSpriteSheet.SCROLL_DANMAKU);
			put("FUMO",ItemSpriteSheet.SCROLL_FUMO);
		}
	};

	protected static ItemStatusHandler<Scroll> handler;

	protected String rune;

	{
		stackable = true;
		defaultAction = AC_READ;
	}

	@SuppressWarnings("unchecked")
	public static void initLabels() {
		handler = new ItemStatusHandler<>( (Class<? extends Scroll>[])Generator.Category.SCROLL.classes, runes );
	}

	public static void save( Bundle bundle ) {
		handler.save( bundle );
	}

	public static void saveSelectively( Bundle bundle, ArrayList<Item> items ) {
		ArrayList<Class<?extends Item>> classes = new ArrayList<>();
		for (Item i : items){
			if (i instanceof ExoticScroll){
				if (!classes.contains(ExoticScroll.exoToReg.get(i.getClass()))){
					classes.add(ExoticScroll.exoToReg.get(i.getClass()));
				}
			} else if (i instanceof Scroll){
				if (!classes.contains(i.getClass())){
					classes.add(i.getClass());
				}
			}
		}
		handler.saveClassesSelectively( bundle, classes );
	}

	@SuppressWarnings("unchecked")
	public static void restore( Bundle bundle ) {
		handler = new ItemStatusHandler<>( (Class<? extends Scroll>[])Generator.Category.SCROLL.classes, runes, bundle );
	}

	public Scroll() {
		super();
		reset();
	}

	//anonymous scrolls are always IDed, do not affect ID status,
	//and their sprite is replaced by a placeholder if they are not known,
	//useful for items that appear in UIs, or which are only spawned for their effects
	protected boolean anonymous = false;
	public void anonymize(){
		if (!isKnown()) image = ItemSpriteSheet.SCROLL_HOLDER;
		anonymous = true;
	}


	@Override
	public void reset(){
		super.reset();
		if (handler != null && handler.contains(this)) {
			image = handler.image(this);
			rune = handler.label(this);
		}
	}

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_READ );
		return actions;
	}

	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals( AC_READ )) {

			if (Dungeon.hero.belongings.weapon() instanceof FireBrand && !(curItem instanceof ScrollOfUpgrade) && !(curItem instanceof ScrollOfIdentify) && !(curItem instanceof ScrollOfRemoveCurse) && !(curItem instanceof ScrollOfTransmutation) && !(curItem instanceof ScrollOfEnchantment)){
				curItem = detach(hero.belongings.backpack);
				Buff.prolong(hero, FireBrandBuff.class, FireBrandBuff.DURATION);
				Buff.detach(hero, FrostBrandBuff.class);
			}

			if (hero.buff(MagicImmune.class) != null){
				GLog.w( Messages.get(this, "no_magic") );
			} else if (hero.buff( Blindness.class ) != null) {
				GLog.w( Messages.get(this, "blinded") );
			} else if (hero.buff( Silence.class ) != null) {
				GLog.w(Messages.get(this, "silence"));
			} else if (hero.buff( Incompetence.class ) != null) {
				GLog.w( Messages.get(this, "incompetence") );
			} else if (hero.buff(UnstableSpellbook.bookRecharge.class) != null
					&& hero.buff(UnstableSpellbook.bookRecharge.class).isCursed()
					&& !(this instanceof ScrollOfRemoveCurse || this instanceof ScrollOfAntiMagic)){
				GLog.n( Messages.get(this, "cursed") );
			} else {
				curUser = hero;
				curItem = detach(hero.belongings.backpack);
				Statistics.extraSTRcheck += 1;
				doRead();
				if (Dungeon.isChallenged(Challenges.YUUMA_POWER_DRAIN)) {
					Buff.prolong(curUser, Degrade.class, Degrade.DURATION/2f);
					Buff.prolong(curUser, WandZeroDamage.class, WandZeroDamage.DURATION/2f);
				}

				Buff.detach(hero, ArisastarRank1.class);
				Buff.detach(hero, ArisastarRank2.class);
				Buff.detach(hero, ArisastarRank3.class);

				if (Dungeon.isChallenged(Challenges.YUUMA_POWER_DRAIN) && Statistics.extraSTRcheck > 9) {
					Statistics.extraSTRcheck = 0;
					GameScene.flash(0x80FFFFFF);
					if (Dungeon.hero.STR > 5) {
						hero.STR--;
					}
				}
			}

		}
	}

	public abstract void doRead();

	protected void readAnimation() {
		Invisibility.dispel();
		curUser.spend( TIME_TO_READ );
		curUser.busy();
		((HeroSprite)curUser.sprite).read();

		if (curUser.hasTalent(Talent.EMPOWERING_SCROLLS)){
			Buff.affect(curUser, ScrollEmpower.class);
			updateQuickslot();
		}

	}

	public boolean isKnown() {
		return anonymous || (handler != null && handler.isKnown( this ));
	}

	public void setKnown() {
		if (!anonymous) {
			if (!isKnown()) {
				handler.know(this);
				updateQuickslot();
			}

			if (Dungeon.hero.isAlive()) {
				Catalog.setSeen(getClass());
			}
		}
	}

	@Override
	public Item identify() {
		super.identify();

		if (!isKnown()) {
			setKnown();
		}
		return this;
	}

	@Override
	public String name() {
		return isKnown() ? super.name() : Messages.get(this, rune);
	}

	@Override
	public String info() {
		return isKnown() ?
				desc() :
				Messages.get(this, "unknown_desc");
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return isKnown();
	}

	public static HashSet<Class<? extends Scroll>> getKnown() {
		return handler.known();
	}

	public static HashSet<Class<? extends Scroll>> getUnknown() {
		return handler.unknown();
	}

	public static boolean allKnown() {
		return handler.known().size() == Generator.Category.SCROLL.classes.length;
	}

	@Override
	public int value() {
		return 30 * quantity;
	}

	public static class PlaceHolder extends Scroll {

		{
			image = ItemSpriteSheet.SCROLL_HOLDER;
		}

		@Override
		public boolean isSimilar(Item item) {
			return ExoticScroll.regToExo.containsKey(item.getClass())
					|| ExoticScroll.regToExo.containsValue(item.getClass());
		}

		@Override
		public void doRead() {}

		@Override
		public String info() {
			return "";
		}
	}

	public static class ScrollToStone extends Recipe {

		private static HashMap<Class<?extends Scroll>, Class<?extends Runestone>> stones = new HashMap<>();
		static {
			stones.put(ScrollOfIdentify.class,      StoneOfIntuition.class);
			stones.put(ScrollOfLullaby.class,       StoneOfDeepSleep.class);
			stones.put(ScrollOfMagicMapping.class,  StoneOfClairvoyance.class);
			stones.put(ScrollOfMirrorImage.class,   StoneOfFlock.class);
			stones.put(ScrollOfRetribution.class,   StoneOfBlast.class);
			stones.put(ScrollOfRage.class,          StoneOfAggression.class);
			stones.put(ScrollOfRecharging.class,    StoneOfShock.class);
			stones.put(ScrollOfRemoveCurse.class,   StoneOfDisarming.class);
			stones.put(ScrollOfTeleportation.class, StoneOfBlink.class);
			stones.put(ScrollOfTerror.class,        StoneOfFear.class);
			stones.put(ScrollOfTransmutation.class, StoneOfAugmentation.class);
			stones.put(ScrollOfUpgrade.class,       StoneOfEnchantment.class);
			stones.put(ScrollOfSlowness.class,      StoneOfBlast.class);
		}

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 1
					|| !(ingredients.get(0) instanceof Scroll)
					|| !stones.containsKey(ingredients.get(0).getClass())){
				return false;
			}

			return true;
		}

		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 0;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;

			Scroll s = (Scroll) ingredients.get(0);

			s.quantity(s.quantity() - 1);
			s.identify();

			return Reflection.newInstance(stones.get(s.getClass())).quantity(2);
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;

			Scroll s = (Scroll) ingredients.get(0);

			if (!s.isKnown()){
				return new Runestone.PlaceHolder().quantity(2);
			} else {
				return Reflection.newInstance(stones.get(s.getClass())).quantity(2);
			}
		}
	}
}