package dao;

import crypto.Cryptor;
import crypto.KeyStoreInit;
import model.Owner;
import util.ConnectDB;

import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OwnerDB {
    private Connection connection;
    private Cryptor cryptor;

    public OwnerDB() {
        this.connection = ConnectDB.getConnection();
        try {
            this.cryptor = new Cryptor(KeyStoreInit.getSecretKey());
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public List<Owner> getAllOwners() {
        List<Owner> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM owners ORDER BY id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while ((resultSet.next())) {

                Owner owner = null;
                try {
                    owner = new Owner(resultSet.getInt(1), cryptor.decryptor(resultSet.getString(2)));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                list.add(owner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Owner getOwnerById(int id) {
        Owner owner = new Owner();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM owners WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while ((resultSet.next())) {
               owner.setId(resultSet.getInt(1));
                try {
                    owner.setName(cryptor.decryptor(resultSet.getString(2)));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owner;
    }
    public String getOwnerNameById(int id) {
        String name="";
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT name FROM owners WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while ((resultSet.next())) {
                try {
                    name=cryptor.decryptor(resultSet.getString(1));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public void addOwner(Owner owner){
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO owners (name) VALUES (?)");
            String name = cryptor.encryptor(owner.getName());
            preparedStatement.setString(1,name);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editOwner(Owner owner){
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE owners SET name=? WHERE id=?");
            preparedStatement.setString(1,cryptor.encryptor(owner.getName()));
            preparedStatement.setInt(2,owner.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOwnerById(int id){
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM owners WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
