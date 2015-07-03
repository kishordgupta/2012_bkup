package android.atomix.wordcombat.facebook;

import android.atomix.wordcombat.facebook.Facebook.DialogListener;


/**
 * Skeleton base class for RequestListeners, providing default error 
 * handling. Applications should handle these error conditions.
 *
 */
public abstract class BaseDialogListener implements DialogListener 
{

    public void onFacebookError(FacebookError e) 
    {
        e.printStackTrace();
    }

    public void onError(DialogError e) 
    {
        e.printStackTrace();        
    }

    public void onCancel() 
    { 
    	
    }
    
}
