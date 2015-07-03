package android.atomix.wordcombat.supports;

import java.util.ArrayList;
import java.util.HashMap;

public class WordCombatApp {
	
	private ArrayList<SpriteInfo> spriteInfosList;
	private ArrayList<SpriteNameAndTag> spriteNameList;
	private HashMap<String, String> mappingWordList;
	private ArrayList<String> randomWordName;
	private ArrayList<WordInfo> wordInfosList;
	
	public static WordCombatApp instance;
	
	private WordCombatApp()
	{
		
	}

	public static WordCombatApp getInstance() 
	{
		if(instance == null)
		{
			instance = new WordCombatApp();
		}
		return instance;
	}

	public static void destroyInstance()
	{
		instance = null;
	}

	public ArrayList<SpriteInfo> getSpriteInfosList() 
	{
		return spriteInfosList;
	}

	public void setSpriteInfosList(ArrayList<SpriteInfo> spriteInfosList) 
	{
		this.spriteInfosList = spriteInfosList;
	}

	public ArrayList<SpriteNameAndTag> getSpriteNameList() 
	{
		return spriteNameList;
	}

	public void setSpriteNameList(ArrayList<SpriteNameAndTag> spriteNameList) 
	{
		this.spriteNameList = spriteNameList;
	}

	public HashMap<String, String> getMappingWordList() 
	{
		return mappingWordList;
	}

	public void setMappingWordList(HashMap<String, String> mappingWordList) 
	{
		this.mappingWordList = mappingWordList;
	}

	public ArrayList<String> getRandomWordName() 
	{
		return randomWordName;
	}

	public void setRandomWordName(ArrayList<String> randomWordName) 
	{
		this.randomWordName = randomWordName;
	}

	public ArrayList<WordInfo> getWordInfosList() 
	{
		return wordInfosList;
	}

	public void setWordInfosList(ArrayList<WordInfo> wordInfosList) 
	{
		this.wordInfosList = wordInfosList;
	}
}
