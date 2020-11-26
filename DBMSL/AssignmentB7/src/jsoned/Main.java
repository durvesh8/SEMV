package jsoned;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.Reader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
		System.out.println("\n\n\n");
	    JSONObject student = new JSONObject();
		System.out.print("Enter Name: ");
		String name = in.nextLine();
	    student.put("name",name);
		System.out.print("Enter Age: ");
	    int age =in.nextInt();
	    student.put("age",age);
		System.out.print("Enter marks: ");
		long marks = in.nextLong();
	    student.put("marks",marks);

		System.out.print("Encoded JSON String: ");
		String encode = student.toJSONString();
        System.out.println(encode+"\n");

        JSONObject object = (JSONObject) JSONValue.parse(encode);
		System.out.println("Decoding values from the encoded string");
		System.out.println("name: "+object.get("name"));
		System.out.println("marks: "+object.get("marks"));
		System.out.println("age: "+object.get("age")+"\n");

		String manualjson = "{\"employee name\":\"Prakash\",\"employee salary\":300000,\"employee city\":\"Mumbai\"}";
		System.out.println("Manual JSON String: "+manualjson+"\n");
		JSONObject object2 = (JSONObject) JSONValue.parse(manualjson);
		System.out.println("Decoding values from the manual json string");
		System.out.println("Employee Name: "+object2.get("employee name"));
		System.out.println("Salary: "+object2.get("employee salary"));
		System.out.println("City: "+object2.get("employee city"));
    }
}
