import java.util.Scanner;

public class TicTacToeGame
 {
    private static final int BOARD_SIZE = 3;
    private static char[][] board;
    private static char currentPlayerSymbol;
    private static boolean gameOver;
    private static int totalMoves;

    public static void main(String[] args) 
{
        Scanner scanner = new Scanner(System.in);

        
        while (true) 
{
            
            initializeGame();
            printBoard();

            
            while (!gameOver) 
{
                getPlayerMove(scanner);
                printBoard();
                checkGameStatus();
                togglePlayer();
            }

            
            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes"))
 {
                System.out.println("Thanks for playing! Goodbye.");
                break;
            }
        }

        scanner.close();
    }

    
    private static void initializeGame()
 {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        currentPlayerSymbol = 'X';
        gameOver = false;
        totalMoves = 0;

        
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++)

 {
                board[i][j] = ' ';
            }
        }
    }

    
    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < BOARD_SIZE; j++) 
{
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    
    private static void getPlayerMove(Scanner scanner) {
        System.out.print("Player " + currentPlayerSymbol + ", enter your move (row[1-3] column[1-3]): ");
        int row = scanner.nextInt() - 1;
        int col = scanner.nextInt() - 1;

        
        if (isValidMove(row, col))
 {
            board[row][col] = currentPlayerSymbol;
            totalMoves++;
        }
 else
 {
            System.out.println("Invalid move! Please try again.");
            getPlayerMove(scanner); // Recursively call until a valid move is made
        }
    }

   
    private static boolean isValidMove(int row, int col) {
        return (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == ' ');
    }

    
    private static void checkGameStatus() 
{
        
        if (checkForWin()) 
{
            System.out.println("Player " + currentPlayerSymbol + " wins!");
            gameOver = true;
        }
        
        else if (totalMoves == BOARD_SIZE * BOARD_SIZE) 
{
            System.out.println("It's a draw!");
            gameOver = true;
        }
    }

    
    private static boolean checkForWin()
 {
       
        for (int i = 0; i < BOARD_SIZE; i++) 
{
            if (board[i][0] == currentPlayerSymbol && board[i][1] == currentPlayerSymbol && board[i][2] == currentPlayerSymbol)
                return true;
            if (board[0][i] == currentPlayerSymbol && board[1][i] == currentPlayerSymbol && board[2][i] == currentPlayerSymbol)
                return true;
        }

        
        if (board[0][0] == currentPlayerSymbol && board[1][1] == currentPlayerSymbol && board[2][2] == currentPlayerSymbol)
            return true;
        if (board[0][2] == currentPlayerSymbol && board[1][1] == currentPlayerSymbol && board[2][0] == currentPlayerSymbol)
            return true;

        return false;
    }

   
    private static void togglePlayer() 
{
        currentPlayerSymbol = (currentPlayerSymbol == 'X') ? 'O' : 'X';
    }
}
