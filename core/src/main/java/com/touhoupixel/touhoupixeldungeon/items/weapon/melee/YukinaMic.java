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
import com.touhoupixel.touhoupixeldungeon.actors.Char;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.AnkhInvulnerability;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Bless;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Buff;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Cripple;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.OneDefDamage;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeon.actors.hero.Belongings;
import com.touhoupixel.touhoupixeldungeon.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeon.items.Item;
import com.touhoupixel.touhoupixeldungeon.items.Stylus;
import com.touhoupixel.touhoupixeldungeon.items.bags.Bag;
import com.touhoupixel.touhoupixeldungeon.messages.Messages;
import com.touhoupixel.touhoupixeldungeon.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeon.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeon.utils.GLog;
import com.touhoupixel.touhoupixeldungeon.windows.WndBag;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class YukinaMic extends MeleeWeapon {

	{
		image = ItemSpriteSheet.YUKINA_MIC;
		hitSound = Assets.Sounds.HIT;
		hitSoundPitch = 1f;

		tier = 5;
	}

	@Override
	public int max(int lvl) {
		return  4*(tier+1) +
				lvl*(tier);
	}

	@Override
	public int happyResistFactor( Char owner ) {
		return 3;
	}

	@Override
	public int damageRoll(Char owner) {
		if (owner instanceof Hero) {
			Hero hero = (Hero) owner;
			Char enemy = hero.enemy();
			if (Dungeon.hero.belongings.weapon() instanceof YukinaMic && (Random.Int(20) == 0)) {
				Buff.prolong(owner, Bless.class, Bless.DURATION);
				Buff.prolong(owner, OneDefDamage.class, OneDefDamage.DURATION);
			}
		}
		return super.damageRoll(owner);
	}

	@Override
	public void execute(final Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals(AC_XYZ) && curItem.isEquipped(hero)){
			GLog.w( Messages.get(Stylus.class, "xyzbeforeunequip") );
		} else if (action.equals(AC_XYZ) && curItem.level() == 7) {
			GameScene.selectItem(itemSelector);
		}
	}

	private final WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(Stylus.class, "promptxyz");
		}

		@Override
		public Class<? extends Bag> preferredBag() {
			return Belongings.Backpack.class;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof EnmaShaku;
		}

		@Override
		public void onSelect(Item item) {
			if (item != null && item.isEquipped(curUser)){
				GLog.w( Messages.get(Stylus.class, "xyzbeforeunequip") );
			} else if (item != null && item.level() == 7){
				curItem.detach(curUser.belongings.backpack);
				item.detach(curUser.belongings.backpack);
				HellMic hm = new HellMic();
				hm.identify().collect();
			}
		}
	};
}