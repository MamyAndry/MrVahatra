package affichage;

import java.io.File;
import bdd.*;
import data.Analyzer;

import java.util.*;
import java.io.*;

public class Main {
    public static void ligne(int lim){
        String ligne = "__________";
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
    public static void main(String[] args) {
        // System.out.print("database = ");
        // Scanner scan = new Scanner(System.in);
        // String database = scan.next();
        Database db = new Database("root");
        // db.useDatabase(database);
        while(true){
            //QUERY SCANNER 
                // System.out.print(db.getDbName()+" => ");
                System.out.print("SGBD => ");
                Scanner sc = new Scanner(System.in);
                String query = sc.nextLine();
                // Vector<String> col = new Vector<String>();
                // col.add("prenom");
                // col.add("age");
            // TEST
                // Vector<Vector<Object>> data_list = new Vector<Vector<Object>>();
                // Vector<Object> data = new Vector<Object>();
                // data.add("Tsy");
                // data.add("Mamisoa");
                // data_list.add(data);
                // File f = new File("D:/Table/test.bdd");
                // db.createDatabase("layah");
                // db.scanFolder();
                // db.createTable("fo",col);
                // db.insert("fo", data_list);
                // db.createDatabase("layah");
                // System.out.println(db.getRelation().size());
                // Vector<Relation> r = db.getRelation();
                // Relation ation = new Relation();
                try{
                   //QWERY ANALYZER 
                    Analyzer a = new Analyzer(query);
                    a.setDatabase(db);
                    Relation r = a.Analyze();
                    // System.out.println(r);
                    tableaux(r);
                    // for(int i = 0 ; i < r.getColumn().size() ; i++){
                    //     for(int j = 0 ; j < r.getData().size(); j++){
                    //         System.out.println(r.getData().get(i).get(j));
                    //     }
                    // }
                    // Relation union = Relation.difference(r.get(1),r.get(0));
                    // Relation union = Relation.Difference(r.get(0),r.get(1));
                    // for(int i = 0 ; i < union.getData().size() ; i++){
                    //     System.out.println(union.getData().get(i));
                    // }
                    // for(int i = 0 ; i < db.getRelation().get(1).getData().size() ; i++){
                    //     System.out.println(db.getRelation().get(1).getData().get(i).get(1));
                    // }
                }catch(Exception e){
                    e.printStackTrace();
                }
        }

    }
}
