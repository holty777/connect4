
public class Tokens {
	private boolean player;
	
	public Tokens (boolean playerNum) {
		this.setPlayer(playerNum);
	}

	/**
	 * @return the player
	 */
	public boolean isPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(boolean player) {
		this.player = player;
	}

	
	public int getPlayerNum()
	{
		if (player == true){
			return 1;
		} else {
			return 2;
		}
	}

}

