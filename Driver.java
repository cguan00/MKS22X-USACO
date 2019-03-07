import java.util.*;
import java.io.*;

public class Driver{
    public static void main(String[]args){
      USACO file1 = new USACO();
      try{
        // USACO.bronze("makelake.1.in");
        USACO.bronze("makelake.2.in");
        // USACO.bronze("makelake.3.in");
        // USACO.bronze("makelake.4.in");
        // USACO.bronze("makelake.5.in");
      }catch(FileNotFoundException e){
        System.out.println("file not found");
      }

    }
  }
