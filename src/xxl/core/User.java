package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;
    private String _user;
    protected List<Spreadsheet> listaspreads = new ArrayList<>();

    private static final User instance = new User(); // Instância única de User

    private User() {
    }

    public static User createUser(String username) {
        return new User();
    }

    public void add(Spreadsheet sheet) {
        listaspreads.add(sheet);
    }

    public List<Spreadsheet> getLista() {
        return listaspreads;
    }

    public static User getInstance() {
        return instance; // Método estático para obter a instância única de User
    }
}
