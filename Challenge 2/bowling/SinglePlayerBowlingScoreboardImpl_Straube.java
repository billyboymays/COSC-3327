package bowling;

/**
 * @author kart
 * NOTE: You can "strip this Skeleton for parts"
 * Don't assume that all of the preconditions are included
 * Be sure to check this code for correctness
 */
public class SinglePlayerBowlingScoreboardImpl_Straube implements SinglePlayerBowlingScoreboard, AssignmentMetaData
{
	private static final int MAXIMUM_ROLLS = 21;	//Maximum rolls in a one player game
	private String playerName;
	private int[] pinsKnockedDownArray = new int[MAXIMUM_ROLLS];
	private int rollCount = 0;

	public SinglePlayerBowlingScoreboardImpl_Straube(String playerName)
	{
		assert playerName != null : "playerName is null!";
		this.playerName = playerName;
		rollCount = 0;
	}

	public String getPlayerName()
	{
		return playerName;
	}

	public int getCurrentFrame() 
	{
		assert !isGameOver() : "Game is over!";

		return((rollCount /2)+1);
		
	}

	public Mark getMark(int frameNumber, int boxIndex) 
	{	
		assert 1 <= frameNumber : "frameNumber = " + frameNumber + " < 1!";
		assert frameNumber <= 10 : "frameNumber = " + frameNumber + " > 10!";
		assert 1 <= boxIndex : "boxIndex = " + boxIndex + " < 1!";
		assert boxIndex <= 3 : "boxIndex = " + boxIndex + " > 3!";
//		Exercise for student: Fix
//			assert (boxIndex != 2) || (!isStrike(frameNumber) && !isSpare(frameNumber)) : "boxIndex = " + boxIndex + ", but there was a Strike! : frameNumber = " + frameNumber;
//			assert (boxIndex != 2) || (!isSpare(frameNumber)) : "boxIndex = " + boxIndex + ", but there was a Spare! : frameNumber = " + frameNumber;
		Mark mark = null;
		if(frameNumber < 10) mark = getMarkSingleDigitFrameNumber(frameNumber, boxIndex);
		else mark = getMarkTenthFrame(boxIndex);
		assert mark != null : "mark is null!";
		return mark;
	}

	private Mark getMarkSingleDigitFrameNumber(int frameNumber, int boxIndex)
	{
		assert 1 <= frameNumber : "frameNumber = " + frameNumber + " < 1!";
		assert frameNumber <= 9 : "frameNumber = " + frameNumber + " > 9!";
		assert 1 <= boxIndex : "boxIndex = " + boxIndex + " < 1!";
		assert boxIndex <= 2 : "boxIndex = " + boxIndex + " > 2!";

		int rollCountLocal = frameNumber * 2;

		if(boxIndex == 1)
		{
			return Mark.translate(pinsKnockedDownArray[rollCountLocal - 2]);
		}
		if(boxIndex == 2)
		{
			return Mark.translate(pinsKnockedDownArray[rollCountLocal - 1]);
		}
		return Mark.EMPTY;
	}

	private Mark getMarkTenthFrame(int boxIndex)
	{
		assert 1 <= boxIndex : "boxIndex = " + boxIndex + " < 1!";
		assert boxIndex <= 3 : "boxIndex = " + boxIndex + " > 3!";

		return Mark.EMPTY;
	}

	public int getScore(int frameNumber) 
	{
		assert 1 <= frameNumber : "frameNumber = " + frameNumber + " < 1!";
		assert frameNumber <= 10 : "frameNumber = " + frameNumber + " > 10!";
		assert frameNumber*2 <= rollCount;

		if(1 <= frameNumber && frameNumber <= 9)
		{
			return getScoreSingleDigitFrame(frameNumber);
		}
		else
		{
			assert frameNumber == 10;
			return getScoreTenthFrame();
		}
	}

	public int getScoreSingleDigitFrame(int frameNumber)
	{
		assert frameNumber != 10;
		assert frameNumber*2 <= rollCount;

		int max = frameNumber * 2;
		int score = 0;

		for(int i = 0; i <= max-1; i++)
		{
			score = pinsKnockedDownArray[i] + score;
		}

		return score;
	}

	public int getScoreTenthFrame()
	{
		throw new RuntimeException("Not Implemented");
	}

	public boolean isGameOver() 
	{
		boolean returnValue = true;
		if(rollCount == MAXIMUM_ROLLS)
		{
			return returnValue;
		}
		if(rollCount == 19)
		{
			return returnValue;
		}
		else
		{
			return !returnValue;
		}
	}

	public void recordRoll(int pinsKnockedDown) 
	{
		assert 0 <= pinsKnockedDown : "pinsKnockedDown = " + pinsKnockedDown + " < 0!";
		assert pinsKnockedDown <= 10 : "pinsKnockedDown = " + pinsKnockedDown + " > 10!";
		//assert (getCurrentBall() == 1) || 
		//		((getCurrentBall() == 2) && (((getCurrentFrame() == 10) && isStrikeOrSpare(getMark(10, 1))) || ((pinsKnockedDownArray[rollCount - 1] + pinsKnockedDown) <= 10))) || 
		//		((getCurrentBall() == 3) && (((getCurrentFrame() == 10) && isStrikeOrSpare(getMark(10, 2))) || ((pinsKnockedDownArray[rollCount - 1] + pinsKnockedDown) <= 10)));
		assert !isGameOver() : "Game is over!";
		assert frameHasMoreThan10Pins(pinsKnockedDown);
		
		pinsKnockedDownArray[rollCount] = pinsKnockedDown;
		rollCount++;
		
	}
	public boolean frameHasMoreThan10Pins(int pinsKnockedDown)
	{
		boolean val = false;
		
		int mark1 = getMark(getCurrentFrame(),1).getValue();
		
		if(mark1 + pinsKnockedDown > 10)
		{
			return val;
		}
		
		return !val;
	}
	public int getCurrentBall()
	{
		assert !isGameOver() : "Game is over!";

		if(rollCount == 21)
		{
			return 3;
		}
		if(rollCount % 2 == 0)
		{
			return 1;
		}
		else
		{
			return 2;
		}
	}


	private static final String VERTICAL_SEPARATOR = "#";
	private static final String HORIZONTAL_SEPARATOR = "#";
	private static final String LEFT_EDGE_OF_SMALL_SQUARE = "[";
	private static final String RIGHT_EDGE_OF_SMALL_SQUARE = "]";

	private String getScoreboardDisplay()
	{
		StringBuffer frameNumberLineBuffer = new StringBuffer();
		StringBuffer markLineBuffer = new StringBuffer();
		StringBuffer horizontalRuleBuffer = new StringBuffer();
		StringBuffer scoreLineBuffer = new StringBuffer();
		frameNumberLineBuffer.append(VERTICAL_SEPARATOR);

		markLineBuffer.append(VERTICAL_SEPARATOR);
		horizontalRuleBuffer.append(VERTICAL_SEPARATOR);
		scoreLineBuffer.append(VERTICAL_SEPARATOR);

		for(int frameNumber = 1; frameNumber <= 9; frameNumber++)
		{
			frameNumberLineBuffer.append("  " + frameNumber + "  ");
			markLineBuffer.append(" ");
			markLineBuffer.append(getMark(frameNumber, 1));
			markLineBuffer.append(LEFT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(getMark(frameNumber, 2));
			markLineBuffer.append(RIGHT_EDGE_OF_SMALL_SQUARE);

			final int CHARACTER_WIDTH_SCORE_AREA = 5;
			for(int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) horizontalRuleBuffer.append(HORIZONTAL_SEPARATOR);
			if(isGameOver() || frameNumber < getCurrentFrame())
			{
				int score = getScore(frameNumber);
				final int PADDING_NEEDED_BEHIND_SCORE = 1;
				final int PADDING_NEEDED_IN_FRONT_OF_SCORE = CHARACTER_WIDTH_SCORE_AREA - ("" + score).length() - PADDING_NEEDED_BEHIND_SCORE;
				for(int i = 0; i < PADDING_NEEDED_IN_FRONT_OF_SCORE; i++) scoreLineBuffer.append(" ");
				scoreLineBuffer.append(score);
				for(int i = 0; i < PADDING_NEEDED_BEHIND_SCORE; i++) scoreLineBuffer.append(" ");
			}
			else
			{
				for(int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) scoreLineBuffer.append(" ");
			}

			frameNumberLineBuffer.append(VERTICAL_SEPARATOR);
			markLineBuffer.append(VERTICAL_SEPARATOR);
			horizontalRuleBuffer.append(VERTICAL_SEPARATOR);
			scoreLineBuffer.append(VERTICAL_SEPARATOR);
		}
		//Frame 10:
		{
			final String THREE_SPACES = "   ";
			frameNumberLineBuffer.append(THREE_SPACES + 10 + THREE_SPACES);

			markLineBuffer.append(" ");
			markLineBuffer.append(getMark(10, 1));
			markLineBuffer.append(LEFT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(getMark(10, 2));
			markLineBuffer.append(RIGHT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(LEFT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(getMark(10, 3));
			markLineBuffer.append(RIGHT_EDGE_OF_SMALL_SQUARE);

			final int CHARACTER_WIDTH_SCORE_AREA = 8;
			for(int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) horizontalRuleBuffer.append(HORIZONTAL_SEPARATOR);
			if(isGameOver())
			{
				int score = getScore(10);
				final int PADDING_NEEDED_BEHIND_SCORE = 1;
				final int PADDING_NEEDED_IN_FRONT_OF_SCORE = CHARACTER_WIDTH_SCORE_AREA - ("" + score).length() - PADDING_NEEDED_BEHIND_SCORE;
				for(int i = 0; i < PADDING_NEEDED_IN_FRONT_OF_SCORE; i++) scoreLineBuffer.append(" ");
				scoreLineBuffer.append(score);
				for(int i = 0; i < PADDING_NEEDED_BEHIND_SCORE; i++) scoreLineBuffer.append(" ");
			}
			else
			{
				for(int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) scoreLineBuffer.append(" ");
			}

			frameNumberLineBuffer.append(VERTICAL_SEPARATOR);
			markLineBuffer.append(VERTICAL_SEPARATOR);
			horizontalRuleBuffer.append(VERTICAL_SEPARATOR);
			scoreLineBuffer.append(VERTICAL_SEPARATOR);
		}

		return 	getPlayerName() + "\n" +
		horizontalRuleBuffer.toString() + "\n" +
		frameNumberLineBuffer.toString() + "\n" +
		horizontalRuleBuffer.toString() + "\n" +
		markLineBuffer.toString() + "\n" +
		scoreLineBuffer.toString() + "\n" +
		horizontalRuleBuffer.toString();
	}

	public String toString()
	{
		return getScoreboardDisplay();
	}

	private int getRollIndexOfFirstBall(int frameNumber)
	{
		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	private boolean isStrike(int frameNumber)
	{
		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	private boolean isSpare(int frameNumber)
	{
		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	private boolean isStrikeOrSpare(Mark mark)
	{
		return ((mark == Mark.STRIKE) || (mark == Mark.SPARE));
	}

	//*************************************************
	//*************************************************
	//*************************************************
	//*********ASSIGNMENT METADATA STUFF***************
	public String getFirstNameOfSubmitter() 
	{
		return "Lukas";
	}

	public String getLastNameOfSubmitter() 
	{
		return "Straube";
	}

	public double getHoursSpentWorkingOnThisAssignment()
	{
		return 2.5;
	}

	public int getScoreAgainstTestCasesSubset()
	{
		return 80;
	}
}