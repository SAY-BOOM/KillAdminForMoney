package zxc.deadinside.sayboom.killadminformoney.messages;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MessageUtils {
    private static final Pattern HEX = Pattern.compile("(?i)&#([0-9A-F]{6})");
    private static final Pattern BUNGEE = Pattern.compile("(?i)&x((&[0-9A-F]){6})");

    public static String parse(String input) {
        String msg = ChatColor.translateAlternateColorCodes('&', input);
        msg = replaceHex(msg);
        return replaceBungee(msg);
    }

    private static String replaceHex(String text) {
        Matcher m = HEX.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, ChatColor.of("#" + m.group(1)).toString());
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private static String replaceBungee(String text) {
        Matcher m = BUNGEE.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String seq = m.group(1);
            StringBuilder hex = new StringBuilder("#");
            for (int i = 1; i < seq.length(); i += 2) {
                hex.append(seq.charAt(i));
            }
            m.appendReplacement(sb, ChatColor.of(hex.toString()).toString());
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
