package radioklub.sekhontech.com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.example.swipeuiforupclose.R;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.webkit.URLUtil;

public class ImageFetcherService extends Service {
	//Constants
	private static final String TAG = "ImageFetcherService";
	public static final String NOTIFICATION = "com.vg.intent.notification.image";
	
	public static final String STATUS = "STATUS";
	public static final String STATUS_FETCHING = "FETCHING";
	public static final String STATUS_NOTFOUND = "NOTFOUND";
	public static final String STATUS_DONE     = "DONE";
	
	//Member variables
	private ImageFetcherBinder mBinder;
	private Bitmap mBitmap;

	/* Service lifecycle hanlder functions
	 * onCreate : first time services created
	 * onStartCommand : on receive intent
	 * onBind : when activity call bindService
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		mBinder = new ImageFetcherBinder();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	/* Public and property access functions
	 * these functions used by activity after binding 
	 * with service
	 */
	public void fetchCover(String searchTerm) {
		new ReceiveImageTask().execute(searchTerm);
	}
	
	public Bitmap getBitmap() {
		return mBitmap;
	}
	
	/* Internal functions 
	 * 	1 - sendNotification : send broadcast notification
	 */
	private void sendNotification(String key, String value) {
		Intent intent = new Intent(NOTIFICATION);
		intent.putExtra(key, value);
		sendBroadcast(intent);
	}
	
	/* Nested class
	 * This sections contain nested class inside ImageFetcherService
	 * 	1 - Binder used for bind service to activity
	 *  2 - ReceiveImageTask used for fetching image asynchronously
	 *      without invoke main thread
	 */
	//Binder class
	public class ImageFetcherBinder extends Binder {
		public ImageFetcherService getService() {
			return ImageFetcherService.this;
		}
	}
	//AsyncTask class
	class ReceiveImageTask extends AsyncTask<String, Void, Bitmap> {
		
		
		/* AsyncTask lifecycle functions
		 * doInBackground : running in background after execute
		 * onPostExecute  : work to done after executed
		 * (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
	    protected Bitmap doInBackground(String... terms) {
	    	//1 - Loading the search term
	    	//2 - Return default image if not an search term
	    	//3 - Query iTune and get the JSON object
	    	//4 - Extract the biggest artwork picture and return
	    	String searchTerm = terms[0];
	    	Bitmap bitmap = null;	

			URI searchUri = getSearchUri(searchTerm);
		    try {
		    	JSONObject jObj = new JSONObject(readFeed(searchUri));
		    	JSONObject response = jObj.getJSONArray("results").getJSONObject(0);
		        String imageUrl = response.getString("artworkUrl100");
		        
		        if (URLUtil.isValidUrl(imageUrl)) {
		        	bitmap = getBitmapFromUrl(imageUrl);
		        } else {
		        	return BitmapFactory.decodeResource(getResources(), R.drawable.radio);
		        }
		      } catch (Exception e) {
		    	  Log.d(TAG, e.toString());
		        e.printStackTrace();
		      }
		    return bitmap;
	    }

	    protected void onPostExecute(Bitmap bitmap) {
	        mBitmap = bitmap;
	        if (mBitmap != null) {
	        	sendNotification(STATUS, STATUS_DONE);
	        } else {
	        	sendNotification(STATUS, STATUS_NOTFOUND);
	        }
	    }
	    
	    /* ReceiveAsyncTask internal functions
	     * 	1 - getBitmapFromUrl, loading bitmap from url string
	     *  2 - readFeed, reading the raw websource
	     *  3 - getSearchUri, generate iTune search url based on search term
	     */
		private Bitmap getBitmapFromUrl(String src) {
			 try {
			        URL url = new URL(src);
			        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			        connection.setDoInput(true);
			        connection.connect();
			        InputStream input = connection.getInputStream();
			        Bitmap myBitmap = BitmapFactory.decodeStream(input);
			        return myBitmap;
			    } catch (IOException e) {
			        e.printStackTrace();
			        return null;
			    }
		}
		
		private String readFeed(URI uri) {
			StringBuilder builder = new StringBuilder();
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(uri);
			try {
				HttpResponse response = client.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				if (statusCode == 200) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
				} else {
					Log.e(TAG, "Failed to download file");
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return builder.toString();
		}

		private URI getSearchUri(String searchTerm) {
			String myURL = "https://itunes.apple.com/search?term={"+ searchTerm +"}&entity=musicTrack";
			URI uri = null;
			try {
				URL url = new URL(myURL);
				String nullFragment = null;
				uri = new URI(url.getProtocol(), url.getHost(), url.getPath(),
						url.getQuery(), nullFragment);
				System.out.println("URI " + uri.toString() + " is OK");
			} catch (MalformedURLException e) {
				System.out.println("URL " + myURL + " is a malformed URL");
			} catch (URISyntaxException e) {
				System.out.println("URI " + myURL + " is a malformed URL");
			}

			return uri;
		}
	}

}
