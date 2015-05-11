
public class Tokens {
	String colour;
	int num;
	
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}

