package it.benvenutobertacco;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by benvenoide on 14/06/17.
 */
public class Test {

    public static void main(String[] args) {

        try (Reader reader = new FileReader("testmessages.json")) {

            MessageManager messageManager = new MessageManager(reader);

            messageManager.run();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
