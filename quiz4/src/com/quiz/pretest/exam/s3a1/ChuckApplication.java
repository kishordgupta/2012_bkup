/**
 * 
 */
package com.quiz.pretest.exam.s3a1;

import com.tmm.android.chuck.quiz.GamePlay;

import android.app.Application;

/**
 * @author rob
 *
 */
public class ChuckApplication extends Application{
	private static GamePlay currentGame;

	/**
	 * @param currentGame the currentGame to set
	 */
	public void setCurrentGame(GamePlay currentGame) {
		this.currentGame = currentGame;
	}

	/**
	 * @return the currentGame
	 */
	public GamePlay getCurrentGame() {
		return currentGame;
	}
}
