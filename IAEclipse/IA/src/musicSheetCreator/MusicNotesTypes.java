package musicSheetCreator;

import java.util.Random;

public abstract class MusicNotesTypes {
	public static MusicNote generateRandomTypeNote(int posX, int posY, MusicNote prevNote) {
		Random rn = new Random();
		switch (rn.nextInt(6)) {
			case 0:
				return new WholeNote(posX, posY);
			case 1:
				return new HalfNote(posX, posY, isReversed(posY));
			case 2:
				return new QuarterNote(posX, posY, isReversed(posY));
			case 3:
				return new EightNote(posX, posY, prevNote);
			case 4:
				return new SixteenthNote(posX, posY, prevNote);
			case 5:
				return new ThirtySecondNote(posX, posY, prevNote);
			case 6:
				return new SixtyFourthNote(posX, posY, prevNote);
			case 7:
				return new HundredTwentyEighth(posX, posY, prevNote);
			case 8:
				return new TwoHundredFiftySixthNote(posX, posY, prevNote);
			default:
				return new WholeNote(posX, posY);
		}
	}
	
	
	private static boolean isReversed(int posY) {
		return posY < 8;
	}
	
	

}
