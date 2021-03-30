import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class ElectricCar{
    static int rows;
    static int columns;
    static int energy;
    static char[][] cityMap;
    public static void main(String args[]){
        cityMap = loadFile();
        printMap();
    }
    public static void printMap(){
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++){
                System.out.print(cityMap[i][j]);
            }
            System.out.println();
        }
    }
    public static char[][] loadFile(){
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("maps/CityA.txt"));
            rows = Integer.parseInt(br.readLine());
            columns = Integer.parseInt(br.readLine());
            energy = Integer.parseInt(br.readLine());
            cityMap = new char[columns][rows];
            String line = br.readLine();
            for(int i = 0; i < columns; i++){
                for(int j = 0; j < rows; j++){
                    cityMap[i][j] = line.charAt(j);
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return cityMap;
    } 
}