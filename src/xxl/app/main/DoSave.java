package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
import xxl.core.exception.MissingFileAssociationException;

import java.io.FileNotFoundException;


/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {
  private Form _form;

  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
    _form = new Form();
    addStringField("filename", Message.newSaveAs());
  }

  @Override
  protected final void execute() throws FileOpenFailedException {
    try{
      if(_receiver.findFile()){_receiver.save();

      }else{
        _form.parse();
        String filename = stringField("filename");
        _receiver.saveAs(filename);
      }
    }catch(FileNotFoundException | MissingFileAssociationException e){
      throw new FileOpenFailedException(e);
    }
  }

}


