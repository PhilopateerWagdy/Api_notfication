import java.util.ArrayList;

public class CRUD implements NotificationManger {

	private static int id = 0;

	/**
	 * 
	 * @param data
	 */
	public Notification create(String[] data,Content content) {
		// TODO - implement CRUD.create
		int dataSize= data.length;

		Notification temp =new Notification();
		temp.setNotificationID(id++);


		String message=content.Massage();

		int count = 0,safetyCount=0;

		for (int i = 0; i < message.length() - 1; i++) {
			if (message.charAt(i)=='{') {
				if(safetyCount<dataSize){
					safetyCount++;
				}
				else{
					throw new UnsupportedOperationException();
				}
				message = message.substring(0,i)+data[count++]+message.substring(i+3);
			}
		}
		temp.setMassage(message);
		temp.setContent(content);
		return temp;
	}

	/**
	 * 
	 * @param notification
	 * @param data
	 */
	public Notification update(Notification notification, String[] data) {
		// TODO - implement CRUD.update
		String message=notification.getContent().Massage();
		int count = 0,safetyCount=0;
		int dataSize= data.length;

		for (int i = 0; i < message.length() - 1; i++) {
			if (message.charAt(i)=='{') {
				if(safetyCount<dataSize){
					safetyCount++;
				}
				else{
					throw new UnsupportedOperationException();
				}
				message = message.substring(0,i)+data[count++]+message.substring(i+3);
			}
		}
		notification.setMassage(message);
		return notification;

	}

	/**
	 * 
	 * @param notification
	 */
	public String read(Notification notification) {
		// TODO - implement CRUD.read
		return notification.getMassage();
	}

	/**
	 * 
	 * @param notifications
	 * @param ID
	 */
	public void delete(ArrayList<Notification> notifications, int ID) {
		// TODO - implement CRUD.delete
		int index=-1;
		for (int i = 0; i < notifications.size(); i++) {
			if (notifications.get(i).getNotificationID()==ID){
				index=i;
				break;
			}
		}
		if (index==-1)
			System.out.println("Wrong Notification ID");
		else
			notifications.remove(index);
	}

}