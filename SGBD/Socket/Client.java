package aff;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import bdd.*;

public class Client extends Thread{
    String query;
    Socket socket;

//SETTERS
    public void setQuery(String query) {
        this.query = query;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    //GETTERS
    public String getQuery() {
        return query;
    }
    public Socket getSocket() {
        return socket;
    }

//CONSTRUCTOR
    public Client(Socket socket){
        this.setSocket(socket);
    }
    public Client(){}

//METHODS
    public static void ligne(int lim){
        String ligne = "____________";
        for(int i = 0 ; i < lim ; i ++){
            System.out.print(ligne);
        }
    }
    public static void tableaux(Relation relation){
        if(relation==null){
            System.out.println("Voatontonsa Hatakao");
            return;
        } 
        String barre = " | ";
        for(int i = 0 ; i < relation.getColumn().size() ;i++){
            System.out.print(barre+relation.getColumn().get(i)+barre); 
        }
        System.out.println("");
        ligne(relation.getColumn().size());
        System.out.println("");
        for(int i = 0 ; i < relation.getData().size() ;i++){
            for(int j = 0 ; j < relation.getData().get(i).size() ;j++){
                System.out.print(barre+relation.getData().get(i).get(j)+barre); 
            }
            System.out.println("");
            ligne(relation.getColumn().size());
            System.out.println("");
        }
    }

    public void run(){
        // Socket client = new Socket("localhost",1121);
        try{
            DataOutputStream out = new DataOutputStream(this.getSocket().getOutputStream());
            while(true){
                System.out.print("SGBD => ");
                Scanner scan = new Scanner(System.in);
                String query = scan.nextLine();
                out.writeUTF(query);
                out.flush();
                if(query.toLowerCase().equals("exit")){
                    DataInputStream input = new DataInputStream(this.getSocket().getInputStream());
                    String mess = (String) input.readUTF();
                    System.out.println(mess);
                    break;
                }
                else{
                    ObjectInputStream input = new ObjectInputStream(this.getSocket().getInputStream());
                    Relation relation = null;
                    Object obj = input.readObject();
                    if(obj != null){
                        relation = (Relation)obj;
                    }
                    tableaux(relation);
                }
            }
            out.close();
            this.getSocket().close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        Client client = new Client(new Socket("localhost",1121));
        client.start();
    }
}