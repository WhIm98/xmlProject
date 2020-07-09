/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HP
 */
public class EscapseHTMLUtils {private static final HashMap<String, String> htmlEncodeChars = new HashMap<>();

    static {
        
        htmlEncodeChars.put("&amp;", "\"");
        htmlEncodeChars.put("&amp;", "&");
        htmlEncodeChars.put("&lt;", "<");
        htmlEncodeChars.put("&gt;", ">");
        htmlEncodeChars.put("&euro;", "€");
        htmlEncodeChars.put("&nbsp;", " ");
        htmlEncodeChars.put("&Aacute;", "Á");
        htmlEncodeChars.put("&aacute;", "á");
        htmlEncodeChars.put("&Acirc;", "Â");
        htmlEncodeChars.put("&acirc;", "â");
        htmlEncodeChars.put("&acute;", "´");
        htmlEncodeChars.put("&AElig;", "Æ");
        htmlEncodeChars.put("&aelig;", "æ");
        htmlEncodeChars.put("&Agrave;", "À");
        htmlEncodeChars.put("&agrave;", "à");
        htmlEncodeChars.put("&Aring;", "Å");
        htmlEncodeChars.put("&Atilde;", "Ã");
        htmlEncodeChars.put("&aring;", "å");
        htmlEncodeChars.put("&atilde;", "ã");
        htmlEncodeChars.put("&Auml;", "Ä");
        htmlEncodeChars.put("&auml;", "ä");
        htmlEncodeChars.put("&brvbar;", "¦");
        htmlEncodeChars.put("&Ccedil;", "Ç");
        htmlEncodeChars.put("&ccedil;", "ç");
        htmlEncodeChars.put("&cedil;", "¸");
        htmlEncodeChars.put("&cent;", "¢");
        htmlEncodeChars.put("&circ;", "ˆ");
        htmlEncodeChars.put("&copy;", "©");
        htmlEncodeChars.put("&curren;", "¤");
        htmlEncodeChars.put("&deg;", "°");
        htmlEncodeChars.put("&divide;", "÷");
        htmlEncodeChars.put("&Eacute;", "É");
        htmlEncodeChars.put("&eacute;", "é");
        htmlEncodeChars.put("&Ecirc;", "Ê");
        htmlEncodeChars.put("&ecirc;", "ê");
        htmlEncodeChars.put("&Egrave;", "È");
        htmlEncodeChars.put("&egrave;", "è");
        htmlEncodeChars.put("&ETH;", "Ð");
        htmlEncodeChars.put("&eth;", "ð");
        htmlEncodeChars.put("&Euml;", "Ë");
        htmlEncodeChars.put("&euml;", "ë");
        htmlEncodeChars.put("&euro;", "€");
        htmlEncodeChars.put("&fnof;", "ƒ");
        htmlEncodeChars.put("&frac12;", "½");
        htmlEncodeChars.put("&frac14;", "¼");
        htmlEncodeChars.put("&frac34;", "¾");
        htmlEncodeChars.put("&Iacute;", "Í");
        htmlEncodeChars.put("&iacute;", "í");
        htmlEncodeChars.put("&Icirc;", "Î");
        htmlEncodeChars.put("&icirc;", "î");
        htmlEncodeChars.put("&iexcl;", "¡");
        htmlEncodeChars.put("&Igrave;", "Ì");
        htmlEncodeChars.put("&igrave;", "ì");
        htmlEncodeChars.put("&iquest;", "¿");
        htmlEncodeChars.put("&Iuml;", "Ï");
        htmlEncodeChars.put("&iuml;", "ï");
        htmlEncodeChars.put("&laquo;", "«");
        htmlEncodeChars.put("&macr;", "¯");
        htmlEncodeChars.put("&micro;", "µ");
        htmlEncodeChars.put("&middot;", "¬");
        htmlEncodeChars.put("&Ntilde;", "Ñ");
        htmlEncodeChars.put("&ntilde;", "ñ");
        htmlEncodeChars.put("&Oacute;", "Ó");
        htmlEncodeChars.put("&oacute;", "ó");
        htmlEncodeChars.put("&Ocirc;", "Ô");
        htmlEncodeChars.put("&ocirc;", "ô");
        htmlEncodeChars.put("&OElig;", "Œ");
        htmlEncodeChars.put("&oelig;", "œ");
        htmlEncodeChars.put("&Ograve;", "Ò");
        htmlEncodeChars.put("&ograve;", "ò");
        htmlEncodeChars.put("&ordf;", "ª");
        htmlEncodeChars.put("&ordm;", "º");
        htmlEncodeChars.put("&Oslash;", "Ø");
        htmlEncodeChars.put("&oslash;", "ø");
        htmlEncodeChars.put("&Otilde;", "Õ");
        htmlEncodeChars.put("&otilde;", "õ");
        htmlEncodeChars.put("&Ouml;", "Ö");
        htmlEncodeChars.put("&ouml;", "ö");
        htmlEncodeChars.put("&para;", "¶");
        htmlEncodeChars.put("&plusmn;", "±");
        htmlEncodeChars.put("&pound;", "£");
        htmlEncodeChars.put("&raquo;", "»");
        htmlEncodeChars.put("&reg;", "®");
        htmlEncodeChars.put("&Scaron;", "Š");
        htmlEncodeChars.put("&scaron;", "š");
        htmlEncodeChars.put("&sect;", "§");
        htmlEncodeChars.put("&sup1;", "¹");
        htmlEncodeChars.put("&sup2;", "²");
        htmlEncodeChars.put("&sup3;", "³");
        htmlEncodeChars.put("&szlig;", "ß");
        htmlEncodeChars.put("&THORN;", "Þ");
        htmlEncodeChars.put("&thorn;", "þ");
        htmlEncodeChars.put("&tilde;", "˜");
        htmlEncodeChars.put("&times;", "×");
        htmlEncodeChars.put("&Uacute;", "Ú");
        htmlEncodeChars.put("&uacute;", "ú");
        htmlEncodeChars.put("&Ucirc;", "Û");
        htmlEncodeChars.put("&ucirc;", "û");
        htmlEncodeChars.put("&Ugrave;", "Ù");
        htmlEncodeChars.put("&ugrave;", "ù");
        htmlEncodeChars.put("&uml;", "¨");
        htmlEncodeChars.put("&Yacute;", "Ý");
        htmlEncodeChars.put("&yacute;", "ý");
        htmlEncodeChars.put("&yen;", "¥");
        htmlEncodeChars.put("&ndash;", "–");
        htmlEncodeChars.put("&mdash;", "—");
        htmlEncodeChars.put("&lsquo;", "‘");
        htmlEncodeChars.put("&rsquo;", "’");
        htmlEncodeChars.put("&sbquo;", "‚");
        htmlEncodeChars.put("&ldquo;", "“");
        htmlEncodeChars.put("&rdquo;", "”");
        htmlEncodeChars.put("&lsaquo;", "‹");
        htmlEncodeChars.put("&rsaquo;", "›");
        htmlEncodeChars.put("&bull;", "•");
        htmlEncodeChars.put("&hellip;", "…");
        htmlEncodeChars.put("&oline;", "‾");
        htmlEncodeChars.put("&frasl;", "⁄");

    }

    private EscapseHTMLUtils() {
    }

    public static String encodeHtml(String source) {
        return encode(source, htmlEncodeChars);
    }

    private static String encode(String source, HashMap<String, String> encodingTable) {
        if (null == source) {
            return null;
        }

        if (null == encodingTable) {
            return source;
        }
        return recursive(source, encodingTable);
    }

    private static String recursive(String source, HashMap<String, String> encodingTable) {
        boolean check = false;
        for (Map.Entry<String, String> entry : encodingTable.entrySet()) {
            String key = entry.getKey();
            if (source.contains(key)) {
                check = true;
                source = source.replace(entry.getKey(), entry.getValue());
            }
        }
        if (!check) {
            return source;
        }
        return recursive(source, encodingTable);
    }
}
