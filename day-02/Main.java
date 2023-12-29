import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private final int[] gameConfiguration = {12, 13, 14};
    public static void main(String... args) {
        Main main = new Main();
        int sum = main.countSum("input.txt", main.gameConfiguration);
        System.out.println(sum);
    }

    public int countSum(String fileName, int[] config) {
        int sum = 0;
        int powerSum = 0;
        File input = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(input);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        while(s.hasNextLine()) {
            String currentLine = s.nextLine();
            boolean isResultFeasible = true;
            int colonIndex = currentLine.indexOf(":");
            int gameId = Integer.parseInt(currentLine.substring(0, colonIndex).replace("Game ", ""));
            int minRed = 0;
            int minGreen = 0;
            int minBlue = 0;

            String gameResults = currentLine.substring(colonIndex + 1, currentLine.length());
            for (String result : gameResults.split(";")) {
                for(String cubes : result.split(",")) {
                    cubes = cubes.trim();
                    String[] cubeSplit = cubes.split("\\s");
                    System.out.println(cubeSplit[0]);
                    System.out.println(cubeSplit[1]);
                    int cubeNumber = Integer.parseInt(cubeSplit[0]);
                    switch(cubeSplit[1]) {
                        case "red":
                            if(cubeNumber > config[0]) {
                                isResultFeasible = false;
                            }
                            if(cubeNumber > minRed) {
                                minRed = cubeNumber;
                            }
                            break;
                        case "green":
                            if(cubeNumber > config[1]) {
                                isResultFeasible = false;
                            }
                            if(cubeNumber > minGreen) {
                                minGreen = cubeNumber;
                            }
                            break;
                        case "blue":
                            if(cubeNumber > config[2]) {
                                isResultFeasible = false;
                            }
                            if(cubeNumber > minBlue) {
                                minBlue = cubeNumber;
                            }
                            break;
                    }
                }
            }
            if(isResultFeasible) {
                sum += gameId;
            }
            powerSum += (minRed * minGreen * minBlue);
        }

        return powerSum;
    }

}