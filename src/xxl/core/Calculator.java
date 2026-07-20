package xxl.core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;


import xxl.app.exception.FileOpenFailedException;
import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Class representing a spreadsheet application.
 */
public class Calculator implements Serializable{


    private final User _user = User.getInstance();
    private Spreadsheet _spreadsheet;
    private String _filename = "";

    public Calculator() {
    }

    /**
     * Return the current spreadsheet.
     *
     * @returns the current spreadsheet of this application. This reference can be null.
     */
    public final Spreadsheet getSpreadsheet() {
        // If the user creates the spreadsheet and saves it will return it
        if(!_user.getLista().isEmpty())
            for(Spreadsheet s : _user.getLista()){
                return s;
            }
        // if the user imports a spreadsheet it will return it from here
        return _spreadsheet;
    }

    /**
     * Saves the serialized application's state into the file associated to the current network.
     *
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException                     if there is some error while serializing the state of the network to disk.
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException {
        try (ObjectOutputStream obOut = new ObjectOutputStream(new FileOutputStream(_filename))) {
            obOut.writeObject(_spreadsheet);
        }catch(FileNotFoundException e){
            e.printStackTrace();

        }catch(IOException e){
            throw new MissingFileAssociationException();
        }
    }

    /**
     * Saves the serialized application's state into the specified file. The current network is
     * associated to this file.
     *
     * @param filename the name of the file.
     * @throws MissingFileAssociationException if the current network does not have a file.
     */
    public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, FileOpenFailedException {
        _filename = filename;
        try (ObjectOutputStream obOut = new ObjectOutputStream(new FileOutputStream(filename))) {
            obOut.writeObject(_spreadsheet);
            _user.add(_spreadsheet);
        }catch(FileNotFoundException e){
            e.printStackTrace();

        }catch (IOException e) {
            e.printStackTrace(System.out);
            throw new FileOpenFailedException(e);
        }

    }



    public void load(String filename) throws UnavailableFileException {
        try(ObjectInputStream obOut = new ObjectInputStream(new FileInputStream(filename))){
            _spreadsheet = (Spreadsheet) obOut.readObject();
            _filename = filename;
            _user.add(_spreadsheet);


        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace(System.out);
            throw new UnavailableFileException(filename);
        }

    }

    public void importFile(String filename) throws ImportFileException, IOException, UnrecognizedEntryException {
        Parser parser = new Parser(_spreadsheet);
        try {
            _spreadsheet = parser.parseFile(filename);
            if (_spreadsheet != null) {
                _user.add(_spreadsheet);

            } else {
                System.out.println("Não foi possível obter a planilha importada.");
            }
        } catch (IOException | UnrecognizedEntryException e) {
            e.printStackTrace();
        }
    }

    public Spreadsheet createNewSpreadsheet(int rows,int columns) throws UnrecognizedEntryException {
        _spreadsheet = new Spreadsheet(new TwoDimensionalArrayStorage(rows,columns));
        _user.add(_spreadsheet);
        return _spreadsheet;
    }

    public boolean findFile(){
        return !this._filename.isEmpty();
    }

    public boolean createUser(User u) {
        return _user != u;
    }

    public User getUser() {
        return _user;
    }
}
