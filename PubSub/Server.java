package PubSub;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote{

    public void subscribe(Client c, Integer channel) throws RemoteException;
    public void publish(Integer channel, Cell o) throws RemoteException;
}