import java.util.*;
import java.io.*;

public class USACO{
  public static int bronze(String filename) throws FileNotFoundException{
    File text = new File(filename);
    Scanner inf = new Scanner(text);
    ArrayList<String> lines = new ArrayList<String>();

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

    int[][] squares = new int[lines.get(1).length()][];
    for(int i = 1; i < R + 1; i++){
      String[] line = lines.get(i).split(" ");
      System.out.println(line[0]);
      // for(int j = 0; j < line.length; j++){
      //   squares[i][j] = Integer.parseInt(line[j]);
      // }
    }

    String output = "";
    for(int i = 0; i < squares.length; i++){
      for(int j = 0; j < squares[i].length; j++){
        output += squares[i][j];
      }
      output += "\n";
    }

    System.out.println(output);

    return 0;
  }

   public static int silver(String filename){
     return 0;
   }

}
