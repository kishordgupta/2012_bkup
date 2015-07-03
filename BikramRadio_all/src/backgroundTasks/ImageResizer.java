package backgroundTasks;



import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ImageResizer {
	
	public static Bitmap getResizedBitmap(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        //float scaleWidth = ((float) ConstantValues.DEVICE_WIDTH) / width;
        //float scaleHeight = ((float) (0.75*ConstantValues.DEVICE_WIDTH)) / height;
        
        float scaleWidth = ((float) 200) / width;
        float scaleHeight = ((float) 100) / height;
        
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);


        // RECREATE THE NEW BITMAP
        //Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bm, 100, 75, true);
        return resizedBitmap;
    }

}
