package backgroundTasks;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DownloadImageByUrl {
	public Bitmap downloadImage(String URL) {
		 Bitmap bitmap = null;
		InputStream in = null;
		try {
			in = OpenHttpConnection(URL);
			bitmap = BitmapFactory.decodeStream(in);
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return bitmap;
	}

	private InputStream OpenHttpConnection(String urlString) throws IOException {
		InputStream in = null;
		int response = -1;

		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();
			response = httpConn.getResponseCode();
			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
		} catch (Exception ex) {
			throw new IOException("Error connecting");
		}
		return in;
	}
/*
	class DownloadImageTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... urls) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Toast.makeText(getApplicationContext(), "doInBackground", Toast.LENGTH_LONG).show();
			Bitmap bitmap = DownloadImage("http://powerpointfx.net/test_wp/2.jpg");
			Log.v("DEBUG_LOG", "In DoInBackground");
			return bitmap.toString();

		}

		@Override
		protected void onPostExecute(String result) {
			//Toast.makeText(getApplicationContext(), "onPostExecute", Toast.LENGTH_LONG).show();
			ImageView img = (ImageView) findViewById(R.id.img);
			img.setImageBitmap(bitmap);
			Log.v("DEBUG_LOG", "In onPostExecute");
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			//Toast.makeText(getApplicationContext(), "onProgressUpdate", Toast.LENGTH_LONG).show();
			//setProgress(progress[0]);
			Log.v("DEBUG_LOG", "In onProgressUpdate");
			super.onProgressUpdate(progress);
		}

	}*/
}
