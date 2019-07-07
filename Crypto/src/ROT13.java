package src;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.lang.Character.*;

public class ROT13  {

    private static String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static  String lowerCase = "abcdefghijklmnopqrstuvwxyz";

    private String alphabetWhole;
    private String keyWhole;


    ROT13(Character cs, Character cf) {

        cs = Character.toUpperCase(cs);
        cf = Character.toLowerCase(cf);
        String upperROTOnce = rotate(upperCase, cs);
        String lowerROTOnce = rotate(lowerCase, Character.toLowerCase(cs));

        alphabetWhole = upperROTOnce + " " + lowerROTOnce;
        String upperROTTwice = rotate(upperROTOnce, cf);
        String lowerROTTwice = rotate(lowerROTOnce, Character.toLowerCase(cf));
        keyWhole = upperROTTwice + " " + lowerROTTwice;
    }

    ROT13() {
        keyWhole = "NOPQRSTUVWXYZABCDEFGHIJKLM nopqrstuvwxyzabcdefghijklm";

    }


    public String crypt(String text) throws UnsupportedOperationException {

        String crypted ="";
        for(int i = 0; i< text.length(); i++) {
            Character c = text.charAt(i);
            if(isAlphabetical(c)) {
                int index = alphabetWhole.indexOf(c);
                crypted += keyWhole.charAt(index);
            }
            else {
                crypted +=c;
            }
        }
        return crypted;
    }

    public String encrypt(String text) {

        return crypt(text);
    }

    public String decrypt(String text) {
        String decrypted ="";
        for (int i=0; i<text.length(); i++) {
            Character c = text.charAt(i);
            if(isAlphabetical(c)) {
                int index = keyWhole.indexOf(c);
                decrypted += alphabetWhole.charAt(index);
            }
            else{
                decrypted += c;
            }
        }
        return decrypted;
    }

    public static String rotate(String s, Character c) {

        int indexOfChar = s.indexOf(c);
        String substr = s.substring(0, indexOfChar);
        String subStr2 = s.substring(indexOfChar);

        return subStr2.concat(substr);
    }

    public static boolean isAlphabetical(Character c) {
        return c > 64 && c < 91 || c > 96 && c < 123;
    }

    public void encryptFile(String fileName, String newFileName) throws IOException {
        Scanner in = new Scanner(Paths.get(fileName), "UTF-8");

        PrintWriter out = new PrintWriter(newFileName, "UTF-8");
        while (in.hasNextLine()) {
            String line = in.nextLine();
            out.println(encrypt(line));
        }
        out.close();
    }

    public static void main(String[] args)throws IOException {
//        ROT13 rot13 = new ROT13('a', 'n');
//        rot13.encryptFile("sonnet18.txt", "sonnet18_encrypted.txt");
//        rot13.encryptFile("sonnet18_encrypted.txt", "sonnet18_encrypt_decrypted.txt");
    }

}
