package com.kd.kdstagemanagement.settings;

 public class FilterSetting {


static public	Checkboxclass ACTION_SCENES;
static public	Checkboxclass NUDITY;
static public	Checkboxclass CLASSIC_CARS;
static public	Checkboxclass SPORTS_CARS;
static public	Checkboxclass HUMOROUS_MOMENTS;


static public void initsettings()
{
	 ACTION_SCENES=new Checkboxclass();
	 NUDITY=new Checkboxclass();
	 CLASSIC_CARS=new Checkboxclass();
	 SPORTS_CARS=new Checkboxclass();
	 HUMOROUS_MOMENTS=new Checkboxclass();
	
	
	
	 ACTION_SCENES.Check=false;
	 NUDITY.Check=false;
	 CLASSIC_CARS.Check=false;
	 SPORTS_CARS.Check=false;
	 HUMOROUS_MOMENTS.Check=false;
	
	
	 ACTION_SCENES.Data="ACTION SCENES";
	 NUDITY.Data="NUDITY";
	 CLASSIC_CARS.Data="CLASSIC CARS";
	 SPORTS_CARS.Data="SPORTS CARS";
	 HUMOROUS_MOMENTS.Data="HUMOROUS MOMENTS";
	 
}


}
