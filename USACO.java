import java.util.*;
import java.io.*;

public class USACO{
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
    int[][] squares = new int[R][C];
    String[] line;
    for(int i = 1; i <= R ; i++){
      line = lines.get(i).split(" ");
      for(int j = 0; j < C; j++){
        squares[i - 1][j] = Integer.parseInt(line[j]);
      }
    }

    // //debugging by printing out the squares
    // String output = "";
    // for(int i = 0; i < R; i++){
    //   for(int j = 0; j < C; j++){
    //     output += squares[i][j] + ", ";
    //   }
    //   output += "\n";
    // }
    // System.out.println(output);

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
      R_s = Integer.parseInt(instructions[0]);
      C_s = Integer.parseInt(instructions[1]);
      D_s = Integer.parseInt(instructions[2]);
      System.out.println(R_s);
      System.out.println(C_s);
      System.out.println(D_s);
    }

    return 0;
  }

   public static int silver(String filename){
     return 0;
   }

}
