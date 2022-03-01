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

package com.touhoupixel.touhoupixeldungeon.items.weapon.melee;

import com.touhoupixel.touhoupixeldungeon.Assets;
import com.touhoupixel.touhoupixeldungeon.Challenges;
import com.touhoupixel.touhoupixeldungeon.Dungeon;
import com.touhoupixel.touhoupixeldungeon.Statistics;
import com.touhoupixel.touhoupixeldungeon.actors.Char;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Doubleevasion;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Silence;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Vertigo;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeon.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeon.sprites.ItemSpriteSheet;

import java.util.ArrayList;

public class ToramaruSpear extends MeleeWeapon {

	{
		image = ItemSpriteSheet.TORAMARU_SPEAR;
		hitSound = Assets.Sounds.HIT;
		hitSoundPitch = 1f;

		tier = 5;
	}

	@Override
	public int coolResistFactor( Char owner ) {
		return 2;
	}

	@Override
	public int max(int lvl) {
		return  Math.round((Statistics.itemsCrafted)*4f+(Statistics.toyohimesKilled)*2f+(Statistics.yorihimesKilled)*2f+(Statistics.limitBreak)*1f+(Statistics.thrownAssists)*0.1f-(Statistics.ankhsUsed)*2f-(Statistics.foodEaten)*0.1f-(Statistics.piranhasKilled)*1f+3f*(tier+1)) +
				lvl*Math.round(1.33f*(tier+1)); //+4 per level, up from +3
	}

	@Override
	public ArrayList<String> actions(Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.remove(AC_XYZ);
		return actions;
	}

	@Override
	public int damageRoll(Char owner) {
		if (owner instanceof Hero) {
			Hero hero = (Hero) owner;
			Char enemy = hero.enemy();
			if (Dungeon.isChallenged(Challenges.ANTI_FUMO) && Dungeon.hero.belongings.weapon() instanceof ToramaruSpear) {
				Buff.prolong(owner, Weakness.class, Weakness.DURATION);
				Buff.prolong(owner, Vulnerable.class, Vulnerable.DURATION);
				Buff.prolong(owner, Hex.class, Hex.DURATION);
				Buff.prolong(owner, Cripple.class, Cripple.DURATION);
			}
		}
		return super.damageRoll(owner);
	}
}