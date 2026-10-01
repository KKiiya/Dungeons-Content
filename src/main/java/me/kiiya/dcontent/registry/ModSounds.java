package me.kiiya.dcontent.registry;

import me.kiiya.dcontent.DungeonsContent;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

/**
 * Central registry for this mod's custom {@link SoundEvent}s.
 *
 * <p>Follows the official Fabric docs: "Creating Custom Sounds"
 * (https://docs.fabricmc.net/develop/sounds/custom).
 *
 * <p><b>HOW TO ADD A NEW CUSTOM SOUND (you don't need to create the audio here,
 * just wire it into the mod):</b>
 * <ol>
 *   <li><b>Prepare the audio file (outside the code).</b> Export it as OGG Vorbis
 *       with a single channel (Mono), e.g. with Audacity. Keep it small/compressed
 *       so the mod jar stays light.</li>
 *   <li><b>Drop the file into the mod:</b>
 *       {@code src/main/resources/assets/dungeonscontent/sounds/<name>.ogg}
 *       (see {@code sounds/README.txt}). The file name (without extension) should
 *       match the id used below and in {@code sounds.json}.</li>
 *   <li><b>Declare it in {@code src/main/resources/assets/dungeonscontent/sounds.json}:</b>
 *       <pre>{@code
 * "my_sound": {
 *   "subtitle": "sound.dungeonscontent.my_sound",
 *   "sounds": [ "dungeonscontent:my_sound" ]
 * }
 *       }</pre>
 *       The {@code subtitle} key is shown when the "Subtitles" accessibility
 *       option is enabled.</li>
 *   <li><b>Add the subtitle text</b> to
 *       {@code src/main/resources/assets/dungeonscontent/lang/en_us.json}:
 *       {@code "sound.dungeonscontent.my_sound": "My Sound"}.</li>
 *   <li><b>Register it here</b> by adding one line:
 *       {@code public static final SoundEvent MY_SOUND = registerSound("my_sound");}
 *       (the string must match the {@code sounds.json} key and the {@code .ogg}
 *       file name).</li>
 *   <li><b>Done.</b> {@link #initialize()} is already called from the mod
 *       initializer, so the new constant is registered automatically. Play it
 *       with {@code level.playSound(...)} / {@code entity.playSound(...)} on the
 *       <b>logical server</b> side (see
 *       https://docs.fabricmc.net/develop/sounds/using-sounds).</li>
 * </ol>
 */
public class ModSounds {

    private ModSounds() {
        
    }

    // One SoundEvent holding all 4 files: the game picks one at random
    // every time this event plays (same pattern vanilla uses, e.g. stone dig 1-4).
    public static final SoundEvent TUFFITE_DIG = registerSound("tuffite_dig");

    // Custom SoundType for the Tuffite blocks. All 5 actions (break, step,
    // place, hit, fall) point at the same event, so every action plays
    // a random one of the 4 files.
    // Constructor order: volume, pitch, break, step, place, hit, fall.
    public static final SoundType TUFFITE = new SoundType(
            1.0F, 1.0F,
            TUFFITE_DIG, TUFFITE_DIG, TUFFITE_DIG, TUFFITE_DIG, TUFFITE_DIG);

    private static SoundEvent registerSound(String id) {
        Identifier identifier = DungeonsContent.id(id);
        return Registry.register(
                BuiltInRegistries.SOUND_EVENT,
                identifier,
                SoundEvent.createVariableRangeEvent(identifier));
    }

    public static void initialize() {
        DungeonsContent.LOGGER.info("Registering Custom Sounds for " + DungeonsContent.MOD_ID);
    }
}
