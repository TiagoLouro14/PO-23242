package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;

import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;


import java.io.FileNotFoundException;


/**
 * Open existing file.
 */
class DoOpen extends Command<Calculator> {

  private Form _form;
  DoOpen(Calculator receiver) {
    super(Label.OPEN, receiver);
    _form = new Form();
    _form.addStringField("filename",Message.openFile());
  }

  @Override
  protected final void execute() throws FileOpenFailedException {
    try {
      _form.parse();
      String filename = _form.stringField("filename");
      _receiver.load(filename);

    } catch (UnavailableFileException e){
      e.printStackTrace(System.out);
      throw new FileOpenFailedException(e);
    }

  }

}


