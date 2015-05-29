package com.polytech.stfu.score;

/**
 * Classe representant un couple (pseudo, score)
 *
 * @author Stfu
 *
 */
public class Couple  implements Comparable<Couple>{
	/**
	 * Pseudo du joueur a qui appartient le score
	 */
	private String pseudo;
	/**
	 * Score effectue
	 */
	private int score;

	public Couple(String pseudo, int score) {
		super();
		this.pseudo = pseudo;
		this.score = score;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
		result = prime * result + score;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Couple other = (Couple) obj;
		if (pseudo == null) {
			if (other.pseudo != null)
				return false;
		} else if (!pseudo.equals(other.pseudo))
			return false;
		if (score != other.score)
			return false;
		return true;
	}

	/**
	 * Fonction de comparaison par ordre décroissant de score
	 */
	@Override
	public int compareTo(Couple o) {
		int compare;
		if((this.score < o.score) || (this.score == o.score)){
			compare = 1;
		}
		else{
			compare = -1;
		}
		return compare;
	}
}
