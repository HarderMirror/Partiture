package musicSheetCreator;

import java.util.Random;

public abstract class MusicNotesTypes {
	public static MusicNote generateRandomTypeNote(int posX, int posY, MusicNote prevNote) {
		Random rn = new Random();
		switch (rn.nextInt(9)) {
			case 0:
				return new WholeNote(posX, posY);
			case 1:
				return new HalfNote(posX, posY);
			case 2:
				return new QuarterNote(posX, posY);
			case 3:
				return isJoinable(prevNote) ? new EightNote(posX, posY, false, true) : new EightNote(posX, posY, true, false);
			case 4:
				return isJoinable(prevNote) ? new SixteenthNote(posX, posY, false, true) : new SixteenthNote(posX, posY, true, false);
			case 5:
				return isJoinable(prevNote) ? new ThirtySecondNote(posX, posY, false, true) : new ThirtySecondNote(posX, posY, true, false);
			case 6:
				return isJoinable(prevNote) ? new SixtyFourthNote(posX, posY, false, true) : new SixtyFourthNote(posX, posY, true, false);
			case 7:
				return isJoinable(prevNote) ? new HundredTwentyEighth(posX, posY, false, true) : new HundredTwentyEighth(posX, posY, true, false);
			case 8:
				return isJoinable(prevNote) ? new TwoHundredFiftySixthNote(posX, posY, false, true) : new TwoHundredFiftySixthNote(posX, posY, true, false);
			default:
				return new WholeNote(posX, posY);
		}
	}
	
	private static boolean isJoinable(MusicNote prevNote) {
		boolean joinable = prevNote instanceof JoinableNote;
		if(joinable) {
			((JoinableNote) prevNote).setJoined(true);
		}
		
		return joinable;
	}
}
