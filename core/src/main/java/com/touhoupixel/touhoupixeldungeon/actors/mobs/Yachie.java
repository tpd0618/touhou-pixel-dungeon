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

package com.touhoupixel.touhoupixeldungeon.actors.mobs;

import com.touhoupixel.touhoupixeldungeon.Assets;
import com.touhoupixel.touhoupixeldungeon.Dungeon;
import com.touhoupixel.touhoupixeldungeon.actors.Char;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Incompetence;
import com.touhoupixel.touhoupixeldungeon.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeon.items.Item;
import com.touhoupixel.touhoupixeldungeon.messages.Messages;
import com.touhoupixel.touhoupixeldungeon.sprites.YachieSprite;
import com.touhoupixel.touhoupixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Yachie extends Mob {

	public Item item;

	{
		spriteClass = YachieSprite.class;

		if (Dungeon.depth > 50){
			HP = HT = 375;
		} else HP = HT = 14;

		if (Dungeon.depth > 50){
			defenseSkill = 57;
		} else defenseSkill = 7;

		if (Dungeon.depth > 50){
			EXP = 55;
		} else EXP = 5;

		if (Dungeon.depth > 50){
			maxLvl = 63;
		} else maxLvl = 13;

		properties.add(Property.YOKAI);
		properties.add(Property.POWERFUL);

		WANDERING = new Wandering();
		FLEEING = new Fleeing();
	}

	private static final String ITEM = "item";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(ITEM, item);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		item = (Item) bundle.get(ITEM);
	}

	@Override
	public int damageRoll() {
		if (Dungeon.depth > 50) {
			return Random.NormalIntRange(37, 44);
		} else return Random.NormalIntRange(2, 7);
	}

	@Override
	public int attackSkill(Char target) {
		if (Dungeon.depth > 50) {
			return 58;
		} else return 8;
	}

	@Override
	protected Item createLoot() {
		Dungeon.LimitedDrops.THEIF_MISC.count++;
		return super.createLoot();
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 3);
	}

	@Override
	public int attackProc(Char enemy, int damage) {
		damage = super.attackProc(enemy, damage);
		if (this.buff(Incompetence.class) == null) {
			if (alignment == Alignment.ENEMY && item == null
					&& enemy instanceof Hero && steal((Hero) enemy)) {
				state = WANDERING;
			}
		}
		return damage;
	}

	protected boolean steal(Hero hero) {

		Item toSteal = hero.belongings.randomUnequipped();

		if (toSteal != null && !toSteal.unique && toSteal.level() < 1) {

			GLog.w(Messages.get(Yachie.class, "stole", toSteal.name()));
			if (!toSteal.stackable) {
				Dungeon.quickslot.convertToPlaceholder(toSteal);
				Sample.INSTANCE.play( Assets.Sounds.CURSED );
			}
			Item.updateQuickslot();

			item = toSteal.detach(hero.belongings.backpack);
			return true;
		} else {
			return false;
		}
	}
}
