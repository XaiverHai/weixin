package com.weixin.message.event;

public class LocationEvent extends BaseEvent {
	//	地理位置纬度
	private String Latitude;
	//	地理位置经度
	private String Longitude;
	//	地理位置精度
	private String Precisong;
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecisong() {
		return Precisong;
	}
	public void setPrecisong(String precisong) {
		Precisong = precisong;
	}
	
}
