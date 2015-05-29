
public class Tokens {
	private boolean player;
	
	/**
	 * Constructor for the token
	 * @param playerNum player number to be associated with the token
	 */
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

	/**
	 * @return returns an int value for the player number
	 */
	public int getPlayerNum()
	{
		if (player == true){
			return 1;
		} else {
			return 2;
		}
	}

}

