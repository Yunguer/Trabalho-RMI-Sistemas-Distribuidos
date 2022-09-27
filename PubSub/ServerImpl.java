package PubSub;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ServerImpl implements Server {

    HashMap<Integer, Cell> objs = new HashMap<Integer, Cell>();
    HashMap<Integer, HashSet<Client>> clientes = new HashMap<Integer, HashSet<Client>>();

    public void subscribe(Client c, Integer channel) {
        HashSet<Client> hs = clientes.get(channel);
        if (hs == null)
            hs = new HashSet<Client>();
        hs.add(c);
        clientes.put(channel, hs);

        Cell o = objs.get(channel);
        if (o != null) {
            try {
                Registry registry = LocateRegistry.getRegistry();
                Client stub = (Client) registry.lookup(c.getName());
                stub.receive(o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void publish(Integer channel, Cell o) {
        objs.put(channel, o);

        HashSet<Client> subs = clientes.get(channel);

        for (Client c : subs) {
            try {
                Registry registry = LocateRegistry.getRegistry();
                Client stub = (Client) registry.lookup(c.getName());
                stub.receive(o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        try {
            ServerImpl obj = new ServerImpl();
            Server stub = (Server) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.getRegistry();

            registry.bind("PubSub", stub);

            System.err.println("Server ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
        }
    }
}