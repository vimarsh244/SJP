import java.io.*;//required package

/**
 * 
 * Code By:
 * Vimarsh A Shah
 * Class: XB
 * Roll No.: 24
 * Zydus School for Excellence
 * 
 *                                      Computer Practical Project
 *                                      
 * Algorithm is as follows:
 * 
 * Computer checks if the move by user is valid or not
 * Checks if player or it wins or is it a draw match
 * Computer checks if it can win 
 * Algorithm checks if user is winning
 * checks for good available move
 * does a random move if not getting any
 * 
 * X: User
 * O: Computer
 * 
 */

public class Tic_Tac_Toe_Game
{   
    static char[][] board=new char[3][3]; //2D array to store the moves
    //declared static so that function can get the last updated value

    static InputStreamReader isr=new InputStreamReader(System.in);
    static BufferedReader br=new BufferedReader(isr);
    //gloabally declared input class

    public static void main()throws IOException
    {
        System.out.println("\t\t\t Tic Tac Toe Game \t\t\t");
        System.out.println("\f\n\t\t\t \'X\' for Player and \'O\' for computer.");
        System.out.println("Instructions:\n   1. Enter move as [x][y].     \n   2. Where x and y are from 1-3.     \n   3. Try to not let computer win.");
        newBoard();//initialization
        printBoard();//function to print latest board status

        String gameStatus = "\f...";             
        while(true) //runs until game comes to an end
        {
            String userMove = getUserMove();
            markMoveOnBoard('X', userMove);         
            gameStatus = getGameStatus(board);
            if (!gameStatus.equals("...")) 
            {               
                break;
            }
            String computerMove = getComputerMove();            
            System.out.println("computer move:"+ computerMove);
            markMoveOnBoard('O', computerMove);         
            gameStatus = getGameStatus(board);
            if (!gameStatus.equals("...")) 
            {               
                break;
            }
        }

        System.out.println(gameStatus);
    }

    //initializes the board variable with blank values
    public static void newBoard() {
        for (int r = 0; r<=2; r++)
        {
            for (int c = 0; c<=2; c++)
            {
                board[r][c] = '-';
            }
        }
    }

    //prints last status of the board
    public static void printBoard()
    {
        for(int r=0;r<=2;r++)
        {
            System.out.print("\n");
            for(int c=0;c<=2;c++)
            {
                System.out.print(board[r][c]);
                System.out.print("    ");
            }
        }
        System.out.print("\n"+"\n");
    }

    //gets player's move and validates it
    public static String getUserMove()throws IOException
    {
        String usermove = "";
        boolean isValidMove = false;
        while(!isValidMove)
        {
            System.out.println("user move:");
            usermove = br.readLine();
            isValidMove = validateMove('X', usermove);
        }

        return usermove;
    }

    //finds the best possible move for the computer algorithm
    public static String getComputerMove() {        
        char[][] boardcopy = createBoardCopy(board);

        //Choose winning move if available
        for(int row = 0; row <= 2; row++) {
            for(int column = 0; column <= 2; column++) {
                if(boardcopy[row][column] == '-') {
                    boardcopy[row][column] = 'O';
                    if(isWinning('O', boardcopy)) {
                        return ("[" + row + "][" + column + "]");
                    }
                    else {
                        boardcopy[row][column] = '-';
                    }
                }
            }
        }

        //Choose blocking move if available
        for(int row = 0; row <= 2; row++) {
            for(int column = 0; column <= 2; column++) {
                if(boardcopy[row][column] == '-') {
                    boardcopy[row][column] = 'X';
                    if(isWinning('X', boardcopy)) {
                        return "[" + row + "][" + column + "]";
                    }
                    else {
                        boardcopy[row][column] = '-';
                    }
                }
            }
        }

        //Choose center if available
        if(boardcopy[1][1] == '-') {
            return "[1][1]";
        }

        //Choose a corner if available 
        if(boardcopy[0][0] == '-') {
            return "[0][0]";
        }   

        if(boardcopy[0][2] == '-') {
            return "[0][2]";
        }

        if(boardcopy[2][0] == '-') {
            return "[2][0]";
        }

        if(boardcopy[2][2] == '-') {
            return "[2][2]";
        }

        //Choose a random move
        for(int row = 0; row <= 2; row++) {
            for(int column = 0; column <= 2; column++) {
                if(boardcopy[row][column] == '-') {
                    return "[" + row + "][" + column + "]";
                }
            }
        }
        return "no move found";
    }

    //validates player's as well as computer's move
    public static boolean validateMove(char player, String move)
    {
        boolean isValidMove = false;
        int row = Character.getNumericValue(move.charAt(1));
        int column = Character.getNumericValue(move.charAt(4));

        //take input from user as 1-3,1-3 but for computer it has to be registered from 0-2,0-2
        if(player=='X'){
            row--;
            column--;
        }

        if(board[row][column] == '-')
        {
            isValidMove = true;
        }
        else
        {
            System.out.println("Invalid Move");
        }

        return isValidMove;
    }   

    //changes value on global board variable as per calculations
    public static void markMoveOnBoard(char player, String move)
    {
        int row = Character.getNumericValue(move.charAt(1));
        int column = Character.getNumericValue(move.charAt(4));

        //take input from user as 1-3,1-3 but for computer it has to be registered from 0-2,0-2
        if(player=='X'){
            row--;
            column--;
        }

        board[row][column] = player;    
        printBoard();
    }

    //just check if user or computer is winning. If not, check for draw. If nothing, continue...
    public static String getGameStatus(char[][] board)
    {
        boolean userWins = isWinning('X', board);
        if(userWins)
        {
            return "User Wins!";
        }
        boolean computerWins = isWinning('O', board);
        if(computerWins) {
            return "Computer Wins!";
        }
        if (isDraw())
        {
            return "Draw";
        }
        return "...";
    }

    //checks who is winning
    public static boolean isWinning(char player, char[][] inputboard) {
        //if player/computer winning in a row
        for(int r = 0; r <= 2; r++)
        {
            if(inputboard[r][0]==player && inputboard[r][1]==player && inputboard[r][2]==player)
            {
                return true;
            }
        }

        //if player/computer winning in column
        for(int c = 0; c <= 2; c++)
        {
            if(inputboard[0][c]==player && inputboard[1][c]==player && inputboard[2][c]==player)
            {
                return true;
            }
        }

        //if player/computer winning in diagonal
        if (inputboard[0][0]==player && inputboard[1][1]==player && inputboard[2][2]==player)
        {
            return true;
        }
        if (inputboard[0][2]==player && inputboard[1][1]==player && inputboard[2][0]==player)
        {
            return true;
        }
        return false;
    }

    public static boolean isDraw() {
        for (int r = 0; r<=2; r++)
        {
            for (int c = 0; c<=2; c++)
            {
                if(board[r][c] =='-')
                {
                    return false;
                }
            }
        }
        //if all spaces full and no one is winning return as Draw
        return true;    
    }

    //this method creates a copy of the board(array) to perform functions
    public static char[][] createBoardCopy(char[][] board) {
        char[][] boardcopy = new char[3][3];
        System.out.println("\f");
        for(int row = 0; row <= 2; row++) {
            for(int column = 0; column <= 2; column++) {
                boardcopy[row][column] = board[row][column];
            }
        }
        return boardcopy;
    }
}