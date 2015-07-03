package android.atomix.wordcombat.supports;

public class ConstantValues 
{
	public static int moveCount = 30;
	public static boolean isNewGame = false;
	public static boolean isResume = false;
	public static boolean isGameWin = false;
	public static int levelCount = 1;
	public static int levelCountMoveOrTime = 1;
	public static boolean isMove = false;
	public static boolean isTimer = false;
	public static String languageName = "English";
	public static int languagePosition = 0;
	public static boolean isfacebookLogin = false;
	public static boolean isTwitterLogin = false;
	public static String archive = "";
	
	public static String mind = "";
	
	public static void reset() 
	{
		moveCount = 30;
		isNewGame = false;
		isResume = false;
		isGameWin = false;
		levelCount = 1;
		levelCountMoveOrTime = 1;
		isMove = false;
		isTimer = false;
		languageName = "";
		languagePosition = 0;
		archive = "";
    }
}
