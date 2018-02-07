package crypto;

import java.io.File;
import java.io.IOException;

public class Test {
    private static String pathKeystore = "D:\\Work\\Workspace\\CRUDs-last\\ParkingCrypto_JDBC_CRUD\\src\\main\\java\\crypto\\keystore.ks";
    public static void main(String[] args) {

        File fileKeyStore = new File(pathKeystore);

        try {
            fileKeyStore.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
