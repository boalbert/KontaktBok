package projektarbete.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import projektarbete.Model.WorkContact;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class ioHandling {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Should execute when we start the program
     *
     * @return HashMap populated from .json-file.
     */
    public static HashMap<String, WorkContact> loadHashMapFromJson() {
        System.out.println("Trying to populate HashMap from .json-file...");

        Type typeHashMap = new TypeToken<HashMap<String, WorkContact>>() {
        }.getType();

        try (FileReader fileReader = new FileReader("src/main/java/projektarbete/data/workcontacs.json")) {

            // Load HashMap from FileReader via Gson, we use Type parameter to specify what data we're loading
            HashMap<String, WorkContact> loadedWorkContacts = gson.fromJson(fileReader, typeHashMap);

            if (null == typeHashMap) {
                System.out.println(".json-file was empty, returning an empty HashMap.");
                loadedWorkContacts = new HashMap<>();
            }

            System.out.println("Success! Returning populated HashMap.");
            return loadedWorkContacts;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new HashMap<>();
    }


    /**
     * Should be executed on closing the program.
     *
     * @param saveHashMap to .json file
     */
    public static void saveHashMapToJson(HashMap<String, WorkContact> saveHashMap) {

        try (FileWriter fileWriter = new FileWriter("src/main/java/projektarbete/data/workcontacs.json")) {


            gson.toJson(saveHashMap, fileWriter);
            System.out.println("Writing updated HashMap to .json.");

        } catch (IOException e) {
            System.out.println("Writing to .json failed: ");
            e.printStackTrace();
        }
    }
}