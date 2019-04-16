package musicSheetCreator;

public abstract class Simbol {
	private int posX, posY;
	
	public Simbol(int posX, int posY) {
		this.setPosX(posX);
		this.setPosY(posY);
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}
}
