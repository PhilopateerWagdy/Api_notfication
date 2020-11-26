public class Notification {

	private int NotificationID;
	private string massage;
	private Content content;

	public int getNotificationID() {
		return this.NotificationID;
	}

	public string getMassage() {
		return this.massage;
	}

	public void setMassage(string massage) {
		this.massage = massage;
	}

	public Content getContent() {
		return this.content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

}