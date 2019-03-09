import java.util.*;
import java.io.*;

public class USACO{
  private static int[][] squares;

  public static int bronze(String filename) throws FileNotFoundException{
    File text = new File(filename);
    Scanner inf = new Scanner(text);
    ArrayList<String> lines = new ArrayList<String>();

    //reading the file and storing each line in an ArrayList
    while(inf.hasNextLine()){
      String line = inf.nextLine();
      lines.add(line);
    }

    //splitting the first line by spaces to get each of the four integers R, C, E, N
    String[] firstLine = lines.get(0).split(" ");
    int R = Integer.parseInt(firstLine[0]);
    int C = Integer.parseInt(firstLine[1]);
    int E = Integer.parseInt(firstLine[2]);
    int N = Integer.parseInt(firstLine[3]);

    //accessing the squares of the land
    squares = new int[R][C];
    String[] line;
    for(int i = 1; i <= R ; i++){
      line = lines.get(i).split(" ");
      for(int j = 0; j < C; j++){
        squares[i - 1][j] = Integer.parseInt(line[j]);
      }
    }

    //debugging by printing out the squares
    String output = "";
    for(int i = 0; i < R; i++){
      for(int j = 0; j < C; j++){
        output += squares[i][j] + " ";
      }
      output += "\n";
    }
    System.out.println(output);

    //accessing stomp digging instructions
    String[] instructions;
    int R_s;
    int C_s;
    int D_s;
    for(int i = R + 1; i < lines.size(); i++){
      // System.out.println(lines.get(i));
      instructions = lines.get(i).split(" ");
      // for(int j = 0; j < instructions.length; j++){
      //   System.out.println(instructions[j]);
      // }
      R_s = Integer.parseInt(instructions[0]) - 1;
      C_s = Integer.parseInt(instructions[1]) - 1;
      D_s = Integer.parseInt(instructions[2]);
      // System.out.println(R_s + " " + C_s + " " + D_s);
      stomp(R_s, C_s, D_s);
      // printSquares();
    }
    return calculation(E);
  }

  public static void stomp(int row, int col, int level){
    int inches = level;
    while(inches != 0){
      int max = 0;
      for(int i = row; i < row + 3; i++){
        for(int j = col; j < col + 3; j++){
          if(squares[i][j] >= max){
            max = squares[i][j];
          }
        }
      }
      for(int i = row; i < row + 3; i++){
        for(int j = col; j < col + 3; j++){
          if(squares[i][j] >= max){
            squares[i][j] -= 1;
          }
        }
      }
      inches--;
    }
  }

  public static int calculation(int target){
    int aggDepth = 0;
    for(int i = 0; i < squares.length; i++){
      for(int j = 0; j < squares[i].length; j++){
        if(target - squares[i][j] >= 0){
          aggDepth += target - squares[i][j];
        }
      }
    }
    // System.out.println(aggDepth);
    // System.out.println(aggDepth * 72 * 72);
    return aggDepth * 72 * 72;
  }

  public static void printSquares(){
    String output = "";
    for(int i = 0; i < squares.length; i++){
      for(int j = 0; j < squares[i].length; j++){
        output += squares[i][j] + " ";
      }
      output += "\n";
    }
    System.out.println(output);
  }

  public static int silver(String filename){
    return 0;
  }
}
