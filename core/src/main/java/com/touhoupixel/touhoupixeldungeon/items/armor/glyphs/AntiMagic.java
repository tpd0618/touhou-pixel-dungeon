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

package com.touhoupixel.touhoupixeldungeon.items.armor.glyphs;

import com.touhoupixel.touhoupixeldungeon.actors.Char;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Charm;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Degrade;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Hex;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.MagicalSleep;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Vulnerable;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Weakness;
import com.touhoupixel.touhoupixeldungeon.actors.mobs.Tojiko;
import com.touhoupixel.touhoupixeldungeon.actors.mobs.Junko;
import com.touhoupixel.touhoupixeldungeon.actors.mobs.Nitori;
import com.touhoupixel.touhoupixeldungeon.actors.mobs.Ran;
import com.touhoupixel.touhoupixeldungeon.actors.mobs.HecatiaBody;
import com.touhoupixel.touhoupixeldungeon.items.armor.Armor;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfBlastWave;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfDisintegration;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfFireblast;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfFrost;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfLightning;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfLivingEarth;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfMagicMissile;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfPrismaticLight;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfTransfusion;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfWarding;
import com.touhoupixel.touhoupixeldungeon.levels.traps.DisintegrationTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.GrimTrap;
import com.touhoupixel.touhoupixeldungeon.sprites.ItemSprite;
import com.watabou.utils.Random;

import java.util.HashSet;

public class AntiMagic extends Armor.Glyph {

	private static ItemSprite.Glowing TEAL = new ItemSprite.Glowing( 0x88EEFF );
	
	public static final HashSet<Class> RESISTS = new HashSet<>();
	static {
		RESISTS.add( MagicalSleep.class );
		RESISTS.add( Charm.class );
		RESISTS.add( Weakness.class );
		RESISTS.add( Vulnerable.class );
		RESISTS.add( Hex.class );
		RESISTS.add( Degrade.class );
		
		RESISTS.add( DisintegrationTrap.class );
		RESISTS.add( GrimTrap.class );

		RESISTS.add( WandOfBlastWave.class );
		RESISTS.add( WandOfDisintegration.class );
		RESISTS.add( WandOfFireblast.class );
		RESISTS.add( WandOfFrost.class );
		RESISTS.add( WandOfLightning.class );
		RESISTS.add( WandOfLivingEarth.class );
		RESISTS.add( WandOfMagicMissile.class );
		RESISTS.add( WandOfPrismaticLight.class );
		RESISTS.add( WandOfTransfusion.class );
		RESISTS.add( WandOfWarding.Ward.class );
		
		RESISTS.add( Tojiko.LightningBolt.class );
		RESISTS.add( Nitori.EarthenBolt.class );
		RESISTS.add( Ran.DarkBolt.class );
		RESISTS.add( Junko.DeathGaze.class );
		RESISTS.add( HecatiaBody.BrightFist.LightBeam.class );
		RESISTS.add( HecatiaBody.DarkFist.DarkBolt.class );
	}
	
	@Override
	public int proc(Armor armor, Char attacker, Char defender, int damage) {
		//no proc effect, see Hero.damage and GhostHero.damage and ArmoredStatue.damage
		return damage;
	}
	
	public static int drRoll( int level ){
		return Random.NormalIntRange(level, 3 + Math.round(level*1.5f));
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return TEAL;
	}

}