package com.kd.kdstagemanagement.settings;

public class SettingsHelp {

static public	Checkboxclass Warning ;
static public	Checkboxclass Summery;
static public	Checkboxclass ACTION_SCENES;
static public	Checkboxclass NUDITY;
static public	Checkboxclass CLASSIC_CARS;
static public	Checkboxclass SPORTS_CARS;
static public	Checkboxclass HUMOROUS_MOMENTS;
static public	Checkboxclass KEY_PLOT_POINTS;
static public	Checkboxclass MOVIE_END;

static public void initsettings()
{
	 Warning=new Checkboxclass();
	 Summery=new Checkboxclass();
	 ACTION_SCENES=new Checkboxclass();
	 NUDITY=new Checkboxclass();
	 CLASSIC_CARS=new Checkboxclass();
	 SPORTS_CARS=new Checkboxclass();
	 HUMOROUS_MOMENTS=new Checkboxclass();
	 KEY_PLOT_POINTS=new Checkboxclass();
	 MOVIE_END=new Checkboxclass();
	
	 Warning.Check=false;
	 Summery.Check=false;
	 ACTION_SCENES.Check=false;
	 NUDITY.Check=false;
	 CLASSIC_CARS.Check=false;
	 SPORTS_CARS.Check=false;
	 HUMOROUS_MOMENTS.Check=false;
	 KEY_PLOT_POINTS.Check=false;
	 MOVIE_END.Check=false;
	 
	 Warning.Data="Warning";
	 Summery.Data="Summery";
	 ACTION_SCENES.Data="ACTION SCENES";
	 NUDITY.Data="NUDITY";
	 CLASSIC_CARS.Data="CLASSIC CARS";
	 SPORTS_CARS.Data="SPORTS CARS";
	 HUMOROUS_MOMENTS.Data="HUMOROUS MOMENTS";
	 KEY_PLOT_POINTS.Data="KEY PLOT POINTS";
	 MOVIE_END.Data="MOVIE END";
}


}
