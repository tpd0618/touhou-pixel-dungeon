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

package com.touhoupixel.touhoupixeldungeon.items.spells;

import com.touhoupixel.touhoupixeldungeon.Dungeon;
import com.touhoupixel.touhoupixeldungeon.actors.Actor;
import com.touhoupixel.touhoupixeldungeon.actors.Char;
import com.touhoupixel.touhoupixeldungeon.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeon.actors.mobs.Mob;
import com.touhoupixel.touhoupixeldungeon.items.armor.MaxwellArmor;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.touhoupixel.touhoupixeldungeon.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeon.messages.Messages;
import com.touhoupixel.touhoupixeldungeon.sprites.ItemSpriteSheet;
import com.touhoupixel.touhoupixeldungeon.utils.GLog;

public class PhaseShift extends TargetedSpell {
	
	{
		image = ItemSpriteSheet.PHASE_SHIFT;
	}
	
	@Override
	protected void affectTarget(Ballistica bolt, Hero hero) {
		final Char ch = Actor.findChar(bolt.collisionPos);

		if (Dungeon.hero.belongings.armor() instanceof MaxwellArmor) {
			GLog.w(Messages.get(ScrollOfTeleportation.class, "tele_interrupt"));
		} else if (ch == hero){
			//TODO probably want this to not work on the hero for balance reasons?
			ScrollOfTeleportation.teleportChar(curUser);
		} else if (ch != null) {
			if (ScrollOfTeleportation.teleportChar(ch)){

				if (ch instanceof Mob && ((Mob) ch).state == ((Mob) ch).HUNTING){
					((Mob) ch).state = ((Mob) ch).WANDERING;
				}
				
			}
		}
	}
	
	@Override
	public int value() {
		//prices of ingredients, divided by output quantity
		return Math.round(quantity * ((30 + 40) / 8f));
	}
	
	public static class Recipe extends com.touhoupixel.touhoupixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{ScrollOfTeleportation.class, ArcaneCatalyst.class};
			inQuantity = new int[]{1, 1};
			
			cost = 6;
			
			output = PhaseShift.class;
			outQuantity = 8;
		}
		
	}
	
}
