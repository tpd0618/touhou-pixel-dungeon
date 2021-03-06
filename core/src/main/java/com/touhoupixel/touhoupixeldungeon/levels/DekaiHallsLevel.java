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

package com.touhoupixel.touhoupixeldungeon.levels;

import com.touhoupixel.touhoupixeldungeon.Assets;
import com.touhoupixel.touhoupixeldungeon.Dungeon;
import com.touhoupixel.touhoupixeldungeon.items.Torch;
import com.touhoupixel.touhoupixeldungeon.levels.painters.HallsPainter;
import com.touhoupixel.touhoupixeldungeon.levels.painters.Painter;
import com.touhoupixel.touhoupixeldungeon.levels.traps.AlarmTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.AntiHealTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.BlazingTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.CorrosionTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.CursingTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.DegradeTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.DespairTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.DisarmingTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.DisintegrationTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.ExConfusionTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.FlashingTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.FrostTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.GapTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.GatewayTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.GeyserTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.GrimTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.PitfallTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.RockfallTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.StormTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.SummoningTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.WarpingTrap;
import com.touhoupixel.touhoupixeldungeon.levels.traps.WeakeningTrap;
import com.touhoupixel.touhoupixeldungeon.messages.Messages;
import com.touhoupixel.touhoupixeldungeon.tiles.DungeonTilemap;
import com.watabou.glwrap.Blending;
import com.watabou.noosa.Game;
import com.watabou.noosa.Group;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class DekaiHallsLevel extends RegularLevel {

	{
		
		color1 = 0x801500;
		color2 = 0xa68521;
	}

	@Override
	protected int standardRooms(boolean forceMax) {
		if (forceMax) return 9;
		//8 to 9, average 8.33
		return 8+Random.chances(new float[]{2, 1});
	}
	
	@Override
	protected int specialRooms(boolean forceMax) {
		if (forceMax) return 3;
		//2 to 3, average 2.5
		return 2 + Random.chances(new float[]{1, 1});
	}
	
	@Override
	protected Painter painter() {
		return new HallsPainter()
				.setWater(feeling == Feeling.WATER ? 0.70f : 0.15f, 6)
				.setGrass(feeling == Feeling.GRASS ? 0.65f : 0.10f, 3)
				.setTraps(nTraps(), trapClasses(), trapChances());
	}
	
	@Override
	public void create() {
		addItemToSpawn( new Torch() );
		addItemToSpawn( new Torch() );
		super.create();
	}
	
	@Override
	public String tilesTex() {
		return Assets.Environment.TILES_HALLS;
	}
	
	@Override
	public String waterTex() {
		return Assets.Environment.WATER_HALLS;
	}

	@Override
	protected Class<?>[] trapClasses() {
		return new Class[]{
				FrostTrap.class, CorrosionTrap.class, BlazingTrap.class, DisintegrationTrap.class, GapTrap.class,
				RockfallTrap.class, FlashingTrap.class, WeakeningTrap.class, AlarmTrap.class, DegradeTrap.class, DespairTrap.class, AntiHealTrap.class, ExConfusionTrap.class,
				DisarmingTrap.class, SummoningTrap.class, WarpingTrap.class, CursingTrap.class, GrimTrap.class, PitfallTrap.class, GatewayTrap.class, GeyserTrap.class };
	}

	@Override
	protected float[] trapChances() {
		return new float[]{
				4, 4, 4, 4, 4,
				2, 2, 2, 2, 2, 2, 2, 2,
				1, 1, 1, 1, 1, 1, 1, 1 };
	}

	@Override
	public String tileName( int tile ) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(DekaiHallsLevel.class, "water_name");
			case Terrain.GRASS:
				return Messages.get(DekaiHallsLevel.class, "grass_name");
			case Terrain.HIGH_GRASS:
				return Messages.get(DekaiHallsLevel.class, "high_grass_name");
			case Terrain.STATUE:
			case Terrain.STATUE_SP:
				return Messages.get(DekaiHallsLevel.class, "statue_name");
			default:
				return super.tileName( tile );
		}
	}
	
	@Override
	public String tileDesc(int tile) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(DekaiHallsLevel.class, "water_desc");
			case Terrain.STATUE:
			case Terrain.STATUE_SP:
				return Messages.get(DekaiHallsLevel.class, "statue_desc");
			case Terrain.BOOKSHELF:
				return Messages.get(DekaiHallsLevel.class, "bookshelf_desc");
			default:
				return super.tileDesc( tile );
		}
	}
	
	@Override
	public Group addVisuals() {
		super.addVisuals();
		addHallsVisuals( this, visuals );
		return visuals;
	}
	
	public static void addHallsVisuals( Level level, Group group ) {
		for (int i=0; i < level.length(); i++) {
			if (level.map[i] == Terrain.WATER) {
				group.add( new Stream( i ) );
			}
		}
	}
	
	private static class Stream extends Group {
		
		private int pos;
		
		private float delay;
		
		public Stream( int pos ) {
			super();
			
			this.pos = pos;
			
			delay = Random.Float( 2 );
		}
		
		@Override
		public void update() {

			if (!Dungeon.level.water[pos]){
				killAndErase();
				return;
			}
			
			if (visible = (pos < Dungeon.level.heroFOV.length && Dungeon.level.heroFOV[pos])) {
				
				super.update();
				
				if ((delay -= Game.elapsed) <= 0) {
					
					delay = Random.Float( 2 );
					
					PointF p = DungeonTilemap.tileToWorld( pos );
					((FireParticle)recycle( FireParticle.class )).reset(
						p.x + Random.Float( DungeonTilemap.SIZE ),
						p.y + Random.Float( DungeonTilemap.SIZE ) );
				}
			}
		}
		
		@Override
		public void draw() {
			Blending.setLightMode();
			super.draw();
			Blending.setNormalMode();
		}
	}
	
	public static class FireParticle extends PixelParticle.Shrinking {
		
		public FireParticle() {
			super();
			
			color( 0xEE7722 );
			lifespan = 1f;
			
			acc.set( 0, +80 );
		}
		
		public void reset( float x, float y ) {
			revive();
			
			this.x = x;
			this.y = y;
			
			left = lifespan;
			
			speed.set( 0, -40 );
			size = 4;
		}
		
		@Override
		public void update() {
			super.update();
			float p = left / lifespan;
			am = p > 0.8f ? (1 - p) * 5 : 1;
		}
	}
}
