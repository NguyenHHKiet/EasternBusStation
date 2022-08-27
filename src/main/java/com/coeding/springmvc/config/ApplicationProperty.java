package com.coeding.springmvc.config;

public class ApplicationProperty {

	public static String userName;
	public static String userEmail;
	public static String fileUploadpath;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		ApplicationProperty.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		ApplicationProperty.userEmail = userEmail;
	}
	public String getFileUploadpath() {
		return fileUploadpath;
	}
	public void setFileUploadpath(String fileUploadpath) {
		ApplicationProperty.fileUploadpath = fileUploadpath;
	}

}
