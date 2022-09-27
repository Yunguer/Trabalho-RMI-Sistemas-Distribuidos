package PubSub;

import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Client extends Remote {
    public void receive(Cell o) throws RemoteException;

    public String getName() throws RemoteException;

    public void setName(String name) throws RemoteException;
}