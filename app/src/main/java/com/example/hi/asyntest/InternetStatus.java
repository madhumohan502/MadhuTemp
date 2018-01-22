package com.example.hi.asyntest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class InternetStatus {

	private static final String LOG_TAG = "NETWORK_STATUS";
	public static boolean isWifiOrMobileConnected(Context context) {

		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
		}
		return false;
	}
	public static boolean hasActiveInternetConnection(Context context) {
	    if (isWifiOrMobileConnected(context)) {
	        try {
	        	if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) {
	                StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	                StrictMode.setThreadPolicy(tp);
	            }
	            HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
	            urlc.setRequestProperty("User-Agent", "Test");
	            urlc.setRequestProperty("Connection", "close");
	            urlc.setConnectTimeout(1500); 
	            urlc.connect();
	            return (urlc.getResponseCode() == 200);
	        } catch (IOException e) {
	            Log.e(LOG_TAG, "Error checking internet connection", e);
	        }
	    } else {
	        Log.d(LOG_TAG, "No network available!");
	    }
	    return false;
	}
}
