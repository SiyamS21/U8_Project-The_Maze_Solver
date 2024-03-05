import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String[][] maze = Main.getMaze("src/maze");
        Main.solvePath(maze);
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
        while (currentRow != maze.length - 1 || currentColumn != maze[0].length - 1) {
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
            int count = 0;
            if (up) {
                if (maze[currentRow - 1][currentColumn].equals(".")) {
                    count++;
                }
            }
            if (down) {
                if (maze[currentRow + 1][currentColumn].equals(".")) {
                    count++;
                }
            }
            if (left) {
                if (maze[currentRow][currentColumn - 1].equals(".")) {
                    count++;
                }
            }
            if (right) {
                if (maze[currentRow][currentColumn + 1].equals(".")) {
                    count++;
                }
            }
            if (count == 0) {
                return false;
            }
            if (count == 1) {
                if (up) {
                    currentRow--;
                    mostRecentMove = "up";
                }
                if (down) {
                    currentRow++;
                    mostRecentMove = "down";
                }
                if (left) {
                    currentColumn--;
                    mostRecentMove = "left";
                }
                if (right) {
                    currentColumn++;
                    mostRecentMove = "right";
                }
            }
            else if (count > 1) {
                if (up) {
                    if (!solvePath(maze, currentRow, currentColumn, mostRecentMove)) {
                        if (down) {
                            currentRow++;
                            mostRecentMove = "down";
                        }
                        if (left) {
                            currentColumn--;
                            mostRecentMove = "left";
                        }
                        if (right) {
                            currentColumn++;
                            mostRecentMove = "right";
                        }
                    }
                }
                if (down) {
                    if (!solvePath(maze, currentRow, currentColumn, mostRecentMove)) {
                        if (up) {
                            currentRow--;
                            mostRecentMove = "up";
                        }
                        if (left) {
                            currentColumn--;
                            mostRecentMove = "left";
                        }
                        if (right) {
                            currentColumn++;
                            mostRecentMove = "right";
                        }
                    }
                }
                if (left) {
                    if (!solvePath(maze, currentRow, currentColumn, mostRecentMove)) {
                        if (up) {
                            currentRow--;
                            mostRecentMove = "up";
                        }
                        if (down) {
                            currentRow++;
                            mostRecentMove = "down";
                        }
                        if (right) {
                            currentColumn++;
                            mostRecentMove = "right";
                        }
                    }
                }
                if (right) {
                    if (!solvePath(maze, currentRow, currentColumn, mostRecentMove)) {
                        if (up) {
                            currentRow--;
                            mostRecentMove = "up";
                        }
                        if (down) {
                            currentRow++;
                            mostRecentMove = "down";
                        }
                        if (left) {
                            currentColumn--;
                            mostRecentMove = "left";
                        }
                    }
                }
            }

            if (currentRow != maze.length - 1 || currentColumn != maze[0].length - 1) {
                endMessage = endMessage + "(" + currentRow + ", " + currentColumn + ") ---> ";
            }
            else {
                endMessage = endMessage + "(" + currentRow + ", " + currentColumn + ")";
            }
        }
        System.out.println(endMessage);
        return true;
    }







    // use if no dead ends
    public static boolean solvePath(String[][] maze) {
        int currentRow = 0;
        int currentColumn = 0;
        String mostRecentMove = "";
        boolean up = true, down = true, left = true, right = true;
        String endMessage = "(0, 0) ---> ";
        while (currentRow != maze.length - 1 || currentColumn != maze[0].length - 1) {
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
            if (up) {
                if (maze[currentRow - 1][currentColumn].equals(".")) {
                    currentRow--;
                    mostRecentMove = "up";
                    down = false;
                    left = false;
                    right = false;
                }
            }
            if (down) {
                if (maze[currentRow + 1][currentColumn].equals(".")) {
                    currentRow = currentRow + 1;
                    mostRecentMove = "down";
                    left = false;
                    right = false;
                }
            }
            if (left) {
                if (maze[currentRow][currentColumn - 1].equals(".")) {
                    currentColumn--;
                    mostRecentMove = "left";
                    right = false;
                }
            }
            if (right) {
                if (maze[currentRow][currentColumn + 1].equals(".")) {
                    currentColumn++;
                    mostRecentMove = "right";
                }
            }
            if (currentRow != maze.length - 1 || currentColumn != maze[0].length - 1) {
                endMessage = endMessage + "(" + currentRow + ", " + currentColumn + ") ---> ";
            }
            else {
                endMessage = endMessage + "(" + currentRow + ", " + currentColumn + ")";
            }
        }
        System.out.println(endMessage);
        return true;
    }
}