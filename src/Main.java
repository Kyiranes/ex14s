import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("JSON with gson under the sun");
        deserializeSimple();
        serializeSimple();
    }
    static void serializeSimple() {
        Todos losdias = new Todos("Walk the Dog", false, 0, 3, "dog");
        Todos secondEvil = new Todos("Pay the Bills", false, 1, 1, "bills");
        ArrayList<Todos> todosList = new ArrayList<>();
        todosList.add(losdias);
        todosList.add(secondEvil);
        Gson gson = new Gson();
        try(FileWriter writer = new FileWriter("data.json")){
            gson.toJson(todosList, writer);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
    static void deserializeSimple() {
        try(FileReader reader = new FileReader("data.json")){
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(reader);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Todos>>(){}.getType();
            ArrayList<Todos> losdias = gson.fromJson(element, type);
            System.out.println(losdias);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
class Simple{
    private final String name;
    private final String email;
    private final int age;
    private final boolean isDev;
    public Simple(String name, String email, int age, boolean isDev) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.isDev = isDev;
    }



    @Override
    public String toString() {
        return "Simple{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", isDev=" + isDev +
                '}';
    }


}
class Todos{
    private final String body;
    private final boolean done;
    private final int id;
    private final int priority;
    private final String title;
    @Override
    public String toString() {
        return "Todos{" +
                "body=" + body +
                ", done=" + done +
                ", id=" + id +
                ", priority=" + priority +
                ", title=" + title +
                '}';
    }
    public Todos(String body, boolean done, int id, int priority, String title) {
        this.body = body;
        this.done = done;
        this.id = id;
        this.priority = priority;
        this.title = title;
    }


}