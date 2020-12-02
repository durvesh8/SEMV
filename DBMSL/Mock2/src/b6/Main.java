package b6;

import com.mongodb.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost",27017);
        DB db = mongoClient.getDB("doorways");
        DBCollection mock2tickets = db.getCollection("mock2tickets");

        System.out.println("----------------Welcome to PlanetariumX----------------- ");
        Scanner scanner = new Scanner(System.in);
        // Main Object
        JSONObject ticket = new JSONObject();
        System.out.print("Enter Ticket Bearer's Name: ");
        String bearer = scanner.nextLine();
        ticket.put("Ticket Bearer",bearer);
        System.out.print("Enter Number of people: ");
        int nump = scanner.nextInt();
        scanner.nextLine();
        ticket.put("Number of people",nump);
        // Array containing objects
        JSONArray people = new JSONArray();
        for(int i=0;i<nump;i++){
            JSONObject person = new JSONObject();
            System.out.print("Enter Name of Person "+(i+1)+": ");
            String name = scanner.nextLine();
            System.out.print("Enter their age: ");
            int age = scanner.nextInt();
            if(age<3){
                i--;
                System.out.println("Children under 3 not allowed!");
                continue;
            }
            scanner.nextLine();
            person.put("name",name);
            person.put("age",age);
            people.add(person);
        }
        System.out.println("Ticket Value: "+1000*nump);
        ticket.put("people",people);
        ticket.put("Ticket value: ",1000*nump);

        BasicDBObject basicDBObject =BasicDBObject.parse(ticket.toJSONString());
        mock2tickets.insert(basicDBObject);
        System.out.println("Printing all the tickets........");
        DBCursor dbCursor = mock2tickets.find();
        while (dbCursor.hasNext()){
            System.out.println(dbCursor.next());
        }
        System.out.println("\n\n\nYour Ticket:");
        DBCursor dbCursor1 = mock2tickets.find(BasicDBObject.parse("{\"Ticket Bearer\":\""+bearer+"\"}"));
        while (dbCursor1.hasNext()){
            System.out.println(dbCursor1.next());
        }


    }
}
