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

package com.touhoupixel.touhoupixeldungeon.items.potions;

import com.touhoupixel.touhoupixeldungeon.Assets;
import com.touhoupixel.touhoupixeldungeon.Challenges;
import com.touhoupixel.touhoupixeldungeon.Dungeon;
import com.touhoupixel.touhoupixeldungeon.actors.blobs.Blob;
import com.touhoupixel.touhoupixeldungeon.actors.blobs.ConfusionGas;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Levitation;
import com.touhoupixel.touhoupixeldungeon.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeon.messages.Messages;
import com.touhoupixel.touhoupixeldungeon.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeon.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class PotionOfLevitation extends Potion {

	{
		icon = ItemSpriteSheet.Icons.POTION_LEVITATE;
	}

	@Override
	public void shatter( int cell ) {

		if (Dungeon.level.heroFOV[cell]) {
			identify();

			splash( cell );
			Sample.INSTANCE.play( Assets.Sounds.SHATTER );
			Sample.INSTANCE.play( Assets.Sounds.GAS );
		}

		GameScene.add( Blob.seed( cell, 1000, ConfusionGas.class ) );
	}
	
	@Override
	public void apply( Hero hero ) {
		identify();
		if (Dungeon.isChallenged(Challenges.TENSHI_EARTHQUAKE)) {
			Buff.affect( hero, Levitation.class, Levitation.DURATION*5f );
		} else Buff.affect( hero, Levitation.class, Levitation.DURATION );
		GLog.i( Messages.get(this, "float") );
	}
	
	@Override
	public int value() {
		return isKnown() ? 40 * quantity : super.value();
	}
}
