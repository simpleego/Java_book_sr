public class Location {
	private String city;
	private double longitude; // �浵
	private double latitude; // ����
	
	public Location(String city, double longitude, double latitude) {
		this.city = city;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public void setCity(String city) { 
		this.city = city;
	}
	public String getCity() {
		return city;
	}
	public void setLogitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLatitude() {
		return latitude;
	}
}
