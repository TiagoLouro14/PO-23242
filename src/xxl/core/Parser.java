package xxl.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;


import xxl.core.exception.UnrecognizedEntryException;

class Parser {

  private Spreadsheet _spreadsheet;
  private final Calculator _cal = new Calculator();
  private final User _user = User.getInstance();


  Parser() throws UnrecognizedEntryException {
  }

  Parser(Spreadsheet spreadsheet) throws UnrecognizedEntryException {
    _spreadsheet = spreadsheet;
  }

  Spreadsheet parseFile(String filename) throws IOException, UnrecognizedEntryException /* More Exceptions? */ {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      parseDimensions(reader);
      String line;
    
      while ((line = reader.readLine()) != null)
        parseLine(line);
    }
    _user.add(_spreadsheet);
    return _spreadsheet;
  }

  private void parseDimensions(BufferedReader reader) throws IOException, UnrecognizedEntryException, NumberFormatException {
    int rows = -1;
    int columns = -1;

    for (int i = 0; i < 2; i++) {

      String[] dimension = reader.readLine().split("=");
      if (dimension[0].equals("linhas"))
        rows = Integer.parseInt(dimension[1]);
      else if (dimension[0].equals("colunas"))
        columns = Integer.parseInt(dimension[1]);
      else
        throw new NumberFormatException();
    }

    if (rows <= 0 || columns <= 0)
      throw new UnrecognizedEntryException("Dimensões inválidas para a folha");

    _spreadsheet = _cal.createNewSpreadsheet(rows,columns);
  }

  private void parseLine(String line) throws UnrecognizedEntryException /*, more exceptions? */{
    String[] components = line.split("\\|");


    if (components.length == 2) {
      String[] address = components[0].split(";");
      Content content = parseContent(components[1]);
      _spreadsheet.insertContent(Integer.parseInt(address[0]), Integer.parseInt(address[1]), content);
      _spreadsheet.getSpreadsheet().getCutBuffer().getCell(Integer.parseInt(address[0]),Integer.parseInt(address[1])).setExpression(components[1]);

    } else
      throw new UnrecognizedEntryException("Wrong format in line: " + line);
  }

  // parse the begining of an expression
  Content parseContent(String contentSpecification) throws UnrecognizedEntryException {
    char c = contentSpecification.charAt(0);

    if (c == '=')
      return parseContentExpression(contentSpecification.substring(1));
    else
      return parseLiteral(contentSpecification);
  }

  private Literal parseLiteral(String literalExpression) throws UnrecognizedEntryException {
    if (literalExpression.charAt(0) == '\'')
      return new LiteralString(literalExpression);
    else {
      try {
        int val = Integer.parseInt(literalExpression);
        return new LiteralInteger(val);
      } catch (NumberFormatException nfe) {
        throw new UnrecognizedEntryException("Número inválido: " + literalExpression);
      }
    }
  }

  // contentSpecification is what comes after '='
  private Content parseContentExpression(String contentSpecification) throws UnrecognizedEntryException  {
    if (contentSpecification.contains("(")) {
        return parseFunction(contentSpecification);
    }
    // It is a reference
    String[] address = contentSpecification.split(";");
    return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]),_cal);
  }

  private Content parseFunction(String functionSpecification) throws UnrecognizedEntryException /*more exceptions */ {
    String[] components = functionSpecification.split("[()]");
    if (components[1].contains(","))
      return parseBinaryFunction(components[0], components[1]);

    return parseIntervalFunction(components[0], components[1]);
  }

  private Content parseBinaryFunction(String functionName, String args) throws UnrecognizedEntryException /* , more Exceptions */ {
    String[] arguments = args.split(",");
    Content arg0 = parseArgumentExpression(arguments[0]);
    Content arg1 = parseArgumentExpression(arguments[1]);

    return switch (functionName) {
      case "ADD" -> new Add(arg0, arg1);
      case "SUB" -> new Sub(arg0, arg1);
      case "MUL" -> new Mul(arg0, arg1);
      case "DIV" -> new Div(arg0, arg1);
      default -> throw new UnrecognizedEntryException("Funcao invalida" + functionName);
    };
  }

  private Content parseArgumentExpression(String argExpression) throws UnrecognizedEntryException {
    if (argExpression.contains(";")  && argExpression.charAt(0) != '\'') {
      String[] address = argExpression.split(";");
      return new Reference(Integer.parseInt(address[0]), Integer.parseInt(address[1]),_cal);
    } else
      return parseLiteral(argExpression);
  }

  private Content parseIntervalFunction(String functionName, String rangeDescription) throws UnrecognizedEntryException /* , more exceptions ? */ {
    Range range = _spreadsheet.createRange(rangeDescription);
    return switch (functionName) {
      case "CONCAT" -> new Concat(range);
      case "COALESCE" -> new Coalesce(range);
      case "PRODUCT" -> new Product(range);
      case "AVERAGE" -> new Average(range);
      default -> throw new UnrecognizedEntryException("Funcao invalida" + functionName);
    };
  }

}
