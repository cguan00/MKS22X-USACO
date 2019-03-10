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

    //splitting the first line by spaces to get each of the four integers N, M, T
    String[] firstLine = lines.get(0).split(" ");
    int N = Integer.parseInt(firstLine[0]);
    int M = Integer.parseInt(firstLine[1]);
    int T = Integer.parseInt(firstLine[2]);

    // System.out.println(N + " " + M + " " + T);

    //accessing the squares of the pasture
    char[][] charPasture = new char[N][M];
    for(int i = 1; i <= N; i++){
      for(int j = 0; j < M; j++){
        charPasture[i - 1][j] = lines.get(i).charAt(j);;
      }
    }

    // printPasture(charPasture);

    int[][] pasture = new int[charPasture.length][charPasture[0].length];
    for(int i = 0; i < charPasture.length; i++){
      for(int j = 0; j < charPasture[i].length; j++){
        if(charPasture[i][j] == '*'){//trees represented by -1
          pasture[i][j] = -1;
        }
      }
    }

    //accessing starting position and ending position. must off set by 1
    String[] lastLine = lines.get(lines.size() - 1).split(" ");
    int R1 = Integer.parseInt(lastLine[0]) - 1;
    int C1 = Integer.parseInt(lastLine[1]) - 1;
    int R2 = Integer.parseInt(lastLine[2]) - 1;
    int C2 = Integer.parseInt(lastLine[3]) - 1;

    // System.out.println(R1 + " " + C1 + " " + R2 + " " + C2 + " ");

    pasture[R1][C1] = 1;
    for(int i = 0; i < T; i++){
      pasture = silverHelper(pasture);
    }

    return pasture[R2][C2];
  }

  private static int[][] silverHelper(int[][] pasture) {
    int[][] moves = {{-1,0},{1,0},{0,-1},{0,1}};
    int[][] newPasture = new int[pasture.length][pasture[0].length];
    for(int r = 0; r < pasture.length; r++) {
      for(int c = 0; c < pasture[r].length; c++){
        if(pasture[r][c] == -1){
          newPasture[r][c] = -1;
        }else {//not a tree
          int sum = 0;
          for(int i = 0; i < moves.length; i++){
            int newR = r + moves[i][0];
            int newC = c + moves[i][1];
            if (!outOfBounds(pasture, newR, newC) && pasture[newR][newC] != -1) {
              sum += pasture[newR][newC];
            }
            newPasture[r][c] = sum;
          }
        }
      }
    }
    return newPasture;
  }



  private static boolean outOfBounds(int[][] pasture, int r, int c) {
    if(r < 0 || c < 0 || r >= pasture.length || c >= pasture[0].length){
      return true;
    }
    return false;
  }


  public static void printPasture(char[][] pasture){
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
