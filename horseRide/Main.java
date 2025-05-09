import java.util.ArrayList;
import java.util.Arrays;
import util.Scan;

public class Main {
    public static void main(String...args){
        start(9, 9);
    }

    public static void start(int x, int y){
        Table t = new Table(x, y);
        Horse.setLoc(x/2, y/2);

        System.out.println("---------------------------------");
        System.out.println("HORSE RIDE");
        System.out.println("---------------------------------");


        while(true) t.print();

//        while(true){
//            t.print();
//        }
    }

    public static class Table {
        public String[][] matrix;
        public final int width;
        public final int height;

        public Table(int x, int y){
            this.matrix = new String[x][y];
            this.width = x;
            this.height = y;

            for(String[]line:this.matrix){
                Arrays.fill(line, "[ ]");
            }
        }

        public void print(){
//            refreshTable();
//            int[] possibleMoves = refreshTable();
            ArrayList<Integer> possibleMoves = refreshTable();
//            Arrays.fill(possibleMoves, -1);

            System.out.println();
            for(String[] line:this.matrix){
                for(String i:line){
                    System.out.print(i);
                }
                System.out.println();
            }
            displayMoves(possibleMoves);
        }


        public void displayMoves(ArrayList<Integer> possibleMovesList){
            System.out.print("\nCHANGE MOVE:\n");

            int i = 1;
            int listSize = possibleMovesList.size();

            System.out.print("[");
            for(int move:possibleMovesList){
                if(move!=0){
                    if(i < listSize){
                        System.out.print(move + ", ");
                        i++;
                        continue;
                    }
                    System.out.print(move);
                }
            }
            System.out.print("]\n");

            int change = Scan.nextInt();
            if(!(possibleMovesList.contains(change))) {
                System.out.println("ILEGAL MOVEMENT!");
                System.out.println("---------------------------------");
                return;
            }
            Horse.move(change, this);
        }

        public ArrayList<Integer> refreshTable(){
            Horse.refreshMoveList();
            clean();

//            int[] movesOutput = new int[8];
            ArrayList<Integer> movesOutput = new ArrayList<>();
            int count = 1;
            for(int[] line:Horse.moveList){
//                String currentValue = matrix[line[0]][line[1]];
                boolean isAccepted = line[0]>=0 && line[1]>=0 && line[0]<width && line[1]<height;
                if(isAccepted) {
                    matrix[line[0]][line[1]] = String.format(" %d ", count);

//                    movesOutput[(count-1)]=count;
                    movesOutput.add(count);
                }
                count++;
            }
            matrix[Horse.loc[0]][Horse.loc[1]] = " K ";
            return movesOutput;
        }

        public int[] find(String element){
            int iCount = 0;
            int jCount = 0;
            int[] out = {iCount, jCount};

            for(String[] i:this.matrix){
                jCount = 0;
                out[0] = iCount;

                for(String j:i){
                    out[1] = jCount;
                    if(j.equals(element))return out;
                    jCount++;
                }
                iCount++;
            }
            out[0]=-1;
            out[1]=-1;
            return out;
        }

        public void clean(){
            for(String[]line:this.matrix){
                Arrays.fill(line, "[ ]");
            }
        }
    }
}


//    1    {loc[0]-2, loc[1]+1},
//    2    {loc[0]-1, loc[1]+2},
//    3    {loc[0]+1, loc[1]+2},
//    4    {loc[0]+2, loc[1]+1},
//    5    {loc[0]+2, loc[1]-1},
//    6    {loc[0]+1, loc[1]-2},
//    7    {loc[0]-1, loc[1]-2},
//    8    {loc[0]-2, loc[1]-1},
