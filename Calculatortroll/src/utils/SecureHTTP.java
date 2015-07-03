package utils;

import java.io.InputStream;
import java.security.KeyStore;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;

import com.codenginebd.mobilemediacms.R;

import android.content.Context;

public class SecureHTTP extends DefaultHttpClient
{
	final Context context;
	
	public SecureHTTP(Context context)
	{
		this.context = context;
	}

	@Override
	protected ClientConnectionManager createClientConnectionManager() {
		// TODO Auto-generated method stub
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		registry.register(new Scheme("https", newSslSocketFactory(), 443));
		return new SingleClientConnManager(getParams(), registry);
	}
	
	private SSLSocketFactory newSslSocketFactory() 
	{
		try
		{
			KeyStore trusted = KeyStore.getInstance("BKS");
			InputStream in = context.getResources().openRawResource(R.raw.keystorefb);
			try
			{
				trusted.load(in, "mysecret".toCharArray());
			}finally
			{
				in.close();
			}
			SSLSocketFactory sf = new SSLSocketFactory(trusted);
			sf.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
			return sf;
			
		}catch(Exception exp)
		{
			throw new AssertionError(exp);
		}
	}	
	
}
