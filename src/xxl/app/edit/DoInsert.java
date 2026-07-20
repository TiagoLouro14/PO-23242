package xxl.app.edit;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;
// FIXME import classes

/**
 * Class for inserting data.
 */
class DoInsert extends Command<Spreadsheet> {
  private Form _form = new Form();

  DoInsert(Spreadsheet receiver) {
    super(Label.INSERT, receiver);
    addStringField("content", Message.contents());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String content = stringField("content");

  }
}
