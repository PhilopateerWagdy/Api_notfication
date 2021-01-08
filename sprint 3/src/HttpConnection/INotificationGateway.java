package HttpConnection;

import model.Notification;

public interface INotificationGateway {
    public void sendingGetRequest() throws Exception;
    public void sendingGetRequest(String user) throws Exception;
    public void sendingPostRequest(Notification notification) throws Exception;
}
