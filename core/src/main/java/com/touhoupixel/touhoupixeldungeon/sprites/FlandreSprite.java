package com.touhoupixel.touhoupixeldungeon.sprites;

import com.touhoupixel.touhoupixeldungeon.Assets;
import com.touhoupixel.touhoupixeldungeon.Dungeon;
import com.touhoupixel.touhoupixeldungeon.actors.Char;
import com.touhoupixel.touhoupixeldungeon.actors.mobs.Remilia;
import com.touhoupixel.touhoupixeldungeon.effects.CellEmitter;
import com.touhoupixel.touhoupixeldungeon.effects.particles.ShadowParticle;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;

public class FlandreSprite extends MobSprite {

	private Animation charging;
	private Emitter summoningBones;

	//TODO sprite is still a bit of a WIP
	public FlandreSprite(){
		super();

		texture( Assets.Sprites.FLANDRE );
		TextureFilm film = new TextureFilm( texture, 16, 16 );

		int c = 16;

		idle = new Animation( 1, true );
		idle.frames( film, c+0, c+0, c+0, c+1, c+0, c+0, c+0, c+0, c+1 );

		run = new Animation( 8, true );
		run.frames( film, c+0, c+0, c+0, c+2, c+3, c+4 );

		zap = new Animation( 10, false );
		zap.frames( film, c+5, c+6, c+7, c+8 );

		charging = new Animation( 5, true );
		charging.frames( film, c+7, c+8 );

		die = new Animation( 10, false );
		die.frames( film, c+9, c+10, c+11, c+12 );

		attack = zap.clone();

		idle();
	}

	@Override
	public void link(Char ch) {
		super.link(ch);
		if (ch instanceof Remilia && ((Remilia) ch).summoning){
			zap(((Remilia) ch).summoningPos);
		}
	}

	@Override
	public void update() {
		super.update();
		if (summoningBones != null && ((Remilia) ch).summoningPos != -1){
			summoningBones.visible = Dungeon.level.heroFOV[((Remilia) ch).summoningPos];
		}
	}

	@Override
	public void die() {
		super.die();
		if (summoningBones != null){
			summoningBones.on = false;
		}
	}

	@Override
	public void kill() {
		super.kill();
		if (summoningBones != null){
			summoningBones.killAndErase();
		}
	}

	public void cancelSummoning(){
		if (summoningBones != null){
			summoningBones.on = false;
		}
	}

	public void finishSummoning(){
		if (summoningBones.visible) {
			Sample.INSTANCE.play(Assets.Sounds.CURSED);
			summoningBones.burst(ShadowParticle.CURSE, 5);
		} else {
			summoningBones.on = false;
		}
		idle();
	}

	public void charge(){
		play(charging);
	}

	@Override
	public void zap(int cell) {
		super.zap(cell);
		if (ch instanceof Remilia && ((Remilia) ch).summoning){
			if (summoningBones != null){
				summoningBones.on = false;
			}
			summoningBones = CellEmitter.get(((Remilia) ch).summoningPos);
			summoningBones.pour(ShadowParticle.MISSILE, 0.1f);
			summoningBones.visible = Dungeon.level.heroFOV[((Remilia) ch).summoningPos];
			if (visible || summoningBones.visible ) Sample.INSTANCE.play( Assets.Sounds.CHARGEUP, 1f, 0.8f );
		}
	}

	@Override
	public void onComplete(Animation anim) {
		super.onComplete(anim);
		if (anim == zap){
			if (ch instanceof Remilia){
				if (((Remilia) ch).summoning){
					charge();
				} else {
					((Remilia)ch).onZapComplete();
					idle();
				}
			} else {
				idle();
			}
		}
	}

}
