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
import com.touhoupixel.touhoupixeldungeon.items.potions.exotic.PotionOfTriplespeed;
import com.touhoupixel.touhoupixeldungeon.sprites.AyaSprite;
import com.watabou.utils.Random;

public class Aya extends Mob {

	{
		spriteClass = AyaSprite.class;

		if (Dungeon.depth > 50){
			HP = HT = 412;
		} else HP = HT = 30;

		if (Dungeon.depth > 50){
			defenseSkill = 65;
		} else defenseSkill = 15;

		if (Dungeon.depth > 50){
			EXP = 56;
		} else EXP = 6;

		if (Dungeon.depth > 50){
			maxLvl = 66;
		} else maxLvl = 16;

		baseSpeed = 5f;

		flying = true;
		
		loot = new PotionOfTriplespeed();
		lootChance = 0.01f;

		properties.add(Property.FLOAT);
		properties.add(Property.POWERFUL);
	}

	@Override
	public int damageRoll() {
		if (Dungeon.depth > 50) {
			return Random.NormalIntRange(32, 37);
		} else return Random.NormalIntRange(12, 14);
	}

	@Override
	public int attackSkill(Char target) {
		if (Dungeon.depth > 50) {
			return 65;
		} else return 15;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 4);
	}
}
