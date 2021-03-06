package com.blundell.tut.service.task;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import com.blundell.tut.domain.Library;
import com.blundell.tut.parser.YouTube;
import com.blundell.tut.parser.YouTubeException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * This is the task that will ask YouTube for a list of videos for a specified user</br>
 * This class implements Runnable meaning it will be ran on its own Thread</br>
 * Because it runs on it's own thread we need to pass in an object that is notified when it has finished
 *
 * @author paul.blundell
 */
public class GetYouTubeUserVideosTask implements Runnable {
	// A reference to retrieve the data when this task finishes
	public static final String LIBRARY = "Library";
	// A handler that will be notified when the task is finished
	private final Handler replyTo;
	// The user we are querying on YouTube for videos
	private final String username;

	/**
	 * Don't forget to call run(); to start this task
	 * @param replyTo - the handler you want to receive the response when this task has finished
	 * @param username - the username of who on YouTube you are browsing
	 */
	public GetYouTubeUserVideosTask(Handler replyTo, String username) {
		this.replyTo = replyTo;
		this.username = username.replaceAll("[^\\w\\s]","").replace("  "," ");
	 }

	@Override
	public void run() {
		try {
			// Get a httpclient to talk to the internet
			
		//	HttpClient client = new DefaultHttpClient();
			//username = username.replaceAll("[^\\w\\s]",""); 
			// Perform a GET request to YouTube for a JSON list of all the videos by a specific user
			String s="https://gdata.youtube.com/feeds/api/videos?q="+username.replace(" ","+")+"&orderby=published&category=entertainment&v=2&alt=jsonc";
			Log.d("kd",s+"");
		
			
			
			
		/*	HttpUriRequest request = new HttpGet(s);
			// Get the response that YouTube sends back
			HttpResponse response = client.execute(request);
			// Convert this response into an inputstream for the parser to use
			InputStream atomInputStream = response.getEntity().getContent();

		*/	// Load up the parser with the atom xml retrieved from youtube
			//AtomParser parser = new AtomPullParser(atomInputStream); // or use AtomSaxParser(atomInputStream);

			// load  the video objects into our list
		//	List<Video> videos = parser.getVideos();
			ArrayList<com.blundell.tut.parser.Video> videos = new YouTube().getVideos(s);
			// Create a library to hold our videos
			Library lib = new Library(username, videos);
			// Pack the Library into the bundle to send back to the Activity
			Bundle data = new Bundle();
			data.putSerializable(LIBRARY, lib);

			// Send the Bundle of data (our Library) back to the handler (our Activity)
			Message msg = Message.obtain();
			msg.setData(data);
			replyTo.sendMessage(msg);

		// We don't do any error catching, just nothing will happen if this task falls over
		// an idea would be to reply to the handler with a different message so your Activity can act accordingly
		} catch (YouTubeException e) {
			// TODO Auto-generated catch block
			
		}
	}
}