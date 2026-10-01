HOW TO ADD A CUSTOM SOUND FILE
=============================

1. MAKE THE AUDIO (outside the mod):
   - Export as OGG Vorbis (.ogg), MONO (single channel). Stereo breaks
     Minecraft's distance handling.
   - Free option: Audacity (https://www.audacityteam.org/).
   - Compress it to keep the mod jar small.

2. DROP THE FILE HERE (or in a subfolder like block/):
   src/main/resources/assets/dungeonscontent/sounds/<name>.ogg

   IMPORTANT: file names must be all lowercase (a-z, 0-9, _, ., -),
   otherwise Minecraft ignores them.
   Example: this folder contains block/tuffite_dig1.ogg ... dig4.ogg.

   TIP: to have the game pick randomly between several files (like vanilla
   stone dig 1-4), list them all under ONE event in sounds.json instead of
   making one event per file.

3. DECLARE IT in ../sounds.json with the same <name>:
     "tuffite_dig": {
       "subtitle": "sound.dungeonscontent.tuffite_dig",
       "sounds": [
         "dungeonscontent:block/tuffite_dig1",
         "dungeonscontent:block/tuffite_dig2",
         "dungeonscontent:block/tuffite_dig3",
         "dungeonscontent:block/tuffite_dig4"
       ]
     }

4. ADD THE SUBTITLE to ../lang/en_us.json:
     "sound.dungeonscontent.tuffite_dig": "Tuffite digging"

5. REGISTER IT in ModSounds.java (one line per event):
     public static final SoundEvent TUFFITE_DIG = registerSound("tuffite_dig");

6. TO USE IT AS BLOCK SOUNDS, build a SoundType in ModSounds.java:
     public static final SoundType TUFFITE = new SoundType(
         1.0F, 1.0F,
         TUFFITE_DIG, TUFFITE_DIG, TUFFITE_DIG, TUFFITE_DIG, TUFFITE_DIG);
   (order: volume, pitch, break, step, place, hit, fall)
   and point the block at it in ModBlocks.java: .sound(ModSounds.TUFFITE)

   TO PLAY IT manually from code on the logical server side, e.g.:
     level.playSound(null, pos, ModSounds.TUFFITE_DIG, SoundSource.BLOCKS, 1.0F, 1.0F);

Docs: https://docs.fabricmc.net/develop/sounds/custom
      https://docs.fabricmc.net/develop/sounds/using-sounds
