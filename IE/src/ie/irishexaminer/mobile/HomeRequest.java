package ie.irishexaminer.mobile;

import ie.landmarkdigital.shared.Jackson;
import ie.landmarkdigital.shared.models.Article;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.stiggpwnz.asynchttpclient.ErrorListener;
import com.stiggpwnz.asynchttpclient.MultiRequest;
import com.stiggpwnz.asynchttpclient.MultiResponseListener;

public class HomeRequest extends MultiRequest<List<Article>> {

	public HomeRequest(List<String> urls, MultiResponseListener<List<Article>> listener, ErrorListener errorListener, Object tag) {
		super(Method.GET, urls, listener, errorListener, tag);
	}

	@Override
	protected List<Article> parseNetworkResponse(String data) throws Exception {
		return Jackson.OBJECT_MAPPER.readValue(data, new TypeReference<List<Article>>() {
		});
	}

	@Override
	protected void deliverResponses(List<List<Article>> responses, long updated) {
		getMultiListener().onResponse(responses, updated);
	}
}
