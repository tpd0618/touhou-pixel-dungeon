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

package com.touhoupixel.touhoupixeldungeon;

import com.touhoupixel.touhoupixeldungeon.items.Item;
import com.touhoupixel.touhoupixeldungeon.items.artifacts.Artifact;
import com.touhoupixel.touhoupixeldungeon.journal.Catalog;
import com.touhoupixel.touhoupixeldungeon.messages.Messages;
import com.touhoupixel.touhoupixeldungeon.scenes.PixelScene;
import com.touhoupixel.touhoupixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Badges {

	public enum Badge {
		//bronze
		UNLOCK_MAGE                 ( 1 ),
		UNLOCK_ROGUE                ( 2 ),
		UNLOCK_HUNTRESS             ( 3 ),
		MONSTERS_SLAIN_1            ( 4 ),
		MONSTERS_SLAIN_2            ( 5 ),
		GOLD_COLLECTED_1            ( 6 ),
		GOLD_COLLECTED_2            ( 7 ),
		ITEM_LEVEL_1                ( 8 ),
		LEVEL_REACHED_1             ( 9 ),
		STRENGTH_ATTAINED_1         ( 10 ),
		FOOD_EATEN_1                ( 11 ),
		ITEMS_CRAFTED_1             ( 12 ),
		BOSS_SLAIN_1                ( 13 ),
		UNLOCK_REISENPLAYER         ( 24 ),
		UNLOCK_NITORIPLAYER         ( 25 ),
		UNLOCK_YUYUKOPLAYER         ( 26 ),
		UNLOCK_MURASAPLAYER         ( 27 ),
		UNLOCK_HINAPLAYER           ( 19 ),
		UNLOCK_KAGUYAPLAYER         ( 20 ),
		UNLOCK_KOGASAPLAYER         ( 21 ),
		UNLOCK_YUKARIPLAYER         ( 22 ),
		UNLOCK_JUNKOPLAYER          ( 23 ),
		UNLOCK_RENKOPLAYER          ( 56 ),
		UNLOCK_SEIJAPLAYER          ( 57 ),
		UNLOCK_TENKYUUPLAYER        ( 58 ),
		SHOPKEEPERS                 ( 28 ),
		TOYOHIMES                   ( 29 ),
		YORIHIMES                   ( 30 ),

		//silver
		NO_MONSTERS_SLAIN           ( 32 ),
		GRIM_WEAPON                 ( 33 ),
		MONSTERS_SLAIN_3            ( 34 ),
		MONSTERS_SLAIN_4            ( 35 ),
		GOLD_COLLECTED_3            ( 36 ),
		GOLD_COLLECTED_4            ( 37 ),
		ITEM_LEVEL_2                ( 38 ),
		ITEM_LEVEL_3                ( 39 ),
		LEVEL_REACHED_2             ( 40 ),
		LEVEL_REACHED_3             ( 41 ),
		STRENGTH_ATTAINED_2         ( 42 ),
		STRENGTH_ATTAINED_3         ( 43 ),
		FOOD_EATEN_2                ( 44 ),
		FOOD_EATEN_3                ( 45 ),
		ITEMS_CRAFTED_2             ( 46 ),
		ITEMS_CRAFTED_3             ( 47 ),
		BOSS_SLAIN_2                ( 48 ),
		BOSS_SLAIN_3                ( 49 ),
		ALL_POTIONS_IDENTIFIED      ( 50 ),
		ALL_SCROLLS_IDENTIFIED      ( 51 ),
		GAMES_PLAYED_1              ( 54, true ),

		//gold
		PIRANHAS                    ( 64 ),
		MASTERY_COMBO               ( 66 ),
		ITEM_LEVEL_4                ( 67 ),
		LEVEL_REACHED_4             ( 68 ),
		STRENGTH_ATTAINED_4         ( 69 ),
		FOOD_EATEN_4                ( 70 ),
		ITEMS_CRAFTED_4             ( 71 ),
		BOSS_SLAIN_4                ( 72 ),
		ALL_WEAPONS_IDENTIFIED      ( 73 ),
		ALL_ARMOR_IDENTIFIED        ( 74 ),
		ALL_WANDS_IDENTIFIED        ( 75 ),
		ALL_RINGS_IDENTIFIED        ( 76 ),
		ALL_ARTIFACTS_IDENTIFIED    ( 77 ),
		VICTORY                     ( 78 ),
		GAMES_PLAYED_2              ( 81, true ),

		//platinum
		HAPPY_END                   ( 96 ),
		ALL_ITEMS_IDENTIFIED        ( 97, true ),
		VICTORY_WARRIOR,
		VICTORY_MAGE,
		VICTORY_ROGUE,
		VICTORY_HUNTRESS,
		VICTORY_REISENPLAYER,
		VICTORY_NITORIPLAYER,
		VICTORY_YUYUKOPLAYER,
		VICTORY_MURASAPLAYER,
		VICTORY_HINAPLAYER,
		VICTORY_KAGUYAPLAYER,
		VICTORY_KOGASAPLAYER,
		VICTORY_YUKARIPLAYER,
		VICTORY_JUNKOPLAYER,
		VICTORY_RENKOPLAYER,
		VICTORY_SEIJAPLAYER,
		VICTORY_ALL_CLASSES         ( 98, true ),
		GAMES_PLAYED_3              ( 99, true ),
		CHAMPION_1                  ( 100 ),

		//diamond
		GAMES_PLAYED_4              ( 112, true ),
		CHAMPION_2                  ( 113 ),
		CHAMPION_3                  ( 114 ),
		CHAMPION_4                  ( 115 ),
		CHAMPION_5                  ( 116 );

		public boolean meta;

		public int image;

		Badge( int image ) {
			this( image, false );
		}

		Badge( int image, boolean meta ) {
			this.image = image;
			this.meta = meta;
		}

		public String desc(){
			return Messages.get(this, name());
		}

		Badge() {
			this( -1 );
		}
	}

	private static HashSet<Badge> global;
	private static HashSet<Badge> local = new HashSet<>();

	private static boolean saveNeeded = false;

	public static void reset() {
		local.clear();
		loadGlobal();
	}

	public static final String BADGES_FILE	= "badges.dat";
	private static final String BADGES		= "badges";

	private static final HashSet<String> removedBadges = new HashSet<>();
	static{
		//no recently removed badges
	}

	private static final HashMap<String, String> renamedBadges = new HashMap<>();
	static{
		//v1.1.0 (some names were from before 1.1.0, but conversion was added then)
		renamedBadges.put("POTIONS_COOKED_1", "ITEMS_CRAFTED_1");
		renamedBadges.put("POTIONS_COOKED_2", "ITEMS_CRAFTED_2");
		renamedBadges.put("POTIONS_COOKED_3", "ITEMS_CRAFTED_3");
		renamedBadges.put("POTIONS_COOKED_4", "ITEMS_CRAFTED_4");
	}

	public static HashSet<Badge> restore( Bundle bundle ) {
		HashSet<Badge> badges = new HashSet<>();
		if (bundle == null) return badges;

		String[] names = bundle.getStringArray( BADGES );
		for (int i=0; i < names.length; i++) {
			try {
				if (renamedBadges.containsKey(names[i])){
					names[i] = renamedBadges.get(names[i]);
				}
				if (!removedBadges.contains(names[i])){
					badges.add( Badge.valueOf( names[i] ) );
				}
			} catch (Exception e) {
				TouhouPixelDungeon.reportException(e);
			}
		}

		addReplacedBadges(badges);

		return badges;
	}

	public static void store( Bundle bundle, HashSet<Badge> badges ) {
		addReplacedBadges(badges);

		int count = 0;
		String names[] = new String[badges.size()];

		for (Badge badge:badges) {
			names[count++] = badge.toString();
		}
		bundle.put( BADGES, names );
	}

	public static void loadLocal( Bundle bundle ) {
		local = restore( bundle );
	}

	public static void saveLocal( Bundle bundle ) {
		store( bundle, local );
	}

	public static void loadGlobal() {
		if (global == null) {
			try {
				Bundle bundle = FileUtils.bundleFromFile( BADGES_FILE );
				global = restore( bundle );

			} catch (IOException e) {
				global = new HashSet<>();
			}
		}
	}

	public static void saveGlobal() {
		if (saveNeeded) {

			Bundle bundle = new Bundle();
			store( bundle, global );

			try {
				FileUtils.bundleToFile(BADGES_FILE, bundle);
				saveNeeded = false;
			} catch (IOException e) {
				TouhouPixelDungeon.reportException(e);
			}
		}
	}

	public static int unlocked(boolean global){
		if (global) return Badges.global.size();
		else        return Badges.local.size();
	}

	public static void validateMonstersSlain() {
		Badge badge = null;

		if (!local.contains( Badge.MONSTERS_SLAIN_1 ) && Statistics.enemiesSlain >= 10) {
			badge = Badge.MONSTERS_SLAIN_1;
			local.add( badge );
		}
		if (!local.contains( Badge.MONSTERS_SLAIN_2 ) && Statistics.enemiesSlain >= 50) {
			badge = Badge.MONSTERS_SLAIN_2;
			local.add( badge );
		}
		if (!local.contains( Badge.MONSTERS_SLAIN_3 ) && Statistics.enemiesSlain >= 150) {
			badge = Badge.MONSTERS_SLAIN_3;
			local.add( badge );
		}
		if (!local.contains( Badge.MONSTERS_SLAIN_4 ) && Statistics.enemiesSlain >= 250) {
			badge = Badge.MONSTERS_SLAIN_4;
			local.add( badge );
		}
		if (!local.contains( Badge.UNLOCK_KOGASAPLAYER ) && Statistics.enemiesSlain >= 500) {
			badge = Badge.UNLOCK_KOGASAPLAYER;
			local.add( badge );
		}

		displayBadge( badge );
	}

	public static void validateGoldCollected() {
		Badge badge = null;

		if (!local.contains( Badge.GOLD_COLLECTED_1 ) && Statistics.goldCollected >= 100) {
			badge = Badge.GOLD_COLLECTED_1;
			local.add( badge );
		}
		if (!local.contains( Badge.GOLD_COLLECTED_2 ) && Statistics.goldCollected >= 500) {
			badge = Badge.GOLD_COLLECTED_2;
			local.add( badge );
		}
		if (!local.contains( Badge.GOLD_COLLECTED_3 ) && Statistics.goldCollected >= 2500) {
			badge = Badge.GOLD_COLLECTED_3;
			local.add( badge );
		}
		if (!local.contains( Badge.GOLD_COLLECTED_4 ) && Statistics.goldCollected >= 7500) {
			badge = Badge.GOLD_COLLECTED_4;
			local.add( badge );
		}
		if (!local.contains( Badge.UNLOCK_SEIJAPLAYER ) && Statistics.goldCollected >= 100000) {
			badge = Badge.UNLOCK_SEIJAPLAYER;
			local.add( badge );
		}

		displayBadge( badge );
	}

	public static void validateLevelReached() {
		Badge badge = null;

		if (!local.contains( Badge.LEVEL_REACHED_1 ) && Dungeon.hero.lvl >= 6) {
			badge = Badge.LEVEL_REACHED_1;
			local.add( badge );
		}
		if (!local.contains( Badge.LEVEL_REACHED_2 ) && Dungeon.hero.lvl >= 12) {
			badge = Badge.LEVEL_REACHED_2;
			local.add( badge );
		}
		if (!local.contains( Badge.LEVEL_REACHED_3 ) && Dungeon.hero.lvl >= 18) {
			badge = Badge.LEVEL_REACHED_3;
			local.add( badge );
		}
		if (!local.contains( Badge.LEVEL_REACHED_4 ) && Dungeon.hero.lvl >= 24) {
			badge = Badge.LEVEL_REACHED_4;
			local.add( badge );
		}
		if (!local.contains( Badge.UNLOCK_NITORIPLAYER ) && Dungeon.hero.lvl >= 30) {
			badge = Badge.UNLOCK_NITORIPLAYER;
			local.add( badge );
		}


		displayBadge( badge );
	}

	public static void validateStrengthAttained() {
		Badge badge = null;

		if (!local.contains( Badge.STRENGTH_ATTAINED_1 ) && Dungeon.hero.STR >= 13) {
			badge = Badge.STRENGTH_ATTAINED_1;
			local.add( badge );
		}
		if (!local.contains( Badge.STRENGTH_ATTAINED_2 ) && Dungeon.hero.STR >= 15) {
			badge = Badge.STRENGTH_ATTAINED_2;
			local.add( badge );
		}
		if (!local.contains( Badge.STRENGTH_ATTAINED_3 ) && Dungeon.hero.STR >= 17) {
			badge = Badge.STRENGTH_ATTAINED_3;
			local.add( badge );
		}
		if (!local.contains( Badge.STRENGTH_ATTAINED_4 ) && Dungeon.hero.STR >= 19) {
			badge = Badge.STRENGTH_ATTAINED_4;
			local.add( badge );
		}
		if (!local.contains( Badge.UNLOCK_JUNKOPLAYER ) && Dungeon.hero.STR >= 35) {
			badge = Badge.UNLOCK_JUNKOPLAYER;
			local.add( badge );
		}

		displayBadge( badge );
	}

	public static void validateFoodEaten() {
		Badge badge = null;

		if (!local.contains( Badge.FOOD_EATEN_1 ) && Statistics.foodEaten >= 10) {
			badge = Badge.FOOD_EATEN_1;
			local.add( badge );
		}
		if (!local.contains( Badge.FOOD_EATEN_2 ) && Statistics.foodEaten >= 20) {
			badge = Badge.FOOD_EATEN_2;
			local.add( badge );
		}
		if (!local.contains( Badge.FOOD_EATEN_3 ) && Statistics.foodEaten >= 30) {
			badge = Badge.FOOD_EATEN_3;
			local.add( badge );
		}
		if (!local.contains( Badge.FOOD_EATEN_4 ) && Statistics.foodEaten >= 40) {
			badge = Badge.FOOD_EATEN_4;
			local.add( badge );
		}
		if (!local.contains( Badge.UNLOCK_YUYUKOPLAYER ) && Statistics.foodEaten >= 50) {
			badge = Badge.UNLOCK_YUYUKOPLAYER;
			local.add( badge );
		}

		displayBadge( badge );
	}

	public static void validateItemsCrafted() {
		Badge badge = null;

		if (!local.contains( Badge.ITEMS_CRAFTED_1 ) && Statistics.itemsCrafted >= 5) {
			badge = Badge.ITEMS_CRAFTED_1;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEMS_CRAFTED_2 ) && Statistics.itemsCrafted >= 10) {
			badge = Badge.ITEMS_CRAFTED_2;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEMS_CRAFTED_3 ) && Statistics.itemsCrafted >= 15) {
			badge = Badge.ITEMS_CRAFTED_3;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEMS_CRAFTED_4 ) && Statistics.itemsCrafted >= 20) {
			badge = Badge.ITEMS_CRAFTED_4;
			local.add( badge );
		}
		if (!local.contains( Badge.UNLOCK_KAGUYAPLAYER ) && Statistics.itemsCrafted >= 3) {
			badge = Badge.UNLOCK_KAGUYAPLAYER;
			local.add( badge );
		}

		displayBadge( badge );
	}

	public static void validatePiranhasKilled() {
		Badge badge = null;

		if (!local.contains( Badge.UNLOCK_MURASAPLAYER ) && Statistics.piranhasKilled >= 3) {
			badge = Badge.UNLOCK_MURASAPLAYER;
			local.add( badge );
		}
		if (!local.contains( Badge.PIRANHAS ) && Statistics.piranhasKilled >= 6) {
			badge = Badge.PIRANHAS;
			local.add( badge );
		}

		displayBadge( badge );
	}

	public static void validateToyohimesKilled() {
		Badge badge = null;

		if (!local.contains( Badge.TOYOHIMES ) && Statistics.toyohimesKilled >= 1) {
			badge = Badge.TOYOHIMES;
			local.add( badge );
		}

		displayBadge( badge );
	}

	public static void validateYorihimesKilled() {
		Badge badge = null;

		if (!local.contains( Badge.YORIHIMES ) && Statistics.yorihimesKilled >= 1) {
			badge = Badge.YORIHIMES;
			local.add( badge );
		}

		displayBadge( badge );
	}

	public static void validateShopkeepersKilled() {
		Badge badge = null;

		if (!local.contains( Badge.SHOPKEEPERS ) && Statistics.shopkeepersKilled >= 1) {
			badge = Badge.SHOPKEEPERS;
			local.add( badge );
		}

		displayBadge( badge );
	}

	public static void validateItemLevelAquired( Item item ) {

		// This method should be called:
		// 1) When an item is obtained (Item.collect)
		// 2) When an item is upgraded (ScrollOfUpgrade, ScrollOfWeaponUpgrade, ShortSword, WandOfMagicMissile)
		// 3) When an item is identified

		// Note that artifacts should never trigger this badge as they are alternatively upgraded
		if (!item.levelKnown || item instanceof Artifact) {
			return;
		}

		Badge badge = null;
		if (!local.contains( Badge.ITEM_LEVEL_1 ) && item.level() >= 3) {
			badge = Badge.ITEM_LEVEL_1;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEM_LEVEL_2 ) && item.level() >= 6) {
			badge = Badge.ITEM_LEVEL_2;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEM_LEVEL_3 ) && item.level() >= 9) {
			badge = Badge.ITEM_LEVEL_3;
			local.add( badge );
		}
		if (!local.contains( Badge.ITEM_LEVEL_4 ) && item.level() >= 12) {
			badge = Badge.ITEM_LEVEL_4;
			local.add( badge );
		}
		if (!local.contains( Badge.UNLOCK_HINAPLAYER ) && item.level() >= 15) {
			badge = Badge.UNLOCK_HINAPLAYER;
			local.add( badge );
		}

		displayBadge( badge );
	}

	public static void validateItemsIdentified() {

		for (Catalog cat : Catalog.values()){
			if (cat.allSeen()){
				Badge b = Catalog.catalogBadges.get(cat);
				if (!global.contains(b)){
					displayBadge(b);
				}
			}
		}

		if (!global.contains( Badge.ALL_ITEMS_IDENTIFIED ) &&
				global.contains( Badge.ALL_WEAPONS_IDENTIFIED ) &&
				global.contains( Badge.ALL_ARMOR_IDENTIFIED ) &&
				global.contains( Badge.ALL_WANDS_IDENTIFIED ) &&
				global.contains( Badge.ALL_RINGS_IDENTIFIED ) &&
				global.contains( Badge.ALL_ARTIFACTS_IDENTIFIED ) &&
				global.contains( Badge.ALL_POTIONS_IDENTIFIED ) &&
				global.contains( Badge.ALL_SCROLLS_IDENTIFIED )) {

			displayBadge( Badge.ALL_ITEMS_IDENTIFIED );
		}
	}

	public static void validateMageUnlock(){
		if (Statistics.upgradesUsed >= 1 && !global.contains(Badge.UNLOCK_MAGE)){
			displayBadge( Badge.UNLOCK_MAGE );
		}
	}

	public static void validateTenkyuuUnlock(){
		if (Statistics.upgradesUsed >= 55 && !global.contains(Badge.UNLOCK_TENKYUUPLAYER)){
			displayBadge( Badge.UNLOCK_TENKYUUPLAYER );
		}
	}

	public static void validateRogueUnlock(){
		if (Statistics.sneakAttacks >= 10 && !global.contains(Badge.UNLOCK_ROGUE)){
			displayBadge( Badge.UNLOCK_ROGUE );
		}
	}

	public static void validateHuntressUnlock(){
		if (Statistics.thrownAssists >= 15 && !global.contains(Badge.UNLOCK_HUNTRESS)){
			displayBadge( Badge.UNLOCK_HUNTRESS );
		}
	}

	public static void validateReisenUnlock(){
		if (Statistics.toyohimesKilled >= 1 && !global.contains(Badge.UNLOCK_REISENPLAYER)){
			displayBadge( Badge.UNLOCK_REISENPLAYER );
		}
	}

	public static void validateRenkoUnlock(){
		if (Statistics.toyohimesKilled >= 5 && !global.contains(Badge.UNLOCK_RENKOPLAYER)){
			displayBadge( Badge.UNLOCK_RENKOPLAYER );
		}
	}

	public static void validateMasteryCombo( int n ) {
		if (!local.contains( Badge.MASTERY_COMBO ) && n == 10) {
			Badge badge = Badge.MASTERY_COMBO;
			local.add( badge );
			displayBadge( badge );
		}
	}

	public static void validateVictory() {

		Badge badge = Badge.VICTORY;
		displayBadge( badge );

		switch (Dungeon.hero.heroClass) {
			case WARRIOR:
				badge = Badge.VICTORY_WARRIOR;
				break;
			case MAGE:
				badge = Badge.VICTORY_MAGE;
				break;
			case ROGUE:
				badge = Badge.VICTORY_ROGUE;
				break;
			case HUNTRESS:
				badge = Badge.VICTORY_HUNTRESS;
				break;
			case REISENPLAYER:
				badge = Badge.VICTORY_REISENPLAYER;
				break;
			case NITORIPLAYER:
				badge = Badge.VICTORY_NITORIPLAYER;
				break;
			case YUYUKOPLAYER:
				badge = Badge.VICTORY_YUYUKOPLAYER;
				break;
			case MURASAPLAYER:
				badge = Badge.VICTORY_MURASAPLAYER;
				break;
			case HINAPLAYER:
				badge = Badge.VICTORY_HINAPLAYER;
				break;
			case KAGUYAPLAYER:
				badge = Badge.VICTORY_KAGUYAPLAYER;
				break;
			case YUKARIPLAYER:
				badge = Badge.VICTORY_YUKARIPLAYER;
				break;
			case JUNKOPLAYER:
				badge = Badge.VICTORY_JUNKOPLAYER;
				break;
			case RENKOPLAYER:
				badge = Badge.VICTORY_RENKOPLAYER;
				break;
			case SEIJAPLAYER:
				badge = Badge.VICTORY_SEIJAPLAYER;
				break;
		}
		local.add( badge );
		if (!global.contains( badge )) {
			global.add( badge );
			saveNeeded = true;
		}

		if (global.contains( Badge.VICTORY_WARRIOR ) &&
				global.contains( Badge.VICTORY_MAGE ) &&
				global.contains( Badge.VICTORY_ROGUE ) &&
				global.contains( Badge.VICTORY_REISENPLAYER ) &&
				global.contains( Badge.VICTORY_NITORIPLAYER ) &&
				global.contains( Badge.VICTORY_YUYUKOPLAYER ) &&
				global.contains( Badge.VICTORY_MURASAPLAYER ) &&
				global.contains( Badge.VICTORY_HINAPLAYER ) &&
				global.contains( Badge.VICTORY_KAGUYAPLAYER ) &&
				global.contains( Badge.VICTORY_YUKARIPLAYER ) &&
				global.contains( Badge.VICTORY_JUNKOPLAYER ) &&
				global.contains( Badge.VICTORY_RENKOPLAYER ) &&
				global.contains( Badge.VICTORY_SEIJAPLAYER ) &&
				global.contains( Badge.VICTORY_HUNTRESS )) {

			badge = Badge.VICTORY_ALL_CLASSES;
			displayBadge( badge );
		}
	}

	public static void validateNoKilling() {
		if (!local.contains( Badge.NO_MONSTERS_SLAIN ) && Statistics.completedWithNoKilling) {
			Badge badge = Badge.NO_MONSTERS_SLAIN;
			local.add( badge );
			displayBadge( badge );
		}
	}

	public static void validateGrimWeapon() {
		if (!local.contains( Badge.GRIM_WEAPON )) {
			Badge badge = Badge.GRIM_WEAPON;
			local.add( badge );
			displayBadge( badge );
		}
	}

	public static void validateGamesPlayed() {
		Badge badge = null;
		if (Rankings.INSTANCE.totalNumber >= 10) {
			badge = Badge.GAMES_PLAYED_1;
		}
		if (Rankings.INSTANCE.totalNumber >= 50) {
			badge = Badge.GAMES_PLAYED_2;
		}
		if (Rankings.INSTANCE.totalNumber >= 100) {
			badge = Badge.GAMES_PLAYED_3;
		}
		if (Rankings.INSTANCE.totalNumber >= 200) {
			badge = Badge.GAMES_PLAYED_4;
		}

		displayBadge( badge );
	}

	//necessary in order to display the happy end badge in the surface scene
	public static void silentValidateHappyEnd() {
		if (!local.contains( Badge.HAPPY_END )){
			local.add( Badge.HAPPY_END );
		}
	}

	public static void validateHappyEnd() {
		displayBadge( Badge.HAPPY_END );
	}

	public static void validateChampion( int challenges ) {
		if (challenges == 0) return;
		Badge badge = null;
		if (challenges >= 1) {
			badge = Badge.CHAMPION_1;
		}
		if (challenges >= 3){
			if (!global.contains(badge)){
				global.add(badge);
				saveNeeded = true;
			}
			badge = Badge.CHAMPION_2;
		}
		if (challenges >= 6){
			if (!global.contains(badge)){
				global.add(badge);
				saveNeeded = true;
			}
			badge = Badge.CHAMPION_3;
		}
		if (challenges >= 10){
			if (!global.contains(badge)){
				global.add(badge);
				saveNeeded = true;
			}
			badge = Badge.CHAMPION_4;
		}
		if (challenges >= 10){
			if (!global.contains(badge)){
				global.add(badge);
				saveNeeded = true;
			}
			badge = Badge.UNLOCK_YUKARIPLAYER;
		}
		if (challenges >= 15){
			if (!global.contains(badge)){
				global.add(badge);
				saveNeeded = true;
			}
			badge = Badge.CHAMPION_5;
		}
		local.add(badge);
		displayBadge( badge );
	}

	private static void displayBadge( Badge badge ) {

		if (badge == null) {
			return;
		}

		if (global.contains( badge )) {

			if (!badge.meta) {
				GLog.h( Messages.get(Badges.class, "endorsed", badge.desc()) );
			}

		} else {

			global.add( badge );
			saveNeeded = true;

			if (badge.meta) {
				GLog.h( Messages.get(Badges.class, "new_super", badge.desc()) );
			} else {
				GLog.h( Messages.get(Badges.class, "new", badge.desc()) );
			}
		}
	}

	public static boolean isUnlocked( Badge badge ) {
		return global.contains( badge );
	}

	public static HashSet<Badge> allUnlocked(){
		loadGlobal();
		return new HashSet<>(global);
	}

	public static void disown( Badge badge ) {
		loadGlobal();
		global.remove( badge );
		saveNeeded = true;
	}

	public static void addGlobal( Badge badge ){
		if (!global.contains(badge)){
			global.add( badge );
			saveNeeded = true;
		}
	}

	public static List<Badge> filterReplacedBadges( boolean global ) {

		ArrayList<Badge> badges = new ArrayList<>(global ? Badges.global : Badges.local);

		Iterator<Badge> iterator = badges.iterator();
		while (iterator.hasNext()) {
			Badge badge = iterator.next();
			if ((!global && badge.meta) || badge.image == -1) {
				iterator.remove();
			}
		}

		Collections.sort(badges);

		return filterReplacedBadges(badges);

	}

	private static final Badge[][] tierBadgeReplacements = new Badge[][]{
			{Badge.MONSTERS_SLAIN_1, Badge.MONSTERS_SLAIN_2, Badge.MONSTERS_SLAIN_3, Badge.MONSTERS_SLAIN_4},
			{Badge.GOLD_COLLECTED_1, Badge.GOLD_COLLECTED_2, Badge.GOLD_COLLECTED_3, Badge.GOLD_COLLECTED_4},
			{Badge.ITEM_LEVEL_1, Badge.ITEM_LEVEL_2, Badge.ITEM_LEVEL_3, Badge.ITEM_LEVEL_4},
			{Badge.LEVEL_REACHED_1, Badge.LEVEL_REACHED_2, Badge.LEVEL_REACHED_3, Badge.LEVEL_REACHED_4},
			{Badge.STRENGTH_ATTAINED_1, Badge.STRENGTH_ATTAINED_2, Badge.STRENGTH_ATTAINED_3, Badge.STRENGTH_ATTAINED_4},
			{Badge.FOOD_EATEN_1, Badge.FOOD_EATEN_2, Badge.FOOD_EATEN_3, Badge.FOOD_EATEN_4},
			{Badge.ITEMS_CRAFTED_1, Badge.ITEMS_CRAFTED_2, Badge.ITEMS_CRAFTED_3, Badge.ITEMS_CRAFTED_4},
			{Badge.BOSS_SLAIN_1, Badge.BOSS_SLAIN_2, Badge.BOSS_SLAIN_3, Badge.BOSS_SLAIN_4},
			{Badge.GAMES_PLAYED_1, Badge.GAMES_PLAYED_2, Badge.GAMES_PLAYED_3, Badge.GAMES_PLAYED_4},
			{Badge.CHAMPION_1, Badge.CHAMPION_2, Badge.CHAMPION_3, Badge.CHAMPION_4, Badge.CHAMPION_5}
	};

	private static final Badge[][] metaBadgeReplacements = new Badge[][]{
			{Badge.ALL_WEAPONS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_ARMOR_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_WANDS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_RINGS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_ARTIFACTS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_POTIONS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED},
			{Badge.ALL_SCROLLS_IDENTIFIED, Badge.ALL_ITEMS_IDENTIFIED}
	};

	public static List<Badge> filterReplacedBadges( List<Badge> badges ) {

		leaveBest( badges, Badge.MONSTERS_SLAIN_1, Badge.MONSTERS_SLAIN_2, Badge.MONSTERS_SLAIN_3, Badge.MONSTERS_SLAIN_4 );
		leaveBest( badges, Badge.GOLD_COLLECTED_1, Badge.GOLD_COLLECTED_2, Badge.GOLD_COLLECTED_3, Badge.GOLD_COLLECTED_4 );
		leaveBest( badges, Badge.BOSS_SLAIN_1, Badge.BOSS_SLAIN_2, Badge.BOSS_SLAIN_3, Badge.BOSS_SLAIN_4 );
		leaveBest( badges, Badge.LEVEL_REACHED_1, Badge.LEVEL_REACHED_2, Badge.LEVEL_REACHED_3, Badge.LEVEL_REACHED_4 );
		leaveBest( badges, Badge.STRENGTH_ATTAINED_1, Badge.STRENGTH_ATTAINED_2, Badge.STRENGTH_ATTAINED_3, Badge.STRENGTH_ATTAINED_4 );
		leaveBest( badges, Badge.FOOD_EATEN_1, Badge.FOOD_EATEN_2, Badge.FOOD_EATEN_3, Badge.FOOD_EATEN_4 );
		leaveBest( badges, Badge.ITEM_LEVEL_1, Badge.ITEM_LEVEL_2, Badge.ITEM_LEVEL_3, Badge.ITEM_LEVEL_4 );
		leaveBest( badges, Badge.ITEMS_CRAFTED_1, Badge.ITEMS_CRAFTED_2, Badge.ITEMS_CRAFTED_3, Badge.ITEMS_CRAFTED_4 );
		leaveBest( badges, Badge.GAMES_PLAYED_1, Badge.GAMES_PLAYED_2, Badge.GAMES_PLAYED_3, Badge.GAMES_PLAYED_4 );
		leaveBest( badges, Badge.CHAMPION_1, Badge.CHAMPION_2, Badge.CHAMPION_3, Badge.CHAMPION_4, Badge.CHAMPION_5 );

		for (Badge[] tierReplace : tierBadgeReplacements){
			leaveBest( badges, tierReplace );
		}

		for (Badge[] metaReplace : metaBadgeReplacements){
			leaveBest( badges, metaReplace );
		}

		return badges;
	}

	private static void leaveBest( Collection<Badge> list, Badge...badges ) {
		for (int i=badges.length-1; i > 0; i--) {
			if (list.contains( badges[i])) {
				for (int j=0; j < i; j++) {
					list.remove( badges[j] );
				}
				break;
			}
		}
	}

	public static List<Badge> filterHigherIncrementalBadges(List<Badges.Badge> badges ) {

		for (Badge[] tierReplace : tierBadgeReplacements){
			leaveWorst( badges, tierReplace );
		}

		Collections.sort( badges );

		return badges;
	}

	private static void leaveWorst( Collection<Badge> list, Badge...badges ) {
		for (int i=0; i < badges.length; i++) {
			if (list.contains( badges[i])) {
				for (int j=i+1; j < badges.length; j++) {
					list.remove( badges[j] );
				}
				break;
			}
		}
	}

	public static Collection<Badge> addReplacedBadges(Collection<Badges.Badge> badges ) {

		for (Badge[] tierReplace : tierBadgeReplacements){
			addLower( badges, tierReplace );
		}

		for (Badge[] metaReplace : metaBadgeReplacements){
			addLower( badges, metaReplace );
		}

		return badges;
	}

	private static void addLower( Collection<Badge> list, Badge...badges ) {
		for (int i=badges.length-1; i > 0; i--) {
			if (list.contains( badges[i])) {
				for (int j=0; j < i; j++) {
					list.add( badges[j] );
				}
				break;
			}
		}
	}
}