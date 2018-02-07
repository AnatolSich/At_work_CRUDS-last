package crypto;

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
    private static SecretKey secretKey = createKey();

    public static SecretKey getSecretKey() {
        return secretKey;
    }

    private static SecretKey createKey() {
        SecretKey secretKey = null;
        File fileKeyStore = new File(pathKeyStore);
        KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(keyPassword);
        KeyStore.ProtectionParameter entryPassword2 = new KeyStore.PasswordProtection(keyPassword);

        if(entryPassword==entryPassword2) System.out.println("entryPassword:ONE OBJECT");
        if(entryPassword.equals(entryPassword2)) System.out.println("entryPassword:EQUAL");
        if(((KeyStore.PasswordProtection)entryPassword).getPassword().equals(((KeyStore.PasswordProtection)entryPassword2).getPassword()))
            System.out.println("entryPassword.Password:EQUAL");
        if (entryPassword == null) System.out.println("entryPassword:NULL");
        else System.out.println("entryPassword:NOT NULL");
        if (!( entryPassword instanceof PrivateKey)) System.out.println("entryPassword:NOT PRIVATE KEY");

        if (!fileKeyStore.exists()) {

            try {
                fileKeyStore.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            KeyStore keyStore = null;

            try {
                keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }


            try {
                keyStore.load(null, keyStorePassword);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            }


            try {
                secretKey = KeyGenerator.getInstance("AES").generateKey();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            if (secretKey == null) System.out.println("secretKey:NULL");
            else System.out.println("secretKey:NOT NULL");

            if (!( secretKey instanceof PrivateKey)) System.out.println("secretKey:NOT PRIVATE KEY");

            KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);



            if (secretKeyEntry == null) System.out.println("secretKeyEntry:NULL");
            else System.out.println("secretKeyEntry:NOT NULL");

            try {
                keyStore.setEntry(keyAlias, secretKeyEntry, entryPassword);
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }




            try (FileOutputStream keyStoreOutputStream = new FileOutputStream(pathKeyStore)) {
                keyStore.store(keyStoreOutputStream, keyStorePassword);
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            KeyStore.Entry entryOut = null;

            try {
                entryOut = keyStore.getEntry(keyAlias, entryPassword);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnrecoverableEntryException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }

            if (entryOut == null) System.out.println("entryOut: NULL");
            else System.out.println("entryOut:NOT NULL");

        } else {
            KeyStore keyStore = null;

            try {
                keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }

            try (InputStream keyStoreData = new FileInputStream(pathKeyStore)) {
                keyStore.load(keyStoreData, keyStorePassword);
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            KeyStore.Entry entry = null;

            try {
                entry = keyStore.getEntry(keyAlias, entryPassword);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnrecoverableEntryException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
            if (entry == null) System.out.println("entry: NULL");
            KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) entry;
            if (secretKeyEntry == null) System.out.println("secretKeyEntry: NULL");
            secretKey = secretKeyEntry.getSecretKey();
        }

        return secretKey;
    }
}
