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
import com.touhoupixel.touhoupixeldungeon.actors.Actor;
import com.touhoupixel.touhoupixeldungeon.actors.Char;
import com.touhoupixel.touhoupixeldungeon.actors.buffs.Light;
import com.touhoupixel.touhoupixeldungeon.actors.hero.Hero;
import com.touhoupixel.touhoupixeldungeon.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeon.effects.particles.PurpleParticle;
import com.touhoupixel.touhoupixeldungeon.effects.particles.ShadowParticle;
import com.touhoupixel.touhoupixeldungeon.items.Dewdrop;
import com.touhoupixel.touhoupixeldungeon.items.Generator;
import com.touhoupixel.touhoupixeldungeon.items.Item;
import com.touhoupixel.touhoupixeldungeon.items.potions.Potion;
import com.touhoupixel.touhoupixeldungeon.items.rings.Ring;
import com.touhoupixel.touhoupixeldungeon.items.scrolls.Scroll;
import com.touhoupixel.touhoupixeldungeon.items.wands.WandOfDisintegration;
import com.touhoupixel.touhoupixeldungeon.items.weapon.melee.KeineBook;
import com.touhoupixel.touhoupixeldungeon.mechanics.Ballistica;
import com.touhoupixel.touhoupixeldungeon.messages.Messages;
import com.touhoupixel.touhoupixeldungeon.scenes.GameScene;
import com.touhoupixel.touhoupixeldungeon.sprites.CharSprite;
import com.touhoupixel.touhoupixeldungeon.sprites.ShouSprite;
import com.touhoupixel.touhoupixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Shou extends Mob {
	
	{
		spriteClass = ShouSprite.class;

		if (Dungeon.depth > 50){
			HP = HT = 879;
		} else HP = HT = 224;

		if (Dungeon.depth > 50){
			defenseSkill = 97;
		} else defenseSkill = 47;

		if (Dungeon.depth > 50){
			EXP = 73;
		} else EXP = 23;

		if (Dungeon.depth > 50){
			maxLvl = 99;
		} else maxLvl = 50;

		viewDistance = Light.DISTANCE;

		HUNTING = new Hunting();
		
		loot = new Dewdrop();
		lootChance = 0.5f;

		properties.add(Property.YOKAI);
		properties.add(Property.HAPPY);
	}

	@Override
	public int damageRoll() {
		if (Dungeon.depth > 50) {
			return Random.NormalIntRange(64, 70);
		} else return Random.NormalIntRange(35, 41);
	}

	@Override
	public int attackSkill(Char target) {
		if (Dungeon.depth > 50) {
			return 99;
		} else return 50;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 10);
	}
	
	private Ballistica beam;
	private int beamTarget = -1;
	private int beamCooldown;
	public boolean beamCharged;

	@Override
	protected boolean canAttack( Char enemy ) {

		if (beamCooldown == 0) {
			Ballistica aim = new Ballistica(pos, enemy.pos, Ballistica.STOP_SOLID);

			if (enemy.invisible == 0 && !isCharmedBy(enemy) && fieldOfView[enemy.pos] && aim.subPath(1, aim.dist).contains(enemy.pos)){
				beam = aim;
				beamTarget = aim.collisionPos;
				return true;
			} else
				//if the beam is charged, it has to attack, will aim at previous location of target.
				return beamCharged;
		} else
			return super.canAttack(enemy);
	}

	@Override
	protected boolean act() {
		if (beamCharged && state != HUNTING){
			beamCharged = false;
			sprite.idle();
		}
		if (beam == null && beamTarget != -1) {
			beam = new Ballistica(pos, beamTarget, Ballistica.STOP_SOLID);
			sprite.turnTo(pos, beamTarget);
		}
		if (beamCooldown > 0)
			beamCooldown--;
		return super.act();
	}

	@Override
	protected boolean doAttack( Char enemy ) {

		if (beamCooldown > 0) {
			return super.doAttack(enemy);
		} else if (!beamCharged){
			((ShouSprite)sprite).charge( enemy.pos );
			spend( attackDelay()*2f );
			beamCharged = true;
			return true;
		} else {

			spend( attackDelay() );
			
			beam = new Ballistica(pos, beamTarget, Ballistica.STOP_SOLID);
			if (Dungeon.level.heroFOV[pos] || Dungeon.level.heroFOV[beam.collisionPos] ) {
				sprite.zap( beam.collisionPos );
				return false;
			} else {
				sprite.idle();
				deathGaze();
				return true;
			}
		}

	}

	@Override
	public void damage(int dmg, Object src) {
		if (beamCharged) dmg /= 4;
		super.damage(dmg, src);
	}
	
	//used so resistances can differentiate between melee and magical attacks
	public static class DeathGaze{}

	public void deathGaze(){
		if (!beamCharged || beamCooldown > 0 || beam == null)
			return;

		beamCharged = false;
		beamCooldown = Random.IntRange(4, 6);

		boolean terrainAffected = false;

		for (int pos : beam.subPath(1, beam.dist)) {

			if (Dungeon.level.flamable[pos]) {

				Dungeon.level.destroy( pos );
				GameScene.updateMap( pos );
				terrainAffected = true;

			}

			Char ch = Actor.findChar( pos );
			if (ch == null) {
				continue;
			}

			if (hit( this, ch, true )) {
				ch.damage( Random.NormalIntRange( 30, 50 ), new DeathGaze() );

				if (ch instanceof Hero) {
					Sample.INSTANCE.play(Assets.Sounds.CURSED);
					CellEmitter.get(pos).burst(ShadowParticle.UP, 5);
					if (!(Dungeon.hero.belongings.weapon() instanceof KeineBook)) {
						Potion.initColors();
						Scroll.initLabels();
						Ring.initGems();
						Item.updateQuickslot();
					}
					GLog.w(Messages.get(this, "uniden"));
				}

				if (Dungeon.level.heroFOV[pos]) {
					ch.sprite.flash();
					CellEmitter.center( pos ).burst( PurpleParticle.BURST, Random.IntRange( 1, 2 ) );
				}

				if (!ch.isAlive() && ch == Dungeon.hero) {
					Dungeon.fail( getClass() );
					GLog.n( Messages.get(this, "deathgaze_kill") );
				}
			} else {
				ch.sprite.showStatus( CharSprite.NEUTRAL,  ch.defenseVerb() );
			}
		}

		if (terrainAffected) {
			Dungeon.observe();
		}

		beam = null;
		beamTarget = -1;
	}

	//generates an average of 1 dew, 0.25 seeds, and 0.25 stones
	@Override
	protected Item createLoot() {
		Item loot;
		switch(Random.Int(4)){
			case 0: case 1: default:
				loot = new Dewdrop();
				int ofs;
				do {
					ofs = PathFinder.NEIGHBOURS8[Random.Int(8)];
				} while (Dungeon.level.solid[pos + ofs] && !Dungeon.level.passable[pos + ofs]);
				if (Dungeon.level.heaps.get(pos+ofs) == null) {
					Dungeon.level.drop(new Dewdrop(), pos + ofs).sprite.drop(pos);
				} else {
					Dungeon.level.drop(new Dewdrop(), pos + ofs).sprite.drop(pos + ofs);
				}
				break;
			case 2:
				loot = Generator.random(Generator.Category.SEED);
				break;
			case 3:
				loot = Generator.random(Generator.Category.STONE);
				break;
		}
		return loot;
	}

	private static final String BEAM_TARGET     = "beamTarget";
	private static final String BEAM_COOLDOWN   = "beamCooldown";
	private static final String BEAM_CHARGED    = "beamCharged";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put( BEAM_TARGET, beamTarget);
		bundle.put( BEAM_COOLDOWN, beamCooldown );
		bundle.put( BEAM_CHARGED, beamCharged );
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		if (bundle.contains(BEAM_TARGET))
			beamTarget = bundle.getInt(BEAM_TARGET);
		beamCooldown = bundle.getInt(BEAM_COOLDOWN);
		beamCharged = bundle.getBoolean(BEAM_CHARGED);
	}

	{
		resistances.add( WandOfDisintegration.class );
	}
	
	{
		//immunities.add( Terror.class );
	}

	private class Hunting extends Mob.Hunting{
		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			//even if enemy isn't seen, attack them if the beam is charged
			if (beamCharged && enemy != null && canAttack(enemy)) {
				enemySeen = enemyInFOV;
				return doAttack(enemy);
			}
			return super.act(enemyInFOV, justAlerted);
		}
	}
}
