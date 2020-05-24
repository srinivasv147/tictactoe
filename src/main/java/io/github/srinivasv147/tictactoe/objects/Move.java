package io.github.srinivasv147.tictactoe.objects;

public class Move {
	
	private int position;
	private double reward;
	
	public Move(int position, double reward) {
		this.setPosition(position);
		this.setReward(reward);
	}
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public double getReward() {
		return reward;
	}
	public void setReward(double reward) {
		this.reward = reward;
	}

}
