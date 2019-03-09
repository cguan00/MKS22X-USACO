import java.util.*;
import java.io.*;

public class USACO{
  private static int[][] squares;
  private static char[][] pasture;

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

    //accessing stomp digging instructions
    String[] instructions;
    int R_s;
    int C_s;
    int D_s;
    for(int i = R + 1; i < lines.size(); i++){
      instructions = lines.get(i).split(" ");
      R_s = Integer.parseInt(instructions[0]) - 1;
      C_s = Integer.parseInt(instructions[1]) - 1;
      D_s = Integer.parseInt(instructions[2]);
      stomp(R_s, C_s, D_s);//stomp the ground
    }
    return calculation(E);
  }

  //stomp the land according to the stomp instructions
  public static void stomp(int row, int col, int level){
    int inches = level;
    while(inches != 0){
      int max = 0;
      for(int i = row; i < row + 3; i++){//keep track of the highest elevation
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

  //calculate the volume by multiplying by 72 *72 to find cubic inches
  public static int calculation(int target){
    int aggDepth = 0;
    for(int i = 0; i < squares.length; i++){
      for(int j = 0; j < squares[i].length; j++){
        if(target - squares[i][j] >= 0){
          aggDepth += target - squares[i][j];
        }
      }
    }
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


  public static int silver(String filename) throws FileNotFoundException{
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
    int N = Integer.parseInt(firstLine[0]);
    int M = Integer.parseInt(firstLine[1]);
    int T = Integer.parseInt(firstLine[2]);

    System.out.println(N + " " + M + " " + T);

    //accessing the squares of the land
    pasture = new char[N][M];
    for(int i = 1; i <= N; i++){
      for(int j = 0; j < M; j++){
        pasture[i - 1][j] = lines.get(i).charAt(j);;
      }
    }

    printPasture();

    String[] lastLine = lines.get(lines.size() - 1).split(" ");
    int R1 = Integer.parseInt(lastLine[0]);
    int C1 = Integer.parseInt(lastLine[1]);
    int R2 = Integer.parseInt(lastLine[2]);
    int C2 = Integer.parseInt(lastLine[3]);

    System.out.println(R1 + " " + C1 + " " + R2 + " " + C2 + " ");


    return 0;
  }




  public static void printPasture(){
    String output = "";
    for(int i = 0; i < pasture.length; i++){
      for(int j = 0; j < pasture[i].length; j++){
        output += pasture[i][j];
      }
      output += "\n";
    }
    System.out.println(output);
  }
}
