package android.atomix.wordcombat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.LayoutGameActivity;
import org.andengine.util.color.Color;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.atomix.wordcombat.database.DataBaseUtil;
import android.atomix.wordcombat.facebook.AsyncFacebookRunner;
import android.atomix.wordcombat.facebook.BaseRequestListener;
import android.atomix.wordcombat.facebook.Facebook;
import android.atomix.wordcombat.facebook.SessionStore;
import android.atomix.wordcombat.supports.ConstantValues;
import android.atomix.wordcombat.supports.MyCustomTextView;
import android.atomix.wordcombat.supports.MyCustomView;
import android.atomix.wordcombat.supports.SpriteInfo;
import android.atomix.wordcombat.supports.SpriteNameAndTag;
import android.atomix.wordcombat.supports.WordCombatApp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class WordCombatActivity extends LayoutGameActivity implements OnClickListener {

	private int cameraWidth, cameraHeight;
	private DisplayMetrics displayMetrics;

	private Camera mCamera;
	private Scene mScene;
	private int iconCount = 50;
	private ArrayList<ITextureRegion> regionList;
	private ArrayList<CustomSprite> allSpriteList;
	private ArrayList<CustomSprite> addNewSpriteList;
	private SpriteNameAndTag spriteNameAndTag;
	private SpriteInfo spriteInfo;

	private StringBuilder wordString;
	private ArrayList<Character> charArray;

	private float difX;
	private float difY;
	private IEntity target, destinationSprite;
	private int previousVlaue = 0, nextValue = 0;
	private float currentX = 0, currentY = 0;

	private String[][] tableChar;
	private ArrayList<String> verticalWordList;
	private ArrayList<String> horizontalWordList;

	private Map<String, Integer> horizontalIndex;
	private Map<String, Integer> verticalIndex;
	
	private int totalMoveCount = 30, totalTimeCount = 90;

	private MyCustomTextView /*txtMoveCount*/ txtStageCount;
	private MyCustomView txtFirstWord, txtSecondWord, txtThirdWord, txtFourthWord, txtFifthWord, txtFirstWordMeaning, txtSecondWordMeaning, txtThirdWordMeaning, txtFourthWordMeaning, txtFifthWordMeaning;
	private Button btnRefresh, btnBack;
	
	private int completedWordCount = 0;
	
	private OnCreateResourcesCallback createResourcesCallback;
	private OnCreateSceneCallback createSceneCallback;
	
	private int currentTouchIndex, left, right, top, bottom, uppetLeft, uppetRight, bottomLeft, bottomRight;
	private ArrayList<Integer> adjacentPoint;
	private DataBaseUtil dataBaseUtil;
	
	private boolean isMove = false, isTime = false, isCountDown = false;
	private CountDownTimer timerMoveOrTime;
	
	private boolean isFirstWord = false, isSecondWord = false, isThirdWord = false, isFourthWord = false, isFifthWord = false;
	
	private ImageView imgViewTimeLeft, imgViewClock;
	
	private int currentTag = 0;
	private boolean isMoveSprite = false;
	
	private MyCustomTextView txtViewPopupMsg;
	
	private MediaPlayer mediaPlayer;
	
	private static final String APP_ID = "603597719692780";
	final Handler mRunOnUi = new Handler();
	private ProgressDialog mProgress;
	private Facebook mFacebook;
	
	private static SharedPreferences mSharedPreferences;
	static final String PREF_KEY_TWITTER_LOGIN = "isTwitterLogedIn";
	private ProgressDialog pDialog;
	
	static String TWITTER_CONSUMER_KEY = "marlVCZaLYAG52rVvholRw";
	static String TWITTER_CONSUMER_SECRET = "5uZZFboHSK9psBPsqJUDSb1GuC36Fy83cYornOPu9A";
	static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
	static final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
	
	private CountDownTimer countDownTimerSprite;
	private int touchTime = 5;
	
	private ViewFlipper viewFlipperOne, viewFlipperTwo;
	
	private Activity activity;

	@Override
	protected void onCreate(Bundle pSavedInstanceState) 
	{
		super.onCreate(pSavedInstanceState);
		
		activity = this;
		
		
		mFacebook = new Facebook(APP_ID);
		SessionStore.restore(mFacebook, getApplicationContext());
		
		mSharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
		
		//txtMoveCount = (MyCustomTextView) findViewById(R.id.txt_view_move_count);
		//txtMoveCount.setTextColor(android.graphics.Color.WHITE);
		
		viewFlipperOne = (ViewFlipper) findViewById(R.id.view_flipper_one);
		
		viewFlipperTwo = (ViewFlipper) findViewById(R.id.view_flipper_two);
		
		imgViewTimeLeft = (ImageView) findViewById(R.id.img_view_move_time);
		
		imgViewClock = (ImageView) findViewById(R.id.img_view_clock);
		
		txtStageCount = (MyCustomTextView) findViewById(R.id.txt_view_stage_count);
		
		txtStageCount.setText(""+ConstantValues.levelCount);
		txtStageCount.setTextColor(android.graphics.Color.WHITE);
		
		txtFirstWord = (MyCustomView) findViewById(R.id.txt_view_word_one);
		txtSecondWord = (MyCustomView) findViewById(R.id.txt_view_word_two);
		txtThirdWord = (MyCustomView) findViewById(R.id.txt_view_word_three);
		txtFourthWord = (MyCustomView) findViewById(R.id.txt_view_word_four);
		txtFifthWord = (MyCustomView) findViewById(R.id.txt_view_word_fifth);
		
		txtFirstWordMeaning = (MyCustomView) findViewById(R.id.txt_view_language_one);
		txtSecondWordMeaning = (MyCustomView) findViewById(R.id.txt_view_language_two);
		txtThirdWordMeaning = (MyCustomView) findViewById(R.id.txt_view_language_three);
		txtFourthWordMeaning = (MyCustomView) findViewById(R.id.txt_view_language_four);
		txtFifthWordMeaning = (MyCustomView) findViewById(R.id.txt_view_language_fifth);
		
		btnRefresh = (Button) findViewById(R.id.btn_refresh);
		btnRefresh.setOnClickListener(this);
		
		btnBack = (Button) findViewById(R.id.btn_back);
		btnBack.setOnClickListener(this);
		
		txtFirstWord.setText(WordCombatApp.getInstance().getRandomWordName().get(0).toUpperCase(Locale.US));
		txtSecondWord.setText(WordCombatApp.getInstance().getRandomWordName().get(1).toUpperCase(Locale.US));
		txtThirdWord.setText(WordCombatApp.getInstance().getRandomWordName().get(2).toUpperCase(Locale.US));
		txtFourthWord.setText(WordCombatApp.getInstance().getRandomWordName().get(3).toUpperCase(Locale.US));
		txtFifthWord.setText(WordCombatApp.getInstance().getRandomWordName().get(4).toUpperCase(Locale.US));
		
		txtFirstWordMeaning.setText(WordCombatApp.getInstance().getMappingWordList().get(WordCombatApp.getInstance().getRandomWordName().get(0)));
		txtSecondWordMeaning.setText(WordCombatApp.getInstance().getMappingWordList().get(WordCombatApp.getInstance().getRandomWordName().get(1)));
		txtThirdWordMeaning.setText(WordCombatApp.getInstance().getMappingWordList().get(WordCombatApp.getInstance().getRandomWordName().get(2)));
		txtFourthWordMeaning.setText(WordCombatApp.getInstance().getMappingWordList().get(WordCombatApp.getInstance().getRandomWordName().get(3)));
		txtFifthWordMeaning.setText(WordCombatApp.getInstance().getMappingWordList().get(WordCombatApp.getInstance().getRandomWordName().get(4)));
		
		wordString = new StringBuilder();
		wordString.append(txtFirstWord.getText().toString().toLowerCase(Locale.US)).append(txtSecondWord.getText().toString().toLowerCase(Locale.US)).append(txtThirdWord.getText().toString().toLowerCase(Locale.US)).append(txtFourthWord.getText().toString().toLowerCase(Locale.US)).append(txtFifthWord.getText().toString().toLowerCase(Locale.US));
		
		Log.i("Count is", " is move or time.............."+ConstantValues.levelCountMoveOrTime);
		if(ConstantValues.levelCountMoveOrTime >= 0 && ConstantValues.levelCountMoveOrTime <= 3)
		{
			imgViewTimeLeft.setImageBitmap(null);
			imgViewTimeLeft.setBackgroundResource(R.drawable.move_left);
			//txtMoveCount.setText("" + totalMoveCount);
			countDownTimerAnimation();
			viewFlipperOne.setDisplayedChild(totalMoveCount / 10); 
			viewFlipperTwo.setDisplayedChild(totalMoveCount % 10);
			
			isMove = true;
			isTime = false;
		}
		
		if(ConstantValues.levelCountMoveOrTime >= 4 && ConstantValues.levelCountMoveOrTime <= 6)
		{
			activity.runOnUiThread(new Runnable() 
			{	
				@Override
				public void run() 
				{
					timerMoveOrTime = new CountDownTimer(91000, 1000) 
					{	
						@Override
						public void onTick(long millisUntilFinished) 
						{
							totalTimeCount--;
							imgViewTimeLeft.setImageBitmap(null);
							imgViewTimeLeft.setBackgroundResource(R.drawable.time_left);
							//txtMoveCount.setText("" + totalTimeCount);
							countDownTimerAnimation();
							
							if((totalTimeCount + 1) % 10 == 0)
							{
								viewFlipperOne.setDisplayedChild(totalTimeCount / 10); 
								viewFlipperTwo.setDisplayedChild(totalTimeCount % 10);
							}
							else
							{
								viewFlipperTwo.setDisplayedChild(totalTimeCount % 10);
							}
							isTime = true;
							isMove = false;
						}
						
						@Override
						public void onFinish() 
						{
							if(completedWordCount == 5)
							{
								ConstantValues.isGameWin = true;
								ConstantValues.levelCount++;
								
								storeWord();
								
								gameOverProcess();
								Log.i("Value is ", "___________1____________");
							}
							
							else
							{
								gameOverProcess();
								Log.i("Value is ", "___________2____________");
							}
							
						}
					};
					timerMoveOrTime.start();
				}
			});
			
		}
		
		txtViewPopupMsg = (MyCustomTextView) findViewById(R.id.txt_view_popup_msg);
		if(isMove)
		{
			txtViewPopupMsg.setText("Match these five words within 30 move");
			txtViewPopupMsg.setTextSize(34);
			txtViewPopupMsg.setTextColor(android.graphics.Color.rgb(70, 70, 170));
		}
		else
		{
			txtViewPopupMsg.setText("Match these five words within  90 sec");
			txtViewPopupMsg.setTextSize(34);
			txtViewPopupMsg.setTextColor(android.graphics.Color.rgb(70, 70, 170));
		}
		txtViewPopupMsg.setVisibility(View.VISIBLE);
		
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.popup_show);
		txtViewPopupMsg.startAnimation(animation);
		
		animation.setAnimationListener(new AnimationListener() 
		{	
			@Override
			public void onAnimationStart(Animation animation) 
			{
				txtViewPopupMsg.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) 
			{
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) 
			{
				Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.popup_hide);
				txtViewPopupMsg.startAnimation(animation1);
				txtViewPopupMsg.setVisibility(View.GONE);
			}
		});
	}

	@Override
	public EngineOptions onCreateEngineOptions() 
	{
		displayMetrics = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

		cameraWidth = displayMetrics.widthPixels;
		cameraHeight = displayMetrics.heightPixels;

		Log.i("Width   " + cameraWidth, "Height   " + cameraHeight);
		this.mCamera = new Camera(0, 0, 1200, 1000);
		return new EngineOptions(true, org.andengine.engine.options.ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(1200, 1000), this.mCamera);
	}

	@Override
	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception 
	{
		createResourcesCallback = pOnCreateResourcesCallback;
		reloadResource(pOnCreateResourcesCallback);
	}

	private void reloadResource(OnCreateResourcesCallback pOnCreateResourcesCallback) 
	{
		regionList = new ArrayList<ITextureRegion>();
		charArray = new ArrayList<Character>();
		
		shuffle(wordString.toString());
		
		Random rnd = new Random();
		
		for (int i = 0; i < wordString.length(); i++) 
		{
			charArray.add(wordString.charAt(i));
		}

		for (int i = 0; i < 50 - wordString.length(); i++) 
		{
			charArray.add(wordString.charAt(rnd.nextInt(wordString.length())));
		}

		for (int j = 1; j <= iconCount; j++) 
		{
			BitmapTextureAtlas texture = new BitmapTextureAtlas(this.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			ITextureRegion region = BitmapTextureAtlasTextureRegionFactory .createFromAsset(texture, this, "gfx/" + charArray.get(j - 1) + ".png", 0, 0);
			texture.load();
			regionList.add(region);
		}
		
//		BitmapTextureAtlas texture = new BitmapTextureAtlas(this.getTextureManager(), 480, 600, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
//		ITextureRegion region = BitmapTextureAtlasTextureRegionFactory .createFromAsset(texture, this, "gfx/bg.png", 0, 0);
//
//		texture.load();
//		regionList.add(region);
		
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)throws Exception 
	{
		createSceneCallback = pOnCreateSceneCallback;
		reload(pOnCreateSceneCallback);
	}
	CustomSprite background;
	private void reload(OnCreateSceneCallback pOnCreateSceneCallback) 
	{
		allSpriteList = new ArrayList<CustomSprite>();
		this.mEngine.registerUpdateHandler(new FPSLogger());

		// scene
		mScene = new Scene();
//		BitmapTextureAtlas texture = new BitmapTextureAtlas(this.getTextureManager(), 480, 600, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
//		texture.load();
//		ITextureRegion region = BitmapTextureAtlasTextureRegionFactory .createFromAsset(texture, this, "gfx/bg.png", 0, 0);
//		Sprite background = new Sprite(0, 0, region, this.getVertexBufferObjectManager());
//		//background.setTag(80);
//		background = new CustomSprite(0, 0, regionList.get(50), this.getVertexBufferObjectManager(), mScene);
//		background.setTag(80);
//		//background.setCullingEnabled(true);
//		//background.setZIndex(1);
//		mScene.setBackground(new SpriteBackground(background));
//		mScene.attachChild(background);
		//mScene.registerTouchArea(background);
		
		mScene.setBackground(new Background(Color.WHITE));
		//getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

		ArrayList<SpriteInfo> arrayList = new ArrayList<SpriteInfo>();
		ArrayList<SpriteNameAndTag> nameList = new ArrayList<SpriteNameAndTag>();
		// sprites
		int index = 0;
		for (int i = 0; i < 1200; i += 200) 
		{
			for (int j = 0; j < 1000; j += 200) 
			{
				spriteInfo = new SpriteInfo();
				// pass the parent object as a last argument
				CustomSprite sprite = new CustomSprite(0, 0, regionList.get(index), this.getVertexBufferObjectManager(), mScene);
				
				sprite.setTag(index);
				mScene.attachChild(sprite);
				mScene.registerTouchArea(sprite);
				spriteInfo.setSpritePositionX(i);
				spriteInfo.setSpritePositionY(j);
				//Log.i("................"+sprite.getX(), ".................."+sprite.getY());
				spriteInfo.setSpriteInterceptX(i + (int) sprite.getWidth() / 2);
				spriteInfo.setSpriteInterceptY(j + (int) sprite.getHeight() / 2);
				
				arrayList.add(spriteInfo);
				allSpriteList.add(index, sprite);
				sprite.setPosition(i, j);
				sprite.setZIndex(1);
				index++;

			}
		}
		
		mScene.sortChildren();

		for (int i = 0; i < 30; i++) 
		{
			spriteNameAndTag = new SpriteNameAndTag();
			spriteNameAndTag.setSpriteName(charArray.get(i).toString());
			nameList.add(spriteNameAndTag);
		}

		WordCombatApp.getInstance().setSpriteInfosList(arrayList);
		WordCombatApp.getInstance().setSpriteNameList(nameList);

		mScene.setTouchAreaBindingOnActionDownEnabled(true);
		mScene.setTouchAreaBindingOnActionMoveEnabled(true);
		pOnCreateSceneCallback.onCreateSceneFinished(mScene);

	}

	@Override
	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception 
	{
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}

	@Override
	protected int getLayoutID() 
	{
		return R.layout.main;
	}

	@Override
	protected int getRenderSurfaceViewID() 
	{
		return R.id.gameSurfaceView;
	}

	@Override
	public void onBackPressed() 
	{
		super.onBackPressed();
		regionList.removeAll(regionList);
		ConstantValues.isResume = true;
		ConstantValues.isNewGame = false;
		startActivity(new Intent(WordCombatActivity.this, SettingActivity.class));
		for(int i = 0; i < allSpriteList.size(); i++)
		{
			mScene.unregisterTouchArea(allSpriteList.get(i));
		}
		finish();
	}
	
	@Override
	public void onDestroyResources() throws Exception 
	{
		super.onDestroyResources();
	}

	private Sprite getTouchedSprite(float touchX, float touchY, IEntity previouslyTouched) 
	{
		for (Sprite sprite : allSpriteList) 
		{
			if (sprite.contains(touchX, touchY) && sprite != previouslyTouched) 
			{
				//Log.i("return", "true________"+sprite.getTag());
				isMoveSprite = true;
				nextValue = previousVlaue;
				previousVlaue = sprite.getTag();
				currentTouchIndex = sprite.getTag();
				int x = currentTouchIndex % 5;
				int y = currentTouchIndex / 5;
				getAdjacentValue(x, y);
				return sprite;
			}
		}
		// Log.i("return", "false");
		isMoveSprite = false;
		return null;
	}

	private void checkCollisions() 
	{
		destinationSprite = getTouchedSprite(currentX + 50, currentY + 50, target);

		if (destinationSprite == null) 
		{
			target.setPosition(currentX, currentY);
			isMoveSprite = true;
			//Log.i("Current X" +currentX, "Current Y "+currentY);
			//isCountDown = false;
		} 
		else 
		{
			mediaPlayer.start();
			isMoveSprite = true;
			isCountDown = true;
			destinationSprite
			.setPosition(WordCombatApp.getInstance().getSpriteInfosList() .get(nextValue).getSpritePositionX(),
						WordCombatApp.getInstance().getSpriteInfosList() .get(nextValue).getSpritePositionY());
			destinationSprite.setTag(nextValue);
			//Log.i("else condition", "_______next_______"+nextValue);
			Collections.swap(WordCombatApp.getInstance().getSpriteNameList(), previousVlaue, nextValue);
			Collections.swap(allSpriteList, previousVlaue, nextValue);
		}
	}

	private void verticalMatchingWord() 
	{
		verticalWordList = new ArrayList<String>();
		verticalIndex = new HashMap<String, Integer>();
		int index = 1;
		int indexWith = 1;
		for (int i = 0; i < 6; i++) 
		{
			for (int j = 0; j < 5; j++) 
			{
				StringBuilder stringBuilder = new StringBuilder();
				for (int k = j; k < 5; k++) 
				{
					stringBuilder.append(tableChar[i][k]);
					verticalIndex.put(stringBuilder.toString() + indexWith, index);
					verticalWordList.add(stringBuilder.toString());
					indexWith++;
				}
				index++;
				stringBuilder = null;
			}
		}

		index = 1;
		indexWith = 1;
	}

	private void horizontalMatchingWord() 
	{
		horizontalWordList = new ArrayList<String>();
		horizontalIndex = new HashMap<String, Integer>();
		int index = 1;
		int indexWith = 1;
		for (int i = 0; i < 5; i++) 
		{
			for (int j = 0; j < 6; j++) 
			{
				StringBuilder stringBuilder = new StringBuilder();
				for (int k = j; k < 6; k++) 
				{
					stringBuilder.append(tableChar[k][i]);
					horizontalIndex.put(stringBuilder.toString() + indexWith, index);
					horizontalWordList.add(stringBuilder.toString());
					indexWith++;
				}

				index++;
				stringBuilder = null;
			}
		}
		index = 1;
		indexWith = 1;
	}
	
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		finish();
	}
	
	public class CustomSprite extends Sprite 
	{
		public CustomSprite(final float pX, final float pY, final ITextureRegion pITextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, Scene pScene) 
		{
			super(pX, pY,  pITextureRegion.getWidth(), pITextureRegion.getHeight(), pITextureRegion, pVertexBufferObjectManager);
			mScene = pScene;
		}
		
		@SuppressLint("NewApi")
		@Override
		public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) 
		{
			{
				currentTag = this.getTag();
				
				switch (pSceneTouchEvent.getAction()) 
				{
				case MotionEvent.ACTION_DOWN:
					activity.runOnUiThread(new Runnable() 
					{
						@Override
						public void run() 
						{
							touchTimer();
						}
					});
					
					mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.movemusic);
					isMoveSprite = false;
					this.setZIndex(2);
					mScene.sortChildren();
					this.setScale(1.2f);
					target = this;
					difX = pSceneTouchEvent.getX() - target.getX();
					difY = pSceneTouchEvent.getY() - target.getY();
					
					currentX = pSceneTouchEvent.getX() - difX;
					currentY = pSceneTouchEvent.getY() - difY;
					
					target = getTouchedSprite(pSceneTouchEvent.getX() - difX, pSceneTouchEvent.getY() - difY, null);
					break;

				case MotionEvent.ACTION_MOVE:
					if(touchTime != 0)
					{
						isMoveSprite = false;
						mScene.setZIndex(2);
						mScene.sortChildren();
						currentX = pSceneTouchEvent.getX() - difX;
						currentY = pSceneTouchEvent.getY() - difY;

						this.setPosition(currentX, currentY);
						if (target != null) 
						{
							for (int i = 0; i < adjacentPoint.size(); i++) 
							{
								int currentIndex = this.getTag();
								
								if (WordCombatApp.getInstance().getSpriteInfosList().get(currentIndex).getSpriteInterceptX() != 0) 
								{
									checkCollisions();
								}
								
								else
								{
									isMoveSprite = true;
								}
							}
						}

						else 
						{
							this.setPosition(currentX, currentY);
						}
					}
					
					break;
					
				case MotionEvent.ACTION_UP:
					countDownTimerSprite.cancel();
					activity.runOnUiThread(new Runnable() 
					{
						@Override
						public void run() 
						{
							imgViewClock.setImageBitmap(null);
							imgViewClock.setBackgroundResource(R.drawable.t_0);
						}
					});
					
					touchTime = 5;
					this.setScale(1.0f);
					if(isCountDown)
					{
						if(isMove)
						{
							activity.runOnUiThread(new Runnable() 
							{	
								@Override
								public void run() 
								{
									countDownTimerAnimation();
									if((totalMoveCount + 1) % 10 == 0)
									{
										viewFlipperOne.setDisplayedChild(totalMoveCount / 10); 
										viewFlipperTwo.setDisplayedChild(totalMoveCount % 10);
									}
									else
									{
										viewFlipperTwo.setDisplayedChild(totalMoveCount % 10);
									}
								}
							});
							totalMoveCount--;
							
						}
					}
					isCountDown = false;
					this.setZIndex(1);
					mScene.sortChildren();
					
					if(isMoveSprite)
					{
						this.setPosition(WordCombatApp.getInstance().getSpriteInfosList().get(previousVlaue).getSpritePositionX(), WordCombatApp.getInstance().getSpriteInfosList().get(previousVlaue).getSpritePositionY());
						this.setTag(previousVlaue);
					}
					
					else
					{
						this.setPosition(WordCombatApp.getInstance().getSpriteInfosList().get(currentTag).getSpritePositionX(), WordCombatApp.getInstance().getSpriteInfosList().get(currentTag).getSpritePositionY());
						this.setTag(currentTag);
					}
					
					isMoveSprite = false;
					target = null;
					previousVlaue = 0;
					nextValue = 0;
					currentX = 0;
					currentY = 0;
					difX = 0;
					difY = 0;
					tableChar = new String[WordCombatApp.getInstance().getSpriteNameList().size() / 5][5];
					for (int i = 0, r = 0; r < tableChar.length; r++) 
					{
						tableChar[r][0] = WordCombatApp.getInstance().getSpriteNameList().get(i++).getSpriteName();
						tableChar[r][1] = WordCombatApp.getInstance().getSpriteNameList().get(i++).getSpriteName();
						tableChar[r][2] = WordCombatApp.getInstance().getSpriteNameList().get(i++).getSpriteName();
						tableChar[r][3] = WordCombatApp.getInstance().getSpriteNameList().get(i++).getSpriteName();
						tableChar[r][4] = WordCombatApp.getInstance().getSpriteNameList().get(i++).getSpriteName();
					}
					
					horizontalMatchingWord();
					verticalMatchingWord();

					final int firstWordHorizontalIndex = horizontalWordList.indexOf(WordCombatApp.getInstance().getRandomWordName().get(0).toLowerCase(Locale.US)) + 1;
					final int secondWordHorizontalIndex = horizontalWordList.indexOf(WordCombatApp.getInstance().getRandomWordName().get(1).toLowerCase(Locale.US)) + 1;
					final int thirdWordHorizontalIndex = horizontalWordList.indexOf(WordCombatApp.getInstance().getRandomWordName().get(2).toLowerCase(Locale.US)) + 1;
					final int fourthWordHorizontalIndex = horizontalWordList.indexOf(WordCombatApp.getInstance().getRandomWordName().get(3).toLowerCase(Locale.US)) + 1;
					final int fifthWordHorizontalIndex = horizontalWordList.indexOf(WordCombatApp.getInstance().getRandomWordName().get(4).toLowerCase(Locale.US)) + 1;

					final int firstWordVerticalIndex = verticalWordList.indexOf(WordCombatApp.getInstance().getRandomWordName().get(0).toLowerCase(Locale.US)) + 1;
					final int secondWordVerticalIndex = verticalWordList.indexOf(WordCombatApp.getInstance().getRandomWordName().get(1).toLowerCase(Locale.US)) + 1;
					final int thirdWordVerticalIndex = verticalWordList.indexOf(WordCombatApp.getInstance().getRandomWordName().get(2).toLowerCase(Locale.US)) + 1;
					final int fourthWordVerticalIndex = verticalWordList.indexOf(WordCombatApp.getInstance().getRandomWordName().get(3).toLowerCase(Locale.US)) + 1;
					final int fifthWordVerticalIndex = verticalWordList.indexOf(WordCombatApp.getInstance().getRandomWordName().get(4).toLowerCase(Locale.US)) + 1;
					
					if (firstWordHorizontalIndex > 0 || firstWordVerticalIndex > 0) 
					{
						txtFirstWord.post(new Runnable() 
						{
							@Override
							public void run() 
							{
								txtFirstWord.setTextColor(android.graphics.Color.RED);
								if (firstWordHorizontalIndex > 0) 
								{
									if(!isFirstWord)
									{
										MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.shot);
										mediaPlayer.start();
										
										String wordName = WordCombatApp.getInstance().getRandomWordName().get(0).toLowerCase(Locale.US);
										horizontalAnimationAndInsert(firstWordHorizontalIndex, wordName.length(), wordName);
										isFirstWord = true;
									}
									
								}

								if (firstWordVerticalIndex > 0) 
								{
									if(!isFirstWord)
									{
										MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.shot);
										mediaPlayer.start();
										
										String wordName = WordCombatApp.getInstance().getRandomWordName().get(0).toLowerCase(Locale.US);
										verticalAnimationAndInsert(firstWordVerticalIndex, wordName.length(), wordName);
										isFirstWord = true;
									}
									
								}
							}
							
						});
					}
					if (secondWordHorizontalIndex > 0 || secondWordVerticalIndex > 0) 
					{
						txtSecondWord.post(new Runnable() 
						{
							@Override
							public void run() 
							{
								txtSecondWord.setTextColor(android.graphics.Color.RED);
								
								if (secondWordHorizontalIndex > 0) 
								{
									if(!isSecondWord)
									{
										MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.shot);
										mediaPlayer.start();
										
										String wordName = WordCombatApp.getInstance().getRandomWordName().get(1).toLowerCase(Locale.US);
										horizontalAnimationAndInsert(secondWordHorizontalIndex, wordName.length(), wordName);
										isSecondWord = true;
									}
								}

								if (secondWordVerticalIndex > 0) 
								{
									if(!isSecondWord)
									{
										MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.shot);
										mediaPlayer.start();
										
										String wordName = WordCombatApp.getInstance().getRandomWordName().get(1).toLowerCase(Locale.US);
										verticalAnimationAndInsert(secondWordVerticalIndex, wordName.length(), wordName);
										isSecondWord = true;
									}
								}
							}
						});
					}
					if (thirdWordHorizontalIndex > 0 || thirdWordVerticalIndex > 0) 
					{
						txtThirdWord.post(new Runnable() 
						{
							@Override
							public void run() 
							{
								txtThirdWord.setTextColor(android.graphics.Color.RED);
								if (thirdWordHorizontalIndex > 0) 
								{
									if(!isThirdWord)
									{
										MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.shot);
										mediaPlayer.start();
										
										String wordName = WordCombatApp.getInstance().getRandomWordName().get(2).toLowerCase(Locale.US);
										horizontalAnimationAndInsert(thirdWordHorizontalIndex, wordName.length(), wordName);
										isThirdWord = true;
									}
								}
								
								if (thirdWordVerticalIndex > 0) 
								{
									if(!isThirdWord)
									{
										MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.shot);
										mediaPlayer.start();
										
										String wordName = WordCombatApp.getInstance().getRandomWordName().get(2).toLowerCase(Locale.US);
										verticalAnimationAndInsert(thirdWordVerticalIndex, wordName.length(), wordName);
										isThirdWord = true;
									}
								}
							}
						});
					}
					if (fourthWordHorizontalIndex > 0 || fourthWordVerticalIndex > 0) 
					{
						txtFourthWord.post(new Runnable() 
						{
							@Override
							public void run() 
							{
								txtFourthWord.setTextColor(android.graphics.Color.RED);
								if (fourthWordHorizontalIndex > 0) 
								{
									if(!isFourthWord)
									{
										MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.shot);
										mediaPlayer.start();
										
										String wordName = WordCombatApp.getInstance().getRandomWordName().get(3).toLowerCase(Locale.US);
										horizontalAnimationAndInsert(fourthWordHorizontalIndex, wordName.length(), wordName);
										isFourthWord = true;
									}
								}

								if (fourthWordVerticalIndex > 0) 
								{
									if(!isFourthWord)
									{
										MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.shot);
										mediaPlayer.start();
										
										String wordName = WordCombatApp.getInstance().getRandomWordName().get(3).toLowerCase(Locale.US);
										verticalAnimationAndInsert(fourthWordVerticalIndex, wordName.length(), wordName);
										isFourthWord = true;
									}
								}
							}
						});
					}
					
					if (fifthWordHorizontalIndex > 0 || fifthWordVerticalIndex > 0) 
					{
						txtFifthWord.post(new Runnable() 
						{
							@Override
							public void run() 
							{
								txtFifthWord.setTextColor(android.graphics.Color.RED);
								if (fifthWordHorizontalIndex > 0) 
								{
									if(!isFifthWord)
									{
										MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.shot);
										mediaPlayer.start();
										
										String wordName = WordCombatApp.getInstance().getRandomWordName().get(4).toLowerCase(Locale.US);
										horizontalAnimationAndInsert(fifthWordHorizontalIndex, wordName.length(), wordName);
										isFifthWord = true;
									}
								}

								if (fifthWordVerticalIndex > 0) 
								{
									if(!isFifthWord)
									{
										MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.shot);
										mediaPlayer.start();
										
										String wordName = WordCombatApp.getInstance().getRandomWordName().get(4).toLowerCase(Locale.US);
										verticalAnimationAndInsert(fifthWordVerticalIndex, wordName.length(), wordName);
										isFifthWord = true;
									}
								}
							}
						});
					}
					else 
					{
						Log.i("Nothing", "Found________________");
					}
					if(isMove)
					{
						if(totalMoveCount == 0)
						{
							activity.runOnUiThread(new  Runnable() 
							{
								public void run() 
								{
									gameOverProcess();
								}
							});
							
						}
					}
					
					if(isTime)
					{
						if(totalTimeCount == 0)
						{
							ConstantValues.isGameWin = false;
							timerMoveOrTime.cancel();
							totalTimeCount = 90;
							Log.i("Value is ", "___________4____________");
							
							activity.runOnUiThread(new  Runnable() 
							{
								public void run() 
								{
									gameOverProcess();
								}
							});
						}
					}
					
					break;
				}

				return true;
			}
		}
	}
	
	public synchronized void horizontalAnimationAndInsert(int wordHorizontalIndex, final int wordLenght, String wordName)
	{
		String coloumString = horizontalIndex.get(wordName + wordHorizontalIndex).toString();
		Log.i("Get index is", "___________________" + coloumString);

		int indexRow = wordHorizontalIndex / 21;
		if (wordHorizontalIndex % 21 == 0) 
		{
			indexRow--;
		}
		int indexColoum = (Integer.parseInt(coloumString) - 1) % 6;

		final int index = indexRow + indexColoum * 5;

		runOnUpdateThread(new Runnable() 
		{	
			@Override
			public void run() 
			{
				for (int i = 0; i < wordLenght; i++) 
				{
					LoopEntityModifier lem = new LoopEntityModifier(new RotationModifier(1, 0, 360), 2);
					allSpriteList.get(index + (5 * i)).registerEntityModifier(lem);
				}
			}
		});
		
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		runOnUpdateThread(new Runnable() 
		{	
			@Override
			public void run() 
			{
				final int moveIndex = index + (5 * (wordLenght - 1));
				
				for (int i = 0; i < wordLenght; i++) 
				{
					mScene.detachChild(allSpriteList.get(index + (5 * i)));
					mScene.unregisterTouchArea(allSpriteList.get(index + (5 * i)));
				}
				
				for (int i = 0; i < (moveIndex % 5); i++) 
				{
					int in = index - i;
					for (int j = 0; j < wordLenght; j++) 
					{
						allSpriteList.get(in - 1).registerEntityModifier(new MoveModifier(1, 
							WordCombatApp.getInstance().getSpriteInfosList().get(in - 1).getSpritePositionX(),
							WordCombatApp.getInstance().getSpriteInfosList().get(in).getSpritePositionX(),
							WordCombatApp.getInstance().getSpriteInfosList().get(in - 1).getSpritePositionY(),
							WordCombatApp.getInstance().getSpriteInfosList().get(in).getSpritePositionY()));
						
						allSpriteList.get(in - 1).setTag(in);
						Collections.swap(WordCombatApp.getInstance().getSpriteNameList(), in, in - 1);
						Collections.swap(allSpriteList, in, in - 1);
						in += 5;
						
					}
	
					in = 0;
				}
	
				int addSpriteIndex = (index - (index % 5));
				 Random rand = new Random();
				 int newSpriteIndex = rand.nextInt(45 - 30 + 1) + 30;
				 addNewSpriteList = new ArrayList<WordCombatActivity.CustomSprite>();
				 for(int i = 0; i < wordLenght; i++) 
				 {
					 CustomSprite sprite = new CustomSprite(0, 0, regionList.get(newSpriteIndex), getVertexBufferObjectManager(), mScene) ;
					 //sprite.setScale(1.1f);
					 sprite.setTag(addSpriteIndex);
					 allSpriteList.remove(addSpriteIndex);
					 allSpriteList.add(addSpriteIndex, sprite);
					 addNewSpriteList.add(sprite);
					 mScene.attachChild(sprite);
					 mScene.registerTouchArea(sprite);
					 //mScene.registerUpdateHandler(new FPSLogger());
					 addNewSpriteList.get(addNewSpriteList.size() - 1).registerEntityModifier(new MoveModifier(1,
						WordCombatApp.getInstance().getSpriteInfosList().get(addSpriteIndex).getSpritePositionX(),
						WordCombatApp.getInstance().getSpriteInfosList().get(addSpriteIndex).getSpritePositionX(),
						-100,
						WordCombatApp.getInstance().getSpriteInfosList().get(addSpriteIndex).getSpritePositionY()));
					 
					 spriteNameAndTag = new SpriteNameAndTag();
					 spriteNameAndTag.setSpriteName(charArray.get(newSpriteIndex).toString());
					 WordCombatApp.getInstance().getSpriteNameList().remove(addSpriteIndex);
					 WordCombatApp.getInstance().getSpriteNameList().add(addSpriteIndex, spriteNameAndTag);
					 					 
					 addSpriteIndex += 5;
					 newSpriteIndex++;
					 
				 }
				completedWordCount++;
				if(completedWordCount == 5)
				{
					ConstantValues.isGameWin = true;
					ConstantValues.levelCount++;
					ConstantValues.levelCountMoveOrTime++;
					
					if(ConstantValues.levelCountMoveOrTime > 6)
					{
						ConstantValues.levelCountMoveOrTime = 0;
					}
					
//					try 
//					{
//						Thread.sleep(1000);
//					} 
//					catch (InterruptedException e) 
//					{
//						e.printStackTrace();
//					}
					
					storeWord();
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() 
						{
							gameOverProcess();
						}
					});
					
					if(isTime)
					{
						timerMoveOrTime.cancel();
						totalTimeCount = 90;
					}
					
					Log.i("Value is ", "___________5____________");
					completedWordCount = 0;
				}
			}
		});
	}
	
	public synchronized void verticalAnimationAndInsert(int wordVerticalIndex, final int wordLenght, String wordName)
	{
		String coloumString = verticalIndex.get(wordName + wordVerticalIndex).toString();

		final int coloumNumber = Integer.parseInt(coloumString) - 1;
		Log.i("Coloum is", "_______________" + coloumNumber);

		runOnUpdateThread(new Runnable() 
		{	
			@Override
			public void run() 
			{
				for (int i = 0; i < wordLenght; i++) 
				{
					LoopEntityModifier lem = new LoopEntityModifier(new RotationModifier(1, 0, 360), 2);
					allSpriteList.get(((coloumNumber) + i)).registerEntityModifier(lem);
				}
			}
		});
		
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		runOnUpdateThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				for (int i = 0; i < wordLenght; i++) 
				{
					mScene.detachChild(allSpriteList.get(coloumNumber + i));
					mScene.unregisterTouchArea(allSpriteList.get(coloumNumber + i));
				}

				for (int i = 0; i < (coloumNumber % 5); i++) 
				{
					allSpriteList.get(coloumNumber - 1).registerEntityModifier(new MoveModifier(1, 
						WordCombatApp.getInstance().getSpriteInfosList().get(coloumNumber - 1).getSpritePositionX(),
						WordCombatApp.getInstance().getSpriteInfosList().get(coloumNumber + 3).getSpritePositionX(),
						WordCombatApp.getInstance().getSpriteInfosList().get(coloumNumber - 1).getSpritePositionY(),
						WordCombatApp.getInstance().getSpriteInfosList().get(coloumNumber + 3).getSpritePositionY()));
					
					allSpriteList.get(coloumNumber - 1).setTag(coloumNumber + 3);
					Collections.swap(WordCombatApp.getInstance().getSpriteNameList(), coloumNumber + 3, coloumNumber - 1);
					Collections.swap(allSpriteList, coloumNumber + 3, coloumNumber - 1);
				}

				int addSpriteIndex = coloumNumber - (coloumNumber % 5);
				 Random rand = new Random();
				 int newSpriteIndex = rand.nextInt(45 - 30 + 1) + 30;
				 addNewSpriteList = new ArrayList<WordCombatActivity.CustomSprite>();
				 for(int i = 0; i < wordLenght; i++) 
				 {
					 CustomSprite sprite = new CustomSprite(0, 0, regionList.get(newSpriteIndex), getVertexBufferObjectManager(), mScene) ;
					
					 //sprite.setScale(1.1f);
					 sprite.setTag(addSpriteIndex);
					 allSpriteList.remove(addSpriteIndex);
					 allSpriteList.add(addSpriteIndex, sprite);
					 addNewSpriteList.add(sprite);
					 mScene.attachChild(sprite);
					 mScene.registerTouchArea(sprite);
					 //mScene.registerUpdateHandler(new FPSLogger());
					 addNewSpriteList.get(addNewSpriteList.size() - 1).registerEntityModifier(new MoveModifier(1,
						WordCombatApp.getInstance().getSpriteInfosList().get(addSpriteIndex).getSpritePositionX(),
						WordCombatApp.getInstance().getSpriteInfosList().get(addSpriteIndex).getSpritePositionX(),
						-150,
						WordCombatApp.getInstance().getSpriteInfosList().get(addSpriteIndex).getSpritePositionY()));
					 
					 spriteNameAndTag = new SpriteNameAndTag();
					 spriteNameAndTag.setSpriteName(charArray.get(newSpriteIndex).toString());
					 WordCombatApp.getInstance().getSpriteNameList().remove(addSpriteIndex);
					 WordCombatApp.getInstance().getSpriteNameList().add(addSpriteIndex, spriteNameAndTag);
					 
					 addSpriteIndex++;
					 newSpriteIndex++;
				 }
				 
				completedWordCount++;
					
				if(completedWordCount == 5)
				{
					ConstantValues.isGameWin = true;
					ConstantValues.levelCount++;
					ConstantValues.levelCountMoveOrTime++;
					
					Log.i("Move or Time", "________First time________"+ConstantValues.levelCountMoveOrTime);
					
					if(ConstantValues.levelCountMoveOrTime > 6)
					{
						ConstantValues.levelCountMoveOrTime = 0;
						Log.i("Move or Time", "_______if condition_________"+ConstantValues.levelCountMoveOrTime);
					}
					
//					try 
//					{
//						Thread.sleep(1000);
//					} 
//					catch (InterruptedException e) 
//					{
//						e.printStackTrace();
//					}
					
					storeWord();
					runOnUiThread(new Runnable() 
					{
						@Override
						public void run() 
						{
							gameOverProcess();
						}
					});
					
					if(isTime)
					{
						timerMoveOrTime.cancel();
						totalTimeCount = 90;
					}
					
					Log.i("Value is ", "___________6____________");
					completedWordCount = 0;
				}
			}
		});
	}

	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
			case R.id.btn_refresh:
				reloadResource(createResourcesCallback);
				reload(createSceneCallback);
				break;
				
			case R.id.btn_back:
				regionList.removeAll(regionList);
				ConstantValues.isResume = true;
				ConstantValues.isNewGame = false;
				
				for(int i = 0; i < allSpriteList.size(); i++)
				{
					mScene.unregisterTouchArea(allSpriteList.get(i));
				}
				onBackPressed();
				break;
		
			default:
				break;
		}
	}
	
	private void getAdjacentValue(int x, int y) 
	{
		adjacentPoint = new ArrayList<Integer>();
		
		left = (x - 1)  + (y * 5);
		right = (x + 1 )  + (y  * 5);
		bottom = x + (y + 1) * 5;
		top = x + (y - 1) * 5;
		
		uppetLeft = (x - 1) + (y - 1)  * 5;
		uppetRight = ( x - 1) + (y + 1) * 5;
		bottomLeft = (x + 1) + (y - 1) * 5;
		bottomRight = (x + 1) + (y + 1)  * 5;
		
		if(left >= 0 && left < 30)
		{
			adjacentPoint.add(left);
		}
		if(right >= 0 && right < 30)
		{
			adjacentPoint.add(right);
		}
		if(bottom >= 0 && bottom < 30)
		{
			adjacentPoint.add(bottom);
		}
		if(top >= 0 && top < 30)
		{
			adjacentPoint.add(top);
		}
		if(uppetLeft >= 0 && uppetLeft < 30)
		{
			adjacentPoint.add(uppetLeft);
		}
		if(uppetRight >= 0 && uppetRight < 30)
		{
			adjacentPoint.add(uppetRight);
		}
		if(bottomLeft >= 0 && bottomLeft < 30)
		{
			adjacentPoint.add(bottomLeft);
		}
		if(bottomRight >= 0 && bottomRight < 30)
		{
			adjacentPoint.add(bottomRight);
		}
		
	}
	
	public void storeWord()
	{
		dataBaseUtil = new DataBaseUtil(getApplicationContext());
		dataBaseUtil.open();
		dataBaseUtil.insertNameWithMeaning(WordCombatApp.getInstance().getRandomWordName().get(0).toString(), WordCombatApp.getInstance().getMappingWordList().get(WordCombatApp.getInstance().getRandomWordName().get(0).toString()), ConstantValues.languageName);
		dataBaseUtil.insertNameWithMeaning(WordCombatApp.getInstance().getRandomWordName().get(1).toString(), WordCombatApp.getInstance().getMappingWordList().get(WordCombatApp.getInstance().getRandomWordName().get(1).toString()), ConstantValues.languageName);
		dataBaseUtil.insertNameWithMeaning(WordCombatApp.getInstance().getRandomWordName().get(2).toString(), WordCombatApp.getInstance().getMappingWordList().get(WordCombatApp.getInstance().getRandomWordName().get(2).toString()), ConstantValues.languageName);
		dataBaseUtil.insertNameWithMeaning(WordCombatApp.getInstance().getRandomWordName().get(3).toString(), WordCombatApp.getInstance().getMappingWordList().get(WordCombatApp.getInstance().getRandomWordName().get(3).toString()), ConstantValues.languageName);
		dataBaseUtil.insertNameWithMeaning(WordCombatApp.getInstance().getRandomWordName().get(4).toString(), WordCombatApp.getInstance().getMappingWordList().get(WordCombatApp.getInstance().getRandomWordName().get(4).toString()), ConstantValues.languageName);
		dataBaseUtil.close();
	}
	
	protected void gameOverProcess() 
	{
		final Dialog dialog = new Dialog(WordCombatActivity.this);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.game_over_popup);
		dialog.setCancelable(false);
		
		MyCustomTextView txtMsg = (MyCustomTextView) dialog.findViewById(R.id.txt_view_msg_over);
		if(ConstantValues.isGameWin)
		{
			int level = ConstantValues.levelCount - 1;
			txtMsg.setText("Congratulations !\nYou have completed level "+level);
		}
		else
		{
			txtMsg.setText("Sorry !\nPlease try again.");
		}
		
		
		Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
		btnCancel.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				dialog.cancel();
				startActivity(new Intent(WordCombatActivity.this, SettingActivity.class));
				ConstantValues.isNewGame = false;
				ConstantValues.isResume = false;
				finish();
			}
		});
		
		Button btnNextOrBuy = (Button) dialog.findViewById(R.id.btn_next_or_buy);
		if(ConstantValues.isGameWin)
		{
			ConstantValues.isNewGame = true;
			btnNextOrBuy.setBackgroundResource(R.drawable.next_selector);
		}
		else
		{
			btnNextOrBuy.setBackgroundResource(R.drawable.buy_selector);
		}
		btnNextOrBuy.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if(ConstantValues.isGameWin)
				{
					dialog.cancel();
					regionList.remove(regionList);
					for(int i = 0; i < allSpriteList.size(); i++)
					{
						mScene.unregisterTouchArea(allSpriteList.get(i));
					}
					finish();
					startActivity(new Intent(WordCombatActivity.this, SettingActivity.class));
					ConstantValues.isGameWin = false;
				}
			}
		});
		
		Button btnReStart = (Button) dialog.findViewById(R.id.btn_re_start);
		btnReStart.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
//				if(ConstantValues.levelCount > 1)
//				{
//					ConstantValues.levelCount--;
//				}
				dialog.cancel();
				finish();
				startActivity(getIntent());
			}
		});
		
		CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) 
		{	
			@Override
			public void onTick(long millisUntilFinished) 
			{
				
			}
			
			@Override
			public void onFinish() 
			{
				gameShareProcess();
			}
		};
		countDownTimer.start();
		if(!this.isFinishing())
			dialog.show();
	}
	
	public void gameShareProcess() 
	{
		final Dialog dialog = new Dialog(WordCombatActivity.this);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.popup_share);
		dialog.setCancelable(false);
		
		final EditText edTxtMind = (EditText) dialog.findViewById(R.id.ed_txt_mind);
		
		Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel_share);
		btnCancel.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				dialog.cancel();
			}
		});
		
		Button btnFacebookShare = (Button) dialog.findViewById(R.id.btn_facebook_share);
		
		if(mFacebook.isSessionValid())
		{
			btnFacebookShare.setBackgroundResource(R.drawable.fb_s_over);
		}
		
		btnFacebookShare.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				startActivity(new Intent(WordCombatActivity.this, FacebookActivity.class));
			}
		});
		
		boolean isLoggedin = mSharedPreferences.getBoolean(PREF_KEY_TWITTER_LOGIN, false);
		
		Button btnTwitterShare = (Button) dialog.findViewById(R.id.btn_twitter_share);
		
		if(isLoggedin)
		{
			btnTwitterShare.setBackgroundResource(R.drawable.tw_s_over);
		}
		btnTwitterShare.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				startActivity(new Intent(WordCombatActivity.this, TwitterActivity.class));
			}
		});
		
		Button btnShare = (Button) dialog.findViewById(R.id.btn_share);
		btnShare.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				if(edTxtMind.getText().toString().length() > 0)
				{
					if(mFacebook.isSessionValid())
					{
						mProgress = ProgressDialog.show(WordCombatActivity.this, "", "Posting...", true, false);
						
						AsyncFacebookRunner mAsyncFbRunner = new AsyncFacebookRunner(mFacebook);
						
						Bundle params = new Bundle();
				    		
						params.putString("message", edTxtMind.getText().toString());
						
						mAsyncFbRunner.request("me/feed", params, "POST", new WallPostListener());
					}
					
					else
					{
						Toast.makeText(getApplicationContext(), "Please login in Facebook!",  Toast.LENGTH_LONG).show();
					}
					
					boolean isLoggedin = mSharedPreferences.getBoolean(PREF_KEY_TWITTER_LOGIN, false);
					if(isLoggedin)
					{
						new UpdateTwitterStatus().execute(edTxtMind.getText().toString());
					}
					
					else
					{
						Toast.makeText(getApplicationContext(), "Please login in Twitter!",  Toast.LENGTH_LONG).show();
					}
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Please write something !",  Toast.LENGTH_LONG).show();
				}
				
			}
		});
		if(!this.isFinishing())
			dialog.show();
	}
	
	final class WallPostListener extends BaseRequestListener 
	{
        public void onComplete(final String response) 
        {
        	mRunOnUi.post(new Runnable() 
        	{
        		public void run() 
        		{
        			if(mProgress.isShowing())
        			{
        				mProgress.dismiss();
        			}
        			
        			Toast.makeText(WordCombatActivity.this, "Posted to Facebook", Toast.LENGTH_SHORT).show();
        		}
        	});
        }
    }
	
	public void shuffle(String s)
    {
        StringBuilder shuffledString = new StringBuilder(); 

        while (s.length() != 0)
        {
            int index = (int) Math.floor(Math.random() * s.length());
            char c = s.charAt(index);
            s = s.substring(0, index) + s.substring(index + 1);
            shuffledString.append(c);
        }
        
        wordString = shuffledString;
    }
	
	class UpdateTwitterStatus extends AsyncTask<String, String, String> 
	{
		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(WordCombatActivity.this);
			pDialog.setMessage("Updating to twitter...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) 
		{
			Log.d("Tweet Text", "> " + args[0]);
			String status = args[0];
			try 
			{
				ConfigurationBuilder builder = new ConfigurationBuilder();
				builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
				builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);
				// Access Token
				String access_token = mSharedPreferences.getString(PREF_KEY_OAUTH_TOKEN, "");
				// Access Token Secret
				String access_token_secret = mSharedPreferences.getString(PREF_KEY_OAUTH_SECRET, "");

				AccessToken accessToken = new AccessToken(access_token, access_token_secret);
				Twitter twitter = new TwitterFactory(builder.build()).getInstance(accessToken);

				// Update status
				twitter4j.Status response = twitter.updateStatus(status);

				Log.d("Status", "> " + response.getText());
			} 
			catch (TwitterException e) 
			{
				// Error in updating status
				Log.d("Twitter Update Error", e.getMessage());
			}
			return null;
		}

		protected void onPostExecute(String file_url) 
		{
			pDialog.dismiss();
			activity.runOnUiThread(new Runnable() 
			{
				public void run() 
				{
					Toast.makeText(getApplicationContext(), "Status tweeted successfully", Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
	
	public void touchTimer()
	{
		countDownTimerSprite = new CountDownTimer(6000, 1000) 
		{	
			@Override
			public void onTick(long millisUntilFinished) 
			{
				imgViewClock.setImageBitmap(null);
				if(touchTime == 5)
				{
					imgViewClock.setBackgroundResource(R.drawable.t_1);
				}
				if(touchTime == 4)
				{
					imgViewClock.setBackgroundResource(R.drawable.t_2);
				}
				if(touchTime == 3)
				{
					imgViewClock.setBackgroundResource(R.drawable.t_3);
				}
				if(touchTime == 2)
				{
					imgViewClock.setBackgroundResource(R.drawable.t_4);
				}
				if(touchTime == 1)
				{
					imgViewClock.setBackgroundResource(R.drawable.t_5);
				}
				
				touchTime--;
			}
			
			@Override
			public void onFinish() 
			{
				imgViewClock.setImageBitmap(null);
				imgViewClock.setBackgroundResource(R.drawable.t_0);
			}
		};
		
		countDownTimerSprite.start();
	}
	
	public void countDownTimerAnimation()
	{
		Animation outToBottom = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 1.0f);
		outToBottom.setInterpolator(new AccelerateInterpolator());
		outToBottom.setDuration(500);
		Animation inFromTop = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromTop.setInterpolator(new AccelerateInterpolator());
		inFromTop.setDuration(500);
		viewFlipperOne.clearAnimation();
		viewFlipperOne.setInAnimation(inFromTop);
		viewFlipperOne.setOutAnimation(outToBottom);
		viewFlipperTwo.clearAnimation();
		viewFlipperTwo.setInAnimation(inFromTop);
		viewFlipperTwo.setOutAnimation(outToBottom);
	}
	
}