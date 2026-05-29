package model;

public interface Publisher {
    public void attach(String subID, Subscription sub);

    public void detach(String subID);

    
}
