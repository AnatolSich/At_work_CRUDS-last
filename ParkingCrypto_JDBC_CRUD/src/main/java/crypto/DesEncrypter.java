package crypto;

import javax.crypto.*;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Класс реализующий работу с алгоритмом шифрования DES
 *
 * @author Rumoku
 */
class DesEncrypter {
    Cipher ecipher;
    Cipher dcipher;
    private static String pathKeystore = "D:\\Work\\Workspace\\CRUDs-last\\ParkingCrypto_JDBC_CRUD\\src\\main\\java\\crypto\\Cryptor.java\\keystore";
    /**
     * Конструктор
     *
     * @param key секретный ключ алгоритма DES. Экземпляр класса SecretKey
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    public DesEncrypter(SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }

    /**
     * Функция шифрования
     *
     * @param str строка открытого текста
     * @return зашифрованная строка в формате Base64
     */
    public String encrypt(String str) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        byte[] utf8 = str.getBytes("UTF8");
        byte[] enc = ecipher.doFinal(utf8);
        return new sun.misc.BASE64Encoder().encode(enc);
    }

    /**
     * Функция расшифрования
     *
     * @param str зашифрованная строка в формате Base64
     * @return расшифрованная строка
     */
    public String decrypt(String str) throws IOException, IllegalBlockSizeException, BadPaddingException {
        byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
        byte[] utf8 = dcipher.doFinal(dec);
        return new String(utf8, "UTF8");
    }

    /**
     * Функция для проверки правильности работы класса
     */
    public static void main(String[] s) throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, CertificateException, KeyStoreException, UnrecoverableEntryException {


        SecretKey key = null;
        key = KeyGenerator.getInstance("DES").generateKey();
        DesEncrypter encrypter = new DesEncrypter(key);
        String OStr1 = "simple string";
        String SStr = encrypter.encrypt(OStr1);
        String OStr2 = encrypter.decrypt(SStr);
        System.out.println("Open String:" + OStr1 +
                "\nAfter encripting: " + SStr + "\nAfter decripting: " + OStr2);


        // Создание объекта keyStore
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        KeyStore keyStore3 = KeyStore.getInstance("PKCS12");
        KeyStore ks = KeyStore.getInstance("JCEKS");

        //загрузка объекта keyStore по паролю из файла и нового
        char[] keyStorePassword = "123abc".toCharArray();
        try (InputStream keyStoreData = new FileInputStream("keystore.ks")) {
            keyStore.load(keyStoreData, keyStorePassword);
        }

        keyStore3.load(null, keyStorePassword);

        //получение secretKey из объекта keyStore по паролю к Entry - keyPassword, по имени alias - "keyAlias", и по паролю к  alias - entryPassword
        char[] keyPassword = "789xyz".toCharArray();
        KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(keyPassword);

        KeyStore.SecretKeyEntry keyEntry = (KeyStore.SecretKeyEntry) keyStore3.getEntry("keyAlias", entryPassword);

        SecretKey secretKey = keyEntry.getSecretKey();

        // Создание новых ключей с пом javax.crypto.KeyGenerator
        SecretKey key1 = KeyGenerator.getInstance("AES").generateKey();
        SecretKey secretKey2 = KeyGenerator.getInstance("DES").generateKey();

        //сохранение ключа в secretKeyEntry и далее в объекте keyStore в alias с именем "keyAlias2" и задание alias пароля - entryPassword
        KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);

        keyStore3.setEntry("keyAlias2", secretKeyEntry, entryPassword);

        //сохранение объекта keyStore в файл с заданием пароля keyStorePassword2
        char[] keyStorePassword2 = "123abc".toCharArray();
        try (FileOutputStream keyStoreOutputStream = new FileOutputStream("data/keystore.ks")) {
            keyStore3.store(keyStoreOutputStream, keyStorePassword);
        }

        //получение энкриптора и дескриптора, кот-е получают имя алгоритма шифрования
        Cipher ecipher = Cipher.getInstance("DES");
        Cipher dcipher = Cipher.getInstance("DES");

        //инициализация экземпляров полученных классов и указание режима их работы
        //передаваемый параметр имеет тип javax.crypto.SecretKey
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);

        //Одиночное шифрование или расшифрование выполняет функция doFinal класса Cipher,
        // которая на входе получает масив байт и возвращает также масив байт
        byte[] utf8 = "str".getBytes("UTF8");
        byte[] enc = ecipher.doFinal(utf8);
        String fin = new sun.misc.BASE64Encoder().encode(enc);

        byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(fin);
        byte[] utf82 = dcipher.doFinal(dec);
        String str = new String(utf82, "UTF8");

        //Множественное шифрование или расшифрование выполняет функция update класса Cipher
        byte[] data1 = "abcdefghijklmnopqrstuvwxyz".getBytes("UTF-8");
        byte[] data2 = "zyxwvutsrqponmlkjihgfedcba".getBytes("UTF-8");
        byte[] data3 = "01234567890123456789012345".getBytes("UTF-8");

        byte[] cipherText1 = ecipher.update(data1);
        byte[] cipherText2 = ecipher.update(data2);
        byte[] cipherText3 = ecipher.doFinal(data3);

        byte[] plainText1 = dcipher.update(cipherText1);
        byte[] plainText2 = dcipher.update(cipherText2);
        byte[] plainText3 = dcipher.doFinal(cipherText3);

        //Экземпляры энкриптора и дескриптора после вызова метода doFinal приводятся в исходное состояние
        //они используются повторно

        Cipher cipher = Cipher.getInstance("AES");

        Key key3 = KeyGenerator.getInstance("AES").generateKey();
        cipher.init(Cipher.ENCRYPT_MODE, key3);

        byte[] data5 = "abcdefghijklmnopqrstuvwxyz".getBytes("UTF-8");
        byte[] data6 = "zyxwvutsrqponmlkjihgfedcba".getBytes("UTF-8");

        byte[] cipherText5 = cipher.update(data5);
        byte[] cipherText6 = cipher.doFinal(data6);

        byte[] data7 = "01234567890123456789012345".getBytes("UTF-8");
        byte[] cipherText7 = cipher.doFinal(data7);

/*
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(null, null);
        KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(key);
        ks.setEntry("mykey", skEntry, new KeyStore.PasswordProtection("mykeypassword".toCharArray()));

        FileOutputStream fos = new FileOutputStream("agb50.keystore");

        ks.store(fos, "somepassword".toCharArray());
        fos.close();*/


    }
}
