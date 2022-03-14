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

import com.touhoupixel.touhoupixeldungeon.Dungeon;
import com.touhoupixel.touhoupixeldungeon.actors.Char;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Incompetence;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.MoveDetect;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Silence;
import com.touhoupixel.touhoupixeldungeon.items.Gold;
import com.touhoupixel.touhoupixeldungeon.items.potions.exotic.PotionOfStamina;
import com.touhoupixel.touhoupixeldungeon.sprites.MomoyoSprite;
import com.touhoupixel.touhoupixeldungeon.sprites.StarSprite;
import com.watabou.utils.Random;

public class Star extends Mob {
	
	{
		spriteClass = StarSprite.class;

		if (Dungeon.depth > 50){
			HP = HT = 612;
		} else HP = HT = 119;

		if (Dungeon.depth > 50){
			defenseSkill = 85;
		} else defenseSkill = 35;

		if (Dungeon.depth > 50){
			EXP = 68;
		} else EXP = 18;

		if (Dungeon.depth > 50){
			maxLvl = 85;
		} else maxLvl = 35;
		
		loot = PotionOfStamina.class;
		lootChance = 0.1f;

		properties.add(Property.HAPPY);
	}

	@Override
	public int damageRoll() {
		if (Dungeon.depth > 50) {
			return Random.NormalIntRange(58, 60);
		} else return Random.NormalIntRange(39, 41);
	}

	@Override
	public int attackSkill(Char target) {
		if (Dungeon.depth > 50) {
			return 95;
		} else return 35;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 2);
	}

	@Override
	public int attackProc( Char hero, int damage ) {
		damage = super.attackProc( enemy, damage );
		if (this.buff(Incompetence.class) == null) {
			if (Random.Int(2) == 0) {
				Buff.prolong(enemy, MoveDetect.class, MoveDetect.DURATION);
			}
		}

		return damage;
	}
}
