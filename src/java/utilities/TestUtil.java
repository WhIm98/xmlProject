/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author HP
 */
public class TestUtil {
    public static String refineHtml(String src) {
        src = removeMiscTags(src);

        XmlSyxtaxChecker xmlSyntaxChecker = new XmlSyxtaxChecker();
        src = xmlSyntaxChecker.check(src);

        return src;
    }
    
    public static String removeMiscTags(String src){
        String result= src;
        String expression = "<script.*?</script>";
        result = result.replaceAll(expression, "");

        expression = "<!--.*?-->";
        result = result.replaceAll(expression, "");

        expression = "&nbsp;?";
        result = result.replaceAll(expression, "");

        return result;
    }
    
    private static List<char[]> getListChar(String input){
        ArrayList<char[]> listChar = new ArrayList<>();
        for(int i = 0; i<input.length()-1; i++){
            char[] chars = new char[2];
            chars[0] = input.charAt(i);
            chars[1] = input.charAt(i + 1);
            listChar.add(chars);
        }
        return listChar;
    }
    public static double getMatchingPercentage(String string1, String string2) {
        List<char[]> listChar1 = getListChar(string1);
        List<char[]> listChar2 = getListChar(string2);
        
        List<char[]> copy = new ArrayList<>(listChar2);
        int matches = 0;
        for (int i = listChar1.size(); --i >= 0;) {
            char[] listchar = listChar1.get(i);
            for (int j = copy.size(); --j >= 0;) {
                char[] toMatch = copy.get(j);
                if (listchar[0] == toMatch[0] && listchar[1] == toMatch[1]) {
                    copy.remove(j);
                    matches += 2;
                    break;
                }
            }
        }
        return (double) matches / (listChar1.size() + listChar2.size());
    }
    public static synchronized String getUUID(){
        return UUID.randomUUID().toString();
    }
}
