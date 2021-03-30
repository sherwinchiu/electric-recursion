import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;

public class ElectricCar{
    static int rows;
    static int columns;
    static int energy;
    static char[][] cityMap;
    static boolean[][] visited;
    static int[] start= {0, 0};
    static int[] end = {0, 0};
    public static void main(String args[]){
        cityMap = loadFile();
        visited = fillArray();
        System.out.println(processPath(energy, start[0], start[1], cityMap, visited));
    }
    public static int processPath(int battery, int x, int y, char[][] map, boolean[][] visited){
        if (map[y][x] == 'D'){
            return battery;
        } else if (battery == 0){
            return 0;
        } else{
            int max = 0;
            // top
            if((y > 0) && map[y-1][x] != '#' && visited[y-1][x]){
                visited[y][x] = false;
                int batteryTop = processPath(battery-1, x, y-1, map, visited);
                if(max < batteryTop){
                    max = batteryTop;
                }
            }
            //left
            if((x > 0) && map[y][x-1] != '#' && visited[y][x-1]){
                visited[y][x] = false;
                int batteryLeft = processPath(battery-1, x-1, y, map, visited);
                if(max < batteryLeft){
                    max = batteryLeft;
                }
            }
            //down
            if((y < rows) && map[y+1][x] != '#' && visited[y+1][x]){
                visited[y][x] = false;
                int batteryDown = processPath(battery-1, x, y+1, map, visited);
                if(max < batteryDown){
                    max = batteryDown;
                }
            }
            //right
            if((x < columns) && map[y][x+1] != '#' && visited[y][x+1]){
                visited[y][x] = false;
                int batteryRight =  processPath(battery-1, x+1, y, map, visited);
                if(max < batteryRight){
                    max = batteryRight;
                }
            }
            return max;
        }
    }
    public static boolean[][] fillArray(){
        visited = new boolean[columns][rows];
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++){
                visited[i][j] = true;
            }
        }
        return visited;
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
        BufferedReader in = null;
        try{
            in = new BufferedReader(new InputStreamReader(System.in));
            String input = in.readLine();
            br = new BufferedReader(new FileReader(input));
            in.close();
            rows = Integer.parseInt(br.readLine());
            columns = Integer.parseInt(br.readLine());
            energy = Integer.parseInt(br.readLine());
            cityMap = new char[columns][rows];
            String line = br.readLine();
            for(int i = 0; i < columns; i++){
                for(int j = 0; j < rows; j++){
                    cityMap[i][j] = line.charAt(j);
                    if (line.charAt(j) == 'S'){
                        start[0] = j;
                        start[1] = i;
                    } else if (line.charAt(j) == 'D'){
                        end[0] = j;
                        end[1] = i;
                    }
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