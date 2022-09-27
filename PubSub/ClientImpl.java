package PubSub;

import java.io.Serializable;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientImpl implements Serializable, Client{

    private static final long serialVersionUID = 7526472295622776147L;
    private String serverName;

    public void receive(Cell o){
        System.err.println(o.get());
    }

    public String getName(){
        return this.serverName;
    }

    public void setName(String name){
        this.serverName = name;
    }

    public static void main(String args[]){
        int mode = -1; // 0 for subscribing and 1 for publishing
        int channel = -1; // channel to publish/subscribe


        String name, host, message = "";
        if (args.length == 3){
            host = "";
            name = args[0];
            mode = Integer.parseInt(args[1]);
            channel = Integer.parseInt(args[2]);
        }
        else{
            System.err.println (args[0] + " " + args[1] + " " + args[2]);
            host = "";
            name = "";
            System.err.println ("Usage: User(string name) Mode(0 - Subscribe; 1 - Publish;) Channel(int number)");
            System.exit(1);
        }
        if(mode == 0){
            pubSub(name, host, channel, mode, message);
        }
        while(mode == 1)
        {
            System.out.print("Digite uma mensagem...\n[" + name + "]: ");
            Scanner scanner = new Scanner(System.in);
            message = "[" + name + "]: ";
            message += scanner.nextLine();
            pubSub(name, host, channel, mode, message);
        }

    }

    private static void pubSub(String name, String host, int channel, int mode, String message)
    {
        try{
            Client c = new ClientImpl();
            c.setName(name);

            Client stubC = (Client)UnicastRemoteObject.exportObject(c, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stubC);

            registry = LocateRegistry.getRegistry(host);
            Server stub = (Server) registry.lookup("PubSub");

            if(mode == 0)
                System.err.println("Subscribing to: Channel " + channel + "\n");
            else if(mode == 1)
                System.err.println("Publishing in: Channel " + channel + "\n");

            if (channel != -1 && mode == 0){
                stub.subscribe(c, channel);
            }
            else if (channel != -1 && mode == 1){
                Cell o = new Cell();
                o.set(message);
                stub.publish(channel, o);
            }
        } catch(Exception e){
        }
    }
}