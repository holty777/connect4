
public class Tokens {
	String colour;
	
	public Tokens (boolean playerNum) {
		if (playerNum == true) {
			this.setColour("blue");
		} else {
			this.setColour("red");
		}
		
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
}
