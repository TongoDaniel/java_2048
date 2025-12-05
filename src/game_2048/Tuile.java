package game_2048;

public class Tuile {
	private int valeur;
	
	
	public Tuile() {
		valeur = 0;
	}
	
	public int getValeur(){
		return valeur;
	}
	public void setValeur(int val) {
		valeur = val;
	}
	public boolean isEmpty() {
		return valeur == 0;
	}
}