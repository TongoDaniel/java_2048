package game_2048;
import java.util.Random;
import java.util.ArrayList;

public class Game {
	private int score;
	private Random randomNumbers = new Random();
	private boolean gameOver = false;
	// Attribut qui contient ma grille ( tableau 4*4)
	private Tuile[][] grid = new Tuile[4][4];
	
	// Constructeur
	public Game() {
		score =0;
		initializeGrid();
	}
	// Permet de recuperer le score
	public int getScore() {
		return score;
	}
	// Permet de modifier le score
	public void setScore(int newScore) {
		score = newScore;
	}
	// Permet d'initialiser la grille en mettant 2 dans 2 Tuiles aleatoires
	public void initializeGrid() {
		// Permet d'initialiser toutes les tuiles de ma grille à 0
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = new Tuile();
            }
        }
        // Permet de recuperer de maniere aleatoire 2 lignes
	    int[] i = {randomNumbers.nextInt(4) % 4, randomNumbers.nextInt(4) % 4};
        // Permet de recuperer de maniere aleatoire 2 colonnes
	    int[] j = {randomNumbers.nextInt(4) % 4, randomNumbers.nextInt(4) % 4};
	    // Permet de changer les lignes ou les colonnes qui ont ete prises de maniere aleatoire
	    // si elles ont la meme valeur
	    while (i[0] == i[1] && j[0] == j[1])
	    {
	        j[1] = randomNumbers.nextInt(4) % 4;
	        i[0] = randomNumbers.nextInt(4) % 4;
	    }
	    // Permet d'ajouter 2 à 2 Tuiles
	    grid[i[0]][j[0]].setValeur(2);
	    grid[i[1]][j[1]].setValeur(2);
	}
	// Permet de verifier si le player est en situation de gameOver
	public boolean isGameOver() {
		// Permet de verifier s'il y a une possibilite de fusion de maniere horizontale et verticale
	    for (int i = 0; i < 4; i++)
	    {
	        for (int j = 1; j < 4; j++)
	        {
	        	if(grid[i][j].getValeur() == grid[i][j-1].getValeur() || grid[j][i].getValeur() == grid[j-1][i].getValeur()) {
	        		return false;
	        	}
	        }
	    }
	    return true;
	}	
	// Permet de faire le mouvement de la droite vers la gauche
	public void moveLeft() {
		// M'aide à savoir s'il a déjà effectuer une fusion pour chacune des lignes
		// Car pour un move horizontale, on a droit à une fusion par ligne
	    boolean[] movedAndMerged = {false, false, false, false};
	    // Me permet de bloquer les lignes
	    for (int row = 0; row < 4; row++)
	    {
	    	// Me permet de bloquer les colonnes
	        for (int col = 1; col < 4; col++)
	        {
	        	// Me permet de translater entre les colonnes
	            for (int i = col; i > 0; i--)
	            {
	            	// Puisque je commence à la 2e Tuile
	            	// Si celle ci est à une valeur de 0, je continue
	                if (grid[row][i].isEmpty())
	                    continue;
	                // Si j'arrive ici ca veut dire que la 2e Tuiles est differentes de 0
	                
	                // Me permet de verifier si la 1ere Tuile est vide
	                // Si elle est vide je permute les valeurs de ma Tuile 2 et de ma Tuile 1
	                if (grid[row][i - 1].isEmpty())
	                {
	                    grid[row][i - 1].setValeur(grid[row][i].getValeur());
	                    grid[row][i].setValeur(0);
	                }
	                // Si la 1ere et 2e Tuile ont la meme valeur alors
	                // la Tuile 1 double sa valeur et la Tuile 2 est initialisé à 0
	                else if (grid[row][i - 1].getValeur() == grid[row][i].getValeur() && !movedAndMerged[row])
	                {
	                    score += grid[row][i].getValeur() * 2;
	                    grid[row][i - 1].setValeur(grid[row][i].getValeur() * 2);
	                    grid[row][i].setValeur(0);
	                    movedAndMerged[row] = true;
	                }
	                else
	                {
	                    break;
	                }
	            }
	        }
	    }
	    // Ajoute 2 dans une des Tuiles vides
	    addRandomTuile();
	}
	public void moveRight(){
	    // To be implemented
	    boolean[] movedAndMerged = {false, false, false, false};
	    for (int row = 0; row < 4; row++)
	    {
	        for (int col = 2; col >= 0; col--)
	        {
	            for (int i = col; i < 3; i++)
	            {
	                if (grid[row][i].isEmpty())
	                    continue;
	                if (grid[row][i + 1].isEmpty())
	                {
	                    grid[row][i + 1].setValeur(grid[row][i].getValeur());
	                    grid[row][i].setValeur(0);
	                }
	                else if (grid[row][i + 1].getValeur() == grid[row][i].getValeur() && !movedAndMerged[row])
	                {
	                    score += grid[row][i + 1].getValeur() * 2;
	                    grid[row][i + 1].setValeur(grid[row][i].getValeur() * 2);
	                    grid[row][i].setValeur(0);
	                    movedAndMerged[row] = true;
	                }
	                else
	                {
	                    break;
	                }
	            }
	        }
	    }
	    addRandomTuile();
	}
	public void moveUp(){
	    // To be implemented
	    boolean[] movedAndMerged = {false, false, false, false};
	    for (int col = 0; col < 4; col++)
	    {
	        for (int row = 1; row < 4; row++)
	        {
	            for (int i = row; i > 0; i--)
	            {
	                if (grid[i][col].isEmpty())
	                    continue;
	                if (grid[i - 1][col].isEmpty())
	                {
	                    grid[i - 1][col].setValeur(grid[i][col].getValeur());
	                    grid[i][col].setValeur(0);
	                }
	                else if (grid[i - 1][col].getValeur() == grid[i][col].getValeur() && !movedAndMerged[col])
	                {
	                    score += grid[i][col].getValeur() * 2;
	                    grid[i - 1][col].setValeur(grid[i][col].getValeur() * 2);
	                    grid[i][col].setValeur(0);
	                    movedAndMerged[col] = true;
	                }
	                else
	                {
	                    break;
	                }
	            }
	        }
	    }
	    addRandomTuile();
	}	
	public void moveDown(){
	    // To be implemented
	    boolean[] movedAndMerged = {false, false, false, false};
	    for (int col = 0; col < 4; col++)
	    {
	        for (int row = 2; row >= 0; row--)
	        {
	            for (int i = row; i < 3; i++)
	            {
	                if (grid[i][col].isEmpty())
	                    continue;
	                if (grid[i + 1][col].isEmpty())
	                {
	                    grid[i + 1][col].setValeur(grid[i][col].getValeur());
	                    grid[i][col].setValeur(0);
	                }
	                else if (grid[i + 1][col].getValeur() == grid[i][col].getValeur() && !movedAndMerged[col])
	                {
	                    score += grid[i + 1][col].getValeur() * 2;
	                    grid[i + 1][col].setValeur(grid[i][col].getValeur() * 2);
	                    grid[i][col].setValeur(0);
	                    movedAndMerged[col] = true;
	                }
	                else
	                {
	                    break;
	                }
	            }
	        }
	    }
	    addRandomTuile();
	}
	// Method qui me permet d'ajouter un 2 dans une des Tuiles vides de la grille
	public void addRandomTuile() {
		// Declaration de mon tableau qui va recenser toutes les Tuiles vides ( dont la valeur est egale à 0)
		// Ce tableau recupere en realite les coordonnees des Tuiles vides
		ArrayList<Pair> emptyTuiles = new ArrayList<>();
	    for (int i = 0; i < 4; i++)
	    {
	        for (int j = 0; j < 4; j++)
	        {
	            if (grid[i][j].isEmpty())
	            {
	            	emptyTuiles.add(new Pair(i, j));
	            }
	        }
	    }
	    // Ces conditions me permettent de dire que si le tableau n'est pas vide, j'affecte 2 dans une des Tuiles vides
	    // Au cas contraires, c a d si le tableau est vide, je verifie s'il est en condition de gameOver
	    if (!emptyTuiles.isEmpty())
	    {
	    	randomNumbers.nextInt(4);
	    	int randomIndex = randomNumbers.nextInt(emptyTuiles.size());
	        int value = (randomNumbers.nextInt(10) < 7) ? 2 : 4;
	        grid[emptyTuiles.get(randomIndex).x][emptyTuiles.get(randomIndex).y].setValeur(value);
	    }
	    else
	    {
	        gameOver = isGameOver();
	    }
	}
	// Permet d'afficher la grille
	public void printGrid() {
	    for (int i=0; i<4; i++)
	    {
			System.out.println(" ___ ___ ___ ___ ");
	        for (int j=0; j<4; j++)
	        {
	        	// Ces conditions me permettent de ne pas afficher les valeurs des Tuiles qui est egale a 0
	            if (grid[i][j].isEmpty()) {
	                System.out.print("|   ");
	            }
	            else
	            {
	    			System.out.print("| " + grid[i][j].getValeur() + " ");
	            }
	        }
			System.out.println("|");
	    }

		System.out.println(" ___ ___ ___ ___ ");
	}
	
	
}
