package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class Serialize {

    /**
     * @param obj
     *            Object - The object that is saved.
     * @param filename
     *            String - The filename of the file it is saved to.
     */
    public static void save(Object obj, String filename) throws IOException {
        ObjectOutputStream objstream = new ObjectOutputStream(new FileOutputStream(filename));
        objstream.writeObject(obj);
        objstream.close();
    }

    /**
     * @param filename
     *            String - The filename for the file to be loaded
     */
    public static Object load(String filename) throws Exception {
        ObjectInputStream objstream = new ObjectInputStream(new FileInputStream(filename));
        Object obj = objstream.readObject();
        objstream.close();
        return obj;
    }

    /**
     * @param args
     *            String[] - the command line arguments
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        /**
         * Trying to use serializing to save a Vector in a file. Afterward the
         * Vector is increased and then owerwriting the same file.
         */

        Vector<String> v;
        try {
            v = (Vector<String>) load("friends.ser");
            System.out.println("Read: " + v);
        } catch (Exception e) {
            System.out.println("File not found. Creating it.");
            v = new Vector<String>();
            v.addElement("Peter");
            v.addElement("John");
            v.addElement("Bryan");
            System.out.println("Created: " + v);
        }

        v.addElement("Friend" + v.size());
        try {
            save(v, "friends.ser");
            System.out.println("Saved: " + v);
            System.out.println("Saved: " + v);
        } catch (Exception e) {
            System.out.print("Error saving file: ");
            e.printStackTrace();
        }
    }
}
