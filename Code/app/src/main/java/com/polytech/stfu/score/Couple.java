package com.polytech.stfu.score;

public class Couple  implements Comparable<Couple>{
	private String pseudo;
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

	@Override
	public int compareTo(Couple o) {
		if(this.score > o.score){
			return 1;
		}
		else if(this.score == o.score){
			return 0;
		}
		return -1;
	}
}