import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class Main {
    private int sum;
    private File input;
    private Scanner scanner;
    private String s1;
    private String s2;
    private int n1;
    private int n2;
    private static HashMap<String, Integer> numStrings = new HashMap<String, Integer>();

    public static void main(String... args) {
        numStrings.put("one", 1);
        numStrings.put("two", 2);
        numStrings.put("three", 3);
        numStrings.put("four", 4);
        numStrings.put("five", 5);
        numStrings.put("six", 6);
        numStrings.put("seven", 7);
        numStrings.put("eight", 8);
        numStrings.put("nine", 9);
        Main main = new Main();
        System.out.println(main.Calibrate());
    }

    private boolean isStringDigit(String s) {
        for ( String key : numStrings.keySet() ) {
            if ( s.equals(key) ) {
                return true;
            }
        }
        return false;
    }

    private int Calibrate(){
        sum = 0;
        input = new File("input.txt");
        try {
            scanner = new Scanner(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            boolean found1 = false;
            boolean found2 = false;
            int lineLen = currentLine.length();
            for( int i = 0, j = lineLen - 1; !( found1 && found2 ); i++, j-- ) {
                if ( !found1 ) {
                    if( Character.isDigit( currentLine.charAt(i)) ) {
                        n1 = Character.getNumericValue(currentLine.charAt(i));
                        found1 = true;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        for( int k = i; k < lineLen; k++ ) {
                            sb.append(currentLine.charAt(k));
                            if( isStringDigit(sb.toString()) ) {
                                n1 = numStrings.get(sb.toString());
                                found1 = true;
                            }
                        }
                    }
                }
                if ( !found2 ) {
                    if( Character.isDigit( currentLine.charAt(j)) ) {
                        n2 = Character.getNumericValue(currentLine.charAt(j));
                        found2 = true;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        for( int k = j; k < lineLen; k++ ) {
                            sb.append(currentLine.charAt(k));
                            if( isStringDigit(sb.toString()) ) {
                                n2 = numStrings.get(sb.toString());
                                found2 = true;
                            }
                        }
                    }
                }

                //System.out.println("Current line:" + currentLine + ", i: " + i + ", j: " + j +
                   //     ", LineLen: " + lineLen + ", n1: " + n1 + ", n2: " + n2);
            }
            String s1, s2;
            if( !found1 ) {
                s1 = "";
            } else {
                    s1 = Integer.toString(n1);
            }
            if( !found2 ) {
                s2 = "";
            } else {
                    s2 = Integer.toString(n2);
            }
            String concatString = s1 + s2;
            int value = 0;
            if( !concatString.isEmpty() ) {
                value = Integer.parseInt(concatString);
            }
            sum += value;
            System.out.println("Current line:" + currentLine + ", concatString: " + concatString + ", value: " + value +
                                ", sum: " + sum);
        }

        return sum;
    }
}
