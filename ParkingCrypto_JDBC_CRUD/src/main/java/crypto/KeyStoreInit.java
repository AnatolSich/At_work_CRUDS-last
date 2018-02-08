package crypto;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import com.sun.xml.internal.fastinfoset.algorithm.HexadecimalEncodingAlgorithm;
import sun.misc.HexDumpEncoder;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;

public class KeyStoreInit {
    private static String pathKeyStore = "D:\\Work\\Workspace\\CRUDs-last\\ParkingCrypto_JDBC_CRUD\\src\\main\\java\\crypto\\keystore.ks";
    private static char[] keyStorePassword = "123abc".toCharArray();
    private static char[] keyPassword = "789xyz".toCharArray();
    private static String keyAlias = "keyAlias";
    private static SecretKey secretKey;



    static {
        try {
            secretKey = createKey();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
        }
    }
    public static SecretKey getSecretKey() {
        return secretKey;
    }

    private static SecretKey createKey() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableEntryException {
        SecretKey secretKey = null;
        File fileKeyStore = new File(pathKeyStore);
        KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(keyPassword);
        KeyStore keyStore = KeyStore.getInstance("JCEKS");

        if (!fileKeyStore.exists()) {


                fileKeyStore.createNewFile();







                keyStore.load(null, keyStorePassword);



                secretKey = KeyGenerator.getInstance("AES").generateKey();


            KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);




                keyStore.setEntry(keyAlias, secretKeyEntry, entryPassword);


            try (FileOutputStream keyStoreOutputStream = new FileOutputStream(pathKeyStore)) {
                keyStore.store(keyStoreOutputStream, keyStorePassword);

            } catch (IOException e) {
                e.printStackTrace();
            }

            KeyStore.Entry entryOut =  keyStore.getEntry(keyAlias, entryPassword);



        } else {


            try (InputStream keyStoreData = new FileInputStream(pathKeyStore)) {
                keyStore.load(keyStoreData, keyStorePassword);
            } catch (IOException e) {
                e.printStackTrace();
            }

            KeyStore.Entry entry =  keyStore.getEntry(keyAlias, entryPassword);

            KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) entry;

            secretKey = secretKeyEntry.getSecretKey();
        }

        return secretKey;
    }
}
