class Main{
    static boolean[][] board;
    static int queencount;
    public static void main(String[] args)
    {
        board = new boolean[8][8];
        queencount = 0;
        if (populateBoard()) printBoard();
    }

    public static boolean populateBoard()
    {
        if (queencount == 8) return true;
        //Iterate over all possible field to find the first free one where no captures occur
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                //when checkCapture returns false the spot is available
                if(!checkCapture(i,j)){
                    board[i][j] = true;
                    ++queencount;
                    //When trying to populate the board fails for the next queen the current position of the queen will be set to the next possible one
                    if(!populateBoard()) {
                        board[i][j] = false;
                        --queencount;
                    }else{
                        return true;
                    }
                }
            }
        }
        return false;
    }
                                           
    public static boolean checkCapture(int i, int j)
    {
        //check for straign line matches
        for (int k = 0; k < 8; k++){
            if (k == i) {
                for (int l = 0; l < 8; l++){
                    if(board[k][l]) return true;
                }
            }else {
                if(board[k][j]) return true;
            }
        }
        //check for diagonal matches
        for (int k = 1; k < 8; k ++){
            //case 1 + + 
            if(i + k < 8 && j + k < 8 && board[i+k][j+k]) return true;
            //case 2 + -
            if(i + k < 8 && j - k >= 0 && board[i+k][j-k]) return true;
            //case 3 - + 
            if(i - k >= 0 && j + k < 8 && board[i-k][j+k]) return true;
            //case 4 - - 
            if(i - k >= 0 && j - k >= 0 && board[i-k][j-k]) return true;
        }
        return false;
    }


    public static void printBoard()
    {
        System.out.println("****************");
        for(int j = 0; j < 8; j++){
            for(int i = 0; i < 8; i++){
                if (board[i][j])
                    System.out.print("+ ");
                else
                    System.out.print("- ");
            }
            System.out.println("");
        }
        System.out.println("****************");
    }
}