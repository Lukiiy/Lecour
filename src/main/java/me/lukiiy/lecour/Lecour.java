package me.lukiiy.lecour;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lecour {
    private static final Pattern LEGACY = Pattern.compile("§(.)");
    public static final Map<Character, String> LEGACY_TO_MINI = new HashMap<>();

    public static final TagResolver EXTRA_TAGS = TagResolver.builder()
            .tag("minecoin_gold", Tag.styling(ColorStorage.MINECOIN_GOLD))

            .tag("material_quartz", Tag.styling(ColorStorage.QUARTZ_MATERIAL))
            .tag("material_iron", Tag.styling(ColorStorage.IRON_MATERIAL))
            .tag("material_netherite", Tag.styling(ColorStorage.NETHERITE_MATERIAL))
            .tag("material_redstone", Tag.styling(ColorStorage.REDSTONE_MATERIAL))
            .tag("material_copper", Tag.styling(ColorStorage.COPPER_MATERIAL))
            .tag("material_gold", Tag.styling(ColorStorage.GOLD_MATERIAL))
            .tag("material_emerald", Tag.styling(ColorStorage.EMERALD_MATERIAL))
            .tag("material_diamond", Tag.styling(ColorStorage.DIAMOND_MATERIAL))
            .tag("material_lapis", Tag.styling(ColorStorage.LAPIS_MATERIAL))
            .tag("material_amethyst", Tag.styling(ColorStorage.AMETHYST_MATERIAL))
            .tag("material_resin", Tag.styling(ColorStorage.RESIN_MATERIAL))

            .tag("m_quartz", Tag.styling(ColorStorage.QUARTZ_MATERIAL))
            .tag("m_iron", Tag.styling(ColorStorage.IRON_MATERIAL))
            .tag("m_netherite", Tag.styling(ColorStorage.NETHERITE_MATERIAL))
            .tag("m_redstone", Tag.styling(ColorStorage.REDSTONE_MATERIAL))
            .tag("m_copper", Tag.styling(ColorStorage.COPPER_MATERIAL))
            .tag("m_gold", Tag.styling(ColorStorage.GOLD_MATERIAL))
            .tag("m_emerald", Tag.styling(ColorStorage.EMERALD_MATERIAL))
            .tag("m_diamond", Tag.styling(ColorStorage.DIAMOND_MATERIAL))
            .tag("m_lapis", Tag.styling(ColorStorage.LAPIS_MATERIAL))
            .tag("m_amethyst", Tag.styling(ColorStorage.AMETHYST_MATERIAL))
            .tag("m_resin", Tag.styling(ColorStorage.RESIN_MATERIAL))

            .build();

    static {
        LEGACY_TO_MINI.put('0', "black");
        LEGACY_TO_MINI.put('1', "dark_blue");
        LEGACY_TO_MINI.put('2', "dark_green");
        LEGACY_TO_MINI.put('3', "dark_aqua");
        LEGACY_TO_MINI.put('4', "dark_red");
        LEGACY_TO_MINI.put('5', "dark_purple");
        LEGACY_TO_MINI.put('6', "gold");
        LEGACY_TO_MINI.put('7', "gray");
        LEGACY_TO_MINI.put('8', "dark_gray");
        LEGACY_TO_MINI.put('9', "blue");
        LEGACY_TO_MINI.put('a', "green");
        LEGACY_TO_MINI.put('b', "aqua");
        LEGACY_TO_MINI.put('c', "red");
        LEGACY_TO_MINI.put('d', "light_purple");
        LEGACY_TO_MINI.put('e', "yellow");
        LEGACY_TO_MINI.put('f', "white");

        // Format
        LEGACY_TO_MINI.put('k', "obf");
        LEGACY_TO_MINI.put('l', "b");
        LEGACY_TO_MINI.put('m', "st");
        LEGACY_TO_MINI.put('n', "u");
        LEGACY_TO_MINI.put('o', "i");
        LEGACY_TO_MINI.put('r', "reset");
    }

    /**
     * Directly replaces legacy color codes with MiniMessage equivalents
     *
     * @param message The message
     * @return A MiniMessage-friendly String
     */
    public static String modernizeFormat(String message) {
        Matcher m = LEGACY.matcher(message);
        StringBuffer buffer = new StringBuffer();

        while (m.find()) {
            char code = m.group(1).charAt(0);
            String tag = LEGACY_TO_MINI.get(code);

            if (tag != null) m.appendReplacement(buffer, '<' + tag + '>');
            else m.appendReplacement(buffer, "§" + code);
        }

        m.appendTail(buffer);
        return buffer.toString();
    }

    /**
     * A MiniMessage build with extra colors (from Bedrock Edition) & direct legacy colors support
     */
    public static final MiniMessage MINI = MiniMessage.builder()
            .preProcessor(Lecour::modernizeFormat)
            .tags(TagResolver.builder().resolvers(TagResolver.standard(), EXTRA_TAGS).build())
            .build();
}
