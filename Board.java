import java.net.SocketPermission;
import java.util.ArrayList;
import java.util.Random;

public class Board {
    //private ArrayList<Cell> cells;
    private int[] mode;//mode{row, column, mine}
    private Cell[][] cels;

    public Board() {
        //cells = new ArrayList<Cell>();
        mode = new int[3];
    }

    public void chooseDifficulty(String easyORnormalORhard){
        if(easyORnormalORhard.equals("easy")){
            mode[0] = 8;
            mode[1] = 8;
            mode[2] = 10;
            cels = new Cell[10][10];
        }
        else if(easyORnormalORhard.equals("normal")){
            mode[0] = 16;
            mode[1] = 16;
            mode[2] = 40;
            cels = new Cell[18][18];
        }
        else if(easyORnormalORhard.equals("hard")){
            mode[0] = 16;
            mode[1] = 30;
            mode[2] = 99;
            cels = new Cell[18][32];
        }
        else{
            //print error message "please choose "easy" or "normal" or "hard". "
        }
        //first build an empty board without mine
        for(int i = 0; i < mode[0] + 2; i++){
            for(int j = 0; j < mode[1] + 2; j++) {
                cels[i][j] = new UnminedCell(1);
            }
        }
        displayTheNewBoard();
        //duplicated cels initialization can be added here.

    }

    /*public int generateSerialNumber(int row, int column){
        //serialNumber is the index of ArrayList rather than realistic index
        return (row - 1) * mode[0] + column - 1;
    }*/

    public void generateBoard(int rowC, int colC){
        //generate mines outside 9 cells and outside existed mines
        for(int i = 0; i < mode[2]; i++){

            //generate a random position
            Random random = new Random();
            int row = random.nextInt(mode[0]) + 1;
            int column = random.nextInt(mode[1]) + 1;

            //check if random position is qualified to fill in a mine
            while(isMined(row, column)||ifIn9Cells(rowC, colC, row, column)){
                row = random.nextInt(mode[0]) + 1;
                column = random.nextInt(mode[1]) + 1;
            }


            //fill in a mine
            cels[row][column] = new MinedCell();
        }

        //generate numbers of other cells
        int number = 0;
        for(int i = 1; i < mode[0] + 1; i++){
            for(int j = 1; j < mode[1] + 1; j++){
                if(!(cels[i][j] instanceof MinedCell)){//not a mined cell
                    /*if((i * j == 0) || (i == mode[0]) || (j == mode[1])){//is a peripheral cell

                    }*/
                    for(int ii = i - 1; ii < i + 2; ii++){//count the number of mines
                        for(int jj = j - 1; jj < j + 2; jj++){
                            if((ii != i) || (jj != j)){//jump over the middle one
                                if(cels[ii][jj] instanceof MinedCell) {
                                    number++;
                                }
                            }
                        }
                    }
                    cels[i][j] = new UnminedCell(number);
                    number = 0;
                }
            }
        }
        leftClick(rowC, colC);

    }

    private boolean isMined(int rowC, int colC){
        if(cels[rowC][colC] instanceof MinedCell){
            return true;
        }
        return false;
    }

    private boolean ifIn9Cells(int rowC, int colC, int row, int column){
        if(row < (rowC - 1) || row > (rowC + 1))
        {
            if(column < (colC - 1) || column > (colC + 1))
            {
                return false;
            }
        }
        return true;
    }

    public void displayTheNewBoard(){
        for(int i = 1; i < mode[0] + 1; i++){
            for(int j = 1; j < mode[1] + 1; j++) {
                if(cels[i][j].isRevealed()){
                    if(cels[i][j] instanceof UnminedCell){
                        if(cels[i][j].getNumber() == 0){
                            System.out.print("_ ");
                        }
                        else{
                            System.out.print(cels[i][j].getNumber() + " ");
                        }
                    }
                    else{
                        System.out.print("M ");
                    }
                }
                else{
                    if(cels[i][j].hasFlag()){
                        System.out.print("F ");
                    }
                    else{
                        System.out.print("x ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void leftClick(int row, int column){
        if(cels[row][column].isRevealed() || cels[row][column].hasFlag()){
            //System.out.println("Are you kidding me?");
        }
        else{
            cels[row][column].reveal();
            if(cels[row][column] instanceof MinedCell){
                gameover();
            }
            if(cels[row][column].getNumber() == 0){
                for(int ii = row - 1; ii < row + 2; ii++){//count the number of mines
                    for(int jj = column - 1; jj < column + 2; jj++){
                        if((ii != row) || (jj != column)){//jump over the middle one
                            leftClick(ii, jj);
                        }
                    }
                }
            }
            displayTheNewBoard();
        }
    }

    private void gameover(){
        for(int i = 1; i < mode[0] + 1; i++) {
            for (int j = 1; j < mode[1] + 1; j++) {
                if(cels[i][j] instanceof MinedCell){
                    cels[i][j].reveal();
                }
            }
        }
        displayTheNewBoard();
        System.out.println("Game Over!");
    }

    public void rightClick(int row, int column){
        if(cels[row][column].isRevealed()){
            System.out.println("Are you kidding me?");
        }
        else{
            if(cels[row][column].hasFlag()){
                cels[row][column].removeFlag();
            }
            else{
                cels[row][column].addFlag();
            }
        }
        displayTheNewBoard();
    }
}
