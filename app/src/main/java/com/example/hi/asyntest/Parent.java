package com.example.hi.asyntest;


import android.app.Application;
import android.text.TextUtils;

public class Parent extends Application {
	/**
	 * 
	 * @param validateString
	 * @author Srikanth Akula
	 */
	public static final String SERVER_ERROR 	= "The server isn't responding. Please try again later. If this continues, please email us at srikanth.dondaldo@gmail.com. Thank you for your patience!";
	public static boolean validateString(String stringValidate) {
		if (TextUtils.isEmpty(stringValidate) == true
				|| stringValidate.equalsIgnoreCase("null")
				|| stringValidate.isEmpty()
				|| stringValidate.equalsIgnoreCase("")) {
			return true;
		}else
		return false;
	}
	public static  String selectedID,removedID;

}
