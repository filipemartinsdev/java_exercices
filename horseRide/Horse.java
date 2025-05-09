public class Horse {
    public static void main(String...args){
//        for(int[] i:moveList){
//            System.out.printf("(%d, %d)\n", i[0], i[1]);
//        }
    }

    public static int[] loc = {0, 0};
    public static int currentMoves = 0;

    public static void move(int changedMove, Main.Table t){
        currentMoves++;

        int[] change = t.find(String.format(" %d ", changedMove));

//        loc[0] = change[0];
//        loc[1] = change[1];
        setLoc(change[0], change[1]);
        refreshMoveList();

        System.out.printf("\nHorse moved to position %d (%d, %d)\n",changedMove, change[0], change[1]);
        System.out.println("---------------------------------");
        System.out.println("Current moves: " + currentMoves);
    }

    public static void setLoc(int x, int y){
        loc[0] = x;
        loc[1] = y;
    }

    public static int[][] moveList = new int[8][2];

    public static void refreshMoveList(){
        moveList[0][0] = loc[0]-2;
        moveList[0][1] = loc[1]+1;

        moveList[1][0] = loc[0]-1;
        moveList[1][1] = loc[1]+2;

        moveList[2][0] = loc[0]+1;
        moveList[2][1] = loc[1]+2;

        moveList[3][0] = loc[0]+2;
        moveList[3][1] = loc[1]+1;

        moveList[4][0] = loc[0]+2;
        moveList[4][1] = loc[1]-1;

        moveList[5][0] = loc[0]+1;
        moveList[5][1] = loc[1]-2;

        moveList[6][0] = loc[0]-1;
        moveList[6][1] = loc[1]-2;

        moveList[7][0] = loc[0]-2;
        moveList[7][1] = loc[1]-1;
    }
}


//        {loc[0]-2, loc[1]+1}
//        {loc[0]-1, loc[1]+2}
//        {loc[0]+1, loc[1]+2}
//        {loc[0]+2, loc[1]+1}
//        {loc[0]+2, loc[1]-1}
//        {loc[0]+1, loc[1]-2},
//        {loc[0]-1, loc[1]-2},
//        {loc[0]-2, loc[1]-1},