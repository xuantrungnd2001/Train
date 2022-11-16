package deserialize.demo;


import org.apache.tomcat.util.codec.binary.Base64;

import java.io.*;

public class Serial {

    public static Object fromBase64(String s) throws IOException, ClassNotFoundException {
        try {
            byte[] data = new Base64().decode(s);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            Object o = ois.readObject();
            ois.close();
            return o;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static String toBase64(Serializable o) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.close();
            return new Base64().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}