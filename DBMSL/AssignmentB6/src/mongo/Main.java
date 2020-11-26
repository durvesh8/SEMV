package mongo;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.*;
import java.io.*;


public class Main {

    public static void main(String[] args) {
        // Inserting plain key-value pairs
        JSONObject teacher = new JSONObject();
        teacher.put("name","Dinesh Marathe");
        teacher.put("qualification","MSc");
        teacher.put("deptname","EnTC");

        // Inserting Array of value-pairs
        JSONArray conferences = new JSONArray();
        conferences.put("CVPR");
        conferences.put("ICC");
        conferences.put("ICML");

        // nested Document
        JSONObject salary = new JSONObject();
        salary.put("basic",400000);
        salary.put("TA",20000);
        salary.put("DA",40000);
        salary.put("HRA",30000);

        // Inserting array and nested document
        teacher.put("conferences",conferences);
        teacher.put("salary",salary);

        System.out.println("JSON Object Finalized");
        System.out.println("teacher");

        // Connecting to the database
        MongoClient mongoClient = new MongoClient("localhost",27017);
        DB db = mongoClient.getDB("doorways");
        DBCollection teachers = db.getCollection("teachers");


        // Inserting the teacher document
        BasicDBObject bson = BasicDBObject.parse(teacher.toJSONString());
        teachers.insert(bson);

        // Printing out all the documents in teachers collection
        DBCursor cursor = teachers.find();
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
        System.out.println("");

        // Printing out one of the documents
        bson = BasicDBObject.parse("{\"salary.basic\":300000}");
        cursor = teachers.find(bson);
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }
}
