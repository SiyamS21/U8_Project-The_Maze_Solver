import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String[][] maze = Main.getMaze("src/maze");
        Main.solvePath(maze, 0, 0, "");
    }

    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        String[][] maze = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;

    }

    public static boolean solvePath(String[][] maze, int x, int y, String mostRecent) {
        int currentRow = x;
        int currentColumn = y;
        String mostRecentMove = mostRecent;
        boolean up = true, down = true, left = true, right = true;
        String endMessage = "(0, 0) ---> ";
        while (currentRow != maze.length - 1 && currentColumn != maze[0].length - 1) {
            up = true;
            down = true;
            left = true;
            right = true;
            if (mostRecentMove.equals("up")) {
                down = false;
            }
            else if (mostRecentMove.equals("down")) {
                up = false;
            }
            else if (mostRecentMove.equals("left")) {
                right = false;
            }
            else if (mostRecentMove.equals("right")) {
                left = false;
            }
            if (currentRow == 0) {
                up = false;
            }
            if (currentRow == maze.length - 1) {
                down = false;
            }
            if (currentColumn == 0) {
                left = false;
            }
            if (currentColumn == maze[0].length - 1) {
                right = false;
            }
            if (up && maze[currentRow--][currentColumn].equals(".")) {
                currentRow--;
                mostRecentMove = "up";
                System.out.println("up");
            }
            else if (down && maze[currentRow++][currentColumn].equals(".")) {
                currentRow++;
                mostRecentMove = "down";
                System.out.println("down");
            }
            else if (left && maze[currentRow][currentColumn--].equals(".")) {
                currentColumn--;
                mostRecentMove = "left";
                System.out.println("left");
            }
            else if (right && maze[currentRow][currentColumn++].equals(".")) {
                currentColumn++;
                mostRecentMove = "right";
                System.out.println("right");
            }
            endMessage = endMessage + "(" + currentRow + ", " + currentColumn + ") ---> ";
        }
        System.out.println(endMessage);
        return true;
    }


    public static boolean solvePathPart1(String[][] maze, int x, int y, String mostRecent) {
        int currentRow = x;
        int currentColumn = y;
        String mostRecentMove = mostRecent;
        String endMessage = "(0, 0) ---> ";
        while (currentRow != maze.length - 1 && currentColumn != maze[0].length - 1) {
            boolean up = true, down = true, left = true, right = true;
            if (mostRecentMove.equals("up")) {
                up = false;
            }
            else if (mostRecentMove.equals("down")) {
                down = false;
            }
            else if (mostRecentMove.equals("left")) {
                left = false;
            }
            else if (mostRecentMove.equals("right")) {
                right = false;
            }
            if (currentRow == 0) {
                up = false;
            }
            if (currentRow == maze.length - 1) {
                down = false;
            }
            if (currentColumn == 0) {
                left = false;
            }
            if (currentColumn == maze[0].length) {
                right = false;
            }
            int count = 0;
            if (up) {
                count++;
                System.out.println("up");
            }
            if (down) {
                count++;
                System.out.println("down");
            }
            if (left) {
                count++;
                System.out.println("left");
            }
            if (right) {
                count++;
                System.out.println("right");
            }
            System.out.println(count);
            if (count == 1) {
                if (up) {
                    currentRow--;
                }
                else if (down) {
                    currentRow++;
                }
                else if (left) {
                    currentColumn--;
                }
                else if (right) {
                    currentRow++;
                }
            }
            endMessage = endMessage + "(" + currentRow + ", " + currentColumn + ") ---> ";
        }
        System.out.println(endMessage);
        return true;
    }
    public static boolean solvePathPart1Version2(String[][] maze) {
        int currentRow = 0;
        int currentColumn = 0;
        while (currentRow != maze.length - 1 && currentColumn != maze[0].length) {
            if (maze[currentRow + 1][currentColumn].equals(".")) {
                currentRow++;
            }
            else if (maze[currentRow][currentColumn + 1].equals(".")) {
                currentColumn++;
            }
        }
        return true;
    }
}