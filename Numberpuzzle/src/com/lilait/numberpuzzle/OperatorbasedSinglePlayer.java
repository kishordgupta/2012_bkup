package com.lilait.numberpuzzle;

import java.util.Random;

public class OperatorbasedSinglePlayer {
	// GAMESTAT
    private static final int GAMESTAT_GAMEEND = 0;
    private static final int GAMESTAT_GAMEPAUSE = -1;
    private static final int GAMESTAT_GAMERUN = 1;

    public static int GAMESTAT_CURRENT = GAMESTAT_GAMERUN;
    
    
    // GAMELEVEL
    private static final int GAMELEVEL_EASY = 0; //can select operator and operand
    private static final int GAMELEVEL_NORMAL = 1; // can only select operator and can see future operand
    private static final int GAMELEVEL_HARD = 2;   // can only select operator

    public static int GAMELEVEL_CURRENT = GAMELEVEL_EASY;
    
    
    // USERINPUTSTATE
    public static final int USERINPUTSTATE_GOT = 0; //user input has got
    public static final int USERINPUTSTATE_WAITING = -1; // waiting for user input
    public static final int USERINPUTSTATE_NEED = 2;   // user input needed

    public static int USERINPUTSTATE_CURRENT = USERINPUTSTATE_GOT;

    
    // USERINPUTTYPE
    public static final int USERINPUTTYPE_OPERATOR = 1; //user input for operator
    public static final int USERINPUTTYPE_FIRSTVALUE = 0; // waiting for user FIRST value
    public static final int USERINPUTTYPE_OPERAND = -1;   // ONLY FOR EASY LEVEL
    public static final int USERINPUTTYPE_FORGAMEEND = 2;   // GAME END RESULT
    
    
    public static int USERINPUTTYPE_CURRENT = USERINPUTTYPE_OPERATOR;

   
    
    // Assigned each operative a value to make random
    private static final int SELECT_OP_PLUS = 0;
    private static final int SELECT_OP_MINUS = 1;
    private static final int SELECT_OP_MULTIPLY = 2;
    private static final int SELECT_OP_DIVIDE = 3;

    // current operand to select
    public static int SELECTED_OP = SELECT_OP_PLUS;

    //for easylevel only
    public static int USER_DEFINED_OPERAND=SELECT_OP_PLUS;
    
    //for normallevelonly predicted next operand 
    public static int NEXT_OPERAND=SELECTED_OP;
    
    
    // the result user will go for
    public static int TERGET_RESULT = 0;

    // First input From user
    public static int USER_MAIN_INPUT = 0;
    
    // input From user for operation
    public static int USER_INPUT = 0;
    
    //Current value for 
    public static int CURRENT_VALUE = 0;
    
    //status of play
    public static boolean GAMESTAT=false;
    // Count of user tries
    public static int COUNT_USER_TRIES = 0;
    // Count of time pass
    public static long  usertimer=0;

    // number from primary input for visual effects
    public static int firstnumber = 0;
    public static int secondnumber = 0;

    
    //refresh full
    void resettodefault()
    {
        
        ///flag reset
        GAMESTAT_CURRENT = GAMESTAT_GAMERUN;
        GAMELEVEL_CURRENT = GAMELEVEL_EASY;
        USERINPUTSTATE_CURRENT = USERINPUTSTATE_GOT;
        USERINPUTTYPE_CURRENT = USERINPUTTYPE_OPERATOR;
        SELECTED_OP = SELECT_OP_PLUS;
        USER_DEFINED_OPERAND=SELECT_OP_PLUS;
        NEXT_OPERAND=SELECTED_OP;
        
        //value reset
        GAMESTAT = false;
        TERGET_RESULT = 0;
        USER_MAIN_INPUT = 0;
        USER_INPUT = 0;
        CURRENT_VALUE = 0;
        COUNT_USER_TRIES = 0;
        usertimer = 0;
        firstnumber = 0;
        secondnumber = 0;
    }
    
    
    // select operand from make random number
    void setSelectedrandom() {

        Random rand = new Random();
        SELECTED_OP = rand.nextInt(4);

    }

    // set first and second number for calculation and visual effects
    void prepareUsermaininput() {
        firstnumber = USER_MAIN_INPUT / 10;
        secondnumber = USER_MAIN_INPUT % 10;

    }

    // set target result which user have to reach
    void setTegetResult() {
        switch (SELECTED_OP) {
            case SELECT_OP_PLUS:
                TERGET_RESULT = firstnumber + secondnumber;
                break;
            case SELECT_OP_MINUS:
                TERGET_RESULT = Math.abs(firstnumber - secondnumber);
                break;
            case SELECT_OP_MULTIPLY:
                TERGET_RESULT = firstnumber * secondnumber;
                break;
            case SELECT_OP_DIVIDE:
                TERGET_RESULT = firstnumber / secondnumber;
                break;

            default:
                break;
        }
    }
    
    ///set current value for first time
    void setCurrentValueforfirsttime()
    {
        CURRENT_VALUE=USER_MAIN_INPUT;
    }
    //initial operation which is common for all
    void firstCommonOperationforall()
    {
        
        USERINPUTTYPE_CURRENT=USERINPUTTYPE_FIRSTVALUE;
        USERINPUTSTATE_CURRENT=USERINPUTSTATE_NEED;
        waitforuserinput();
        prepareUsermaininput();
        setSelectedrandom();
        setTegetResult();
        setCurrentValueforfirsttime();
       
    }
    
    /// after user select value the operation done
    void processDataafterUserOperation()
    {
        switch (SELECTED_OP) {
            case SELECT_OP_PLUS:
                CURRENT_VALUE = CURRENT_VALUE + USER_INPUT;
                break;
            case SELECT_OP_MINUS:
                CURRENT_VALUE = Math.abs(CURRENT_VALUE - USER_INPUT);
                break;
            case SELECT_OP_MULTIPLY:
                CURRENT_VALUE = CURRENT_VALUE * USER_INPUT;
                break;
            case SELECT_OP_DIVIDE:
                CURRENT_VALUE = CURRENT_VALUE / USER_INPUT;
                break;

            default:
                break;
        }
    }
 
    
    ///check result
    void resulcheck()
    {
        if(TERGET_RESULT==CURRENT_VALUE)
           GAMESTAT_CURRENT= GAMESTAT_GAMEEND;
        else
            GAMESTAT_CURRENT= GAMESTAT_GAMERUN;
        
    }
    
    /// Check gamestat
    boolean checkGameStat()
    {
        if( GAMESTAT_CURRENT== GAMESTAT_GAMEEND)
            GAMESTAT=true;
        else
            GAMESTAT=false;
        return GAMESTAT;
    }
    
    //waiting for user input 
    void waitforuserinput()
    {
        boolean loopControlVariable=true;
        while(loopControlVariable)
        {
            switch (USERINPUTSTATE_CURRENT) {
                case USERINPUTSTATE_GOT:
                    loopControlVariable=false;
                    break;
                case USERINPUTSTATE_NEED:
                    loopControlVariable=true;
                    break;
                case USERINPUTSTATE_WAITING:
                    loopControlVariable=true;
                    break;
                default:
                    break;
            }
        }
        
    }
    
    //gameplay
    void gameplay()
    {
        switch (GAMELEVEL_CURRENT) {
            case GAMELEVEL_EASY:
                
                break;
           case GAMELEVEL_NORMAL:
                
                break;
           case GAMELEVEL_HARD:
               
               break;
            default:
                break;
        }
    }
    
    // easy level game
    public void Gameplayeasy()
    {
        
        firstCommonOperationforall();
        while(!checkGameStat())
        {
            USERINPUTTYPE_CURRENT=USERINPUTTYPE_OPERATOR;
            USERINPUTSTATE_CURRENT=USERINPUTSTATE_NEED;
            waitforuserinput();
            USERINPUTTYPE_CURRENT=USERINPUTTYPE_OPERAND;
            USERINPUTSTATE_CURRENT=USERINPUTSTATE_NEED;
            waitforuserinput();
            processDataafterUserOperation();
            resulcheck();
        }
    }

    //normal level game
    public void GameplayNormal()
    {
        
        firstCommonOperationforall();
        while(!checkGameStat())
        {
            USERINPUTTYPE_CURRENT=USERINPUTTYPE_OPERATOR;
            USERINPUTSTATE_CURRENT=USERINPUTSTATE_NEED;
            setSelectedrandom();
            waitforuserinput();
            processDataafterUserOperation();
            resulcheck();
        }
    }
    
    //hard level game
    public void GameplayHard()
    {
        
        firstCommonOperationforall();
        while(!checkGameStat())
        {
            USERINPUTTYPE_CURRENT=USERINPUTTYPE_OPERATOR;
            USERINPUTSTATE_CURRENT=USERINPUTSTATE_NEED;
            waitforuserinput();
            setSelectedrandom();
            processDataafterUserOperation();
            resulcheck();
        }
    }

}
