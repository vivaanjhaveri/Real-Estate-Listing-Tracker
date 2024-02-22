package persistence;

// CITATION: JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Portfolio;
import org.json.JSONObject;
import java.io.*;

// Represents a writer that writes JSON representation of portfolio to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String writeDestination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String writeDestination) {
        this.writeDestination = writeDestination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(writeDestination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of portfolio to file
    public void write(Portfolio p) {
        JSONObject json = p.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
