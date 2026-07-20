package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;

import xxl.core.exception.UnrecognizedEntryException;


/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {
  private Form _form;

  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
    _form = new Form();
    addIntegerField("lines", Message.lines());
    addIntegerField("columns", Message.columns());
  }

  @Override
  protected final void execute() throws CommandException {
    try {
      _form.parse();
      Integer lines = integerField("lines");
      Integer columns = integerField("columns");
      _receiver.createNewSpreadsheet(lines,columns);
    } catch (UnrecognizedEntryException e) {
      throw new RuntimeException(e);
    }

  }
}