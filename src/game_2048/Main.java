package game_2048;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();
	    while (game.isGameOver() == false)
	    {
	        game.printGrid();
			System.out.println("Score: " + game.getScore());
			System.out.println("Enter move (a: left, d: right, w: up, s: down): ");
			
			Scanner input_move = new Scanner(System.in);
	        String move = input_move.nextLine();
	        switch (move)
	        {
	        case "a":
	            game.moveLeft();
	            break;
	        case "d":
	            game.moveRight();
	            break;
	        case "w":
	            game.moveUp();
	            break;
	        case "s":
	            game.moveDown();
	            break;
	        default:
				System.out.println("Invalid input!");
	            break;
	        }
	    }
		System.out.println("Game Over !");
		

	}

}
