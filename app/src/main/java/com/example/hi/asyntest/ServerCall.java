package com.example.hi.asyntest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class ServerCall extends AsyncTask<String, String, String> {
	private String SERVER_URL;
	private Context mContext;
	private DentistKartData callback;
	private ProgressDialog pd;
	private StringBuffer response;
	private String method;

	public ServerCall(String url, Context context, String method) {
		// TODO Auto-generated constructor stub
		this.SERVER_URL = url;
		this.mContext = context;
		this.callback = (DentistKartData) context;
		this.method = method;
		Log.e(method, url);

	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		if (result != null)
			Log.e("Fetch Result", result);
		if (!Parent.validateString(result)) {
			Object json;
			try {
				json = new JSONTokener(result).nextValue();
				if (json instanceof JSONObject) {
					try {
						 if (((JSONObject) json).getInt("statuscode") == 1) {
							callback.serverData(result, method);
						} else if (method.equalsIgnoreCase("login_service")) {
							callback.serverData(result, method);
							
						}else if (method.equalsIgnoreCase("search_result")) {
							callback.serverData(result, method); 
						}else if (method.equalsIgnoreCase("coupon")){
							callback.serverData(result, method); 
						}
						else {
							Toast.makeText(mContext, ((JSONObject) json).getString("message"), Toast.LENGTH_SHORT)
									.show();

						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (json instanceof JSONArray) {
					callback.serverData(result, method);

				} else {
					Toast.makeText(mContext, "something_wrong_text2222222222222",
							Toast.LENGTH_SHORT).show();
				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else {
			Toast.makeText(mContext, "something_wrong_text333333333333333",
					Toast.LENGTH_LONG).show();
		}
//		if (pd != null && pd.isShowing()) {
//			pd.dismiss();
//			pd = null;
//		}
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
//		pd = new ProgressDialog(mContext, R.style.MyTheme);
//		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//		// pd.setMessage(mContext.getResources().getString(R.string.loading));
//		pd.setCancelable(false);
//		pd.show();
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		URL url;
		HttpURLConnection connection = null;
		int code = 0;
		try {
			url = new URL(SERVER_URL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			code = connection.getResponseCode();
            Log.e("madhu", String.valueOf(code));
            Log.e("madhu", "connection done");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Log.e("madhu", String.valueOf(code)+"==200");
		if (code == 200) {
			BufferedReader in;
			try {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				response = new StringBuffer();


				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return response.toString();

		}
		return null;
	}

}
