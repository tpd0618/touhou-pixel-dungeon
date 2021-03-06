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

package com.touhoupixel.touhoupixeldungeon.windows;

import com.touhoupixel.touhoupixeldungeon.Challenges;
import com.touhoupixel.touhoupixeldungeon.TPDSettings;
import com.touhoupixel.touhoupixeldungeon.TouhouPixelDungeon;
import com.touhoupixel.touhoupixeldungeon.messages.Messages;
import com.touhoupixel.touhoupixeldungeon.scenes.PixelScene;
import com.touhoupixel.touhoupixeldungeon.ui.CheckBox;
import com.touhoupixel.touhoupixeldungeon.ui.IconButton;
import com.touhoupixel.touhoupixeldungeon.ui.Icons;
import com.touhoupixel.touhoupixeldungeon.ui.RenderedTextBlock;
import com.touhoupixel.touhoupixeldungeon.ui.Window;

import java.util.ArrayList;

public class WndChallenges extends Window {

	private static final int WIDTH		= 120;
	private static final int TTL_HEIGHT = 0;
	private static final int BTN_HEIGHT = 12;
	private static final int GAP        = -1;

	private boolean editable;
	private ArrayList<CheckBox> boxes;

	public WndChallenges( int checked, boolean editable ) {

		super();

		this.editable = editable;

		boxes = new ArrayList<>();

		float pos = TTL_HEIGHT;
		for (int i=0; i < Challenges.NAME_IDS.length; i++) {

			final String challenge = Challenges.NAME_IDS[i];
			
			CheckBox cb = new CheckBox( Messages.titleCase(Messages.get(Challenges.class, challenge)) );
			cb.checked( (checked & Challenges.MASKS[i]) != 0 );
			cb.active = editable;

			if (i > 0) {
				pos += GAP;
			}
			cb.setRect( 0, pos, WIDTH-16, BTN_HEIGHT );

			add( cb );
			boxes.add( cb );
			
			IconButton info = new IconButton(Icons.get(Icons.INFO)){
				@Override
				protected void onClick() {
					super.onClick();
					TouhouPixelDungeon.scene().add(
							new WndMessage(Messages.get(Challenges.class, challenge+"_desc"))
					);
				}
			};
			info.setRect(cb.right(), pos, 16, BTN_HEIGHT);
			add(info);
			
			pos = cb.bottom();
		}

		resize( WIDTH, (int)pos );
	}

	@Override
	public void onBackPressed() {

		if (editable) {
			int value = 0;
			for (int i=0; i < boxes.size(); i++) {
				if (boxes.get( i ).checked()) {
					value |= Challenges.MASKS[i];
				}
			}
			TPDSettings.challenges( value );
		}

		super.onBackPressed();
	}
}