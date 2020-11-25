package mongojavacon;

import com.mongodb.client.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
        MongoDatabase db = mongoClient.getDatabase("doorways");
        MongoCollection<Document> teachers = db.getCollection("teachers");
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------Welcome to Teacher Database!-----------");
        int choice = 0;
        while(choice!=8){
            System.out.println("0. View Selective  1. View all  2. Insert One  3. Insert Many  4. Update One\n5. Update Many" +
                    "  6. Delete One  7. Delete Many  8. Exit");
            choice = scanner.nextInt();
            switch (choice){
                case 0:
                    scanner.nextLine();
                    Document filterfind=new Document();
                    System.out.println("Enter Field you want to match");
                    String matchfieldfind = scanner.nextLine();
                    System.out.println("Enter the value of the field you want to match");
                    String matchvaluefind = scanner.nextLine();
                    filterfind.put(matchfieldfind,matchvaluefind);
                    MongoCursor<Document> cursor1 = teachers.find(filterfind).iterator();
                    while (cursor1.hasNext()){
                        System.out.println(cursor1.next().toJson());
                    }
                    break;
                case 1:
                    MongoCursor<Document> cursor = teachers.find().iterator();
                    while (cursor.hasNext()){
                        System.out.println(cursor.next().toJson());
                    }
                    break;
                case 2:
                    scanner.nextLine();
                    String field="";
                    String value;
                    Document docinsert=new Document();
                    while (true){
                        System.out.println("Enter Field name or enter exit");
                        field = scanner.nextLine();
                        if(field.equals("exit"))
                            break;
                        System.out.println("Enter field value");
                        value = scanner.nextLine();
                        docinsert.put(field,value);
                    }
                    teachers.insertOne(docinsert);
                    break;
                case 3:
                    ArrayList<Document> objects = new ArrayList<>();
                    System.out.println("Enter number of records you want to enter");
                    int numrec = scanner.nextInt();
                    scanner.nextLine();
                    for(int i=0;i<numrec;i++){
                        System.out.println("Record no. "+ i);
                        String fieldn="";
                        String valuen;
                        Document docu = new Document();
                        while (true){
                            System.out.println("Enter Field name or enter exit");
                            fieldn = scanner.nextLine();
                            if(fieldn.equals("exit"))
                                break;
                            System.out.println("Enter field value");
                            valuen = scanner.nextLine();
                            docu.put(fieldn,valuen);
                        }
                        objects.add(docu);
                    }
                    teachers.insertMany(objects);
                    break;
                case 4:
                    scanner.nextLine();
                    Document filter=new Document();
                    System.out.println("Enter Field you want to match");
                    String matchfield = scanner.nextLine();
                    System.out.println("Enter the value of the field you want to match");
                    String matchvalue = scanner.nextLine();
                    filter.put(matchfield,matchvalue);
                    System.out.println("Enter the Field you want to update");
                    String updatefield = scanner.nextLine();
                    System.out.println("Enter the updated value for the field above");
                    String updatevalue = scanner.nextLine();
                    teachers.updateOne(filter, Updates.set(updatefield,updatevalue));
                    break;
                case 5:
                    scanner.nextLine();
                    Document filtermult=new Document();
                    System.out.println("Enter Field you want to match");
                    String matchfieldmult = scanner.nextLine();
                    System.out.println("Enter the value of the field you want to match");
                    String matchvaluemult = scanner.nextLine();
                    filtermult.put(matchfieldmult,matchvaluemult);
                    System.out.println("Enter the Field you want to update");
                    String updatefieldmult = scanner.nextLine();
                    System.out.println("Enter the updated value for the field above");
                    String updatevaluemult = scanner.nextLine();
                    teachers.updateMany(filtermult,Updates.set(updatefieldmult,updatevaluemult));
                    break;
                case 6:
                    scanner.nextLine();
                    System.out.println("Enter Field you want to match");
                    String matchfielddelete = scanner.nextLine();
                    System.out.println("Enter the value of the field you want to match");
                    String matchvaluedelete = scanner.nextLine();
                    Document filterdelete = new Document();
                    filterdelete.put(matchfielddelete,matchvaluedelete);
                    teachers.deleteOne(filterdelete);
                    break;
                case 7:
                    scanner.nextLine();
                    System.out.println("Enter Field you want to match");
                    String matchfielddeletemult = scanner.nextLine();
                    System.out.println("Enter the value of the field you want to match");
                    String matchvaluedeletemult = scanner.nextLine();
                    Document filterdeletemult = new Document();
                    filterdeletemult.put(matchfielddeletemult,matchvaluedeletemult);
                    teachers.deleteMany(filterdeletemult);
                    break;
                case 8:

                    break;
            }
        }
    }
}
