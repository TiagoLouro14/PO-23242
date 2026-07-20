package xxl.core;

public abstract class Literal extends Content {

    @Override
    public abstract String toString();

    @Override
    public abstract Literal value();

    @Override
    public int asInt() {
        try {
            return Integer.parseInt(toString());
        } catch (NumberFormatException e) {
            // Lida com a exceção caso a conversão falhe
            throw new NumberFormatException("A string não representa um número inteiro válido.");
        }
    }

    public abstract void accept(Visitor visitor);

    public abstract String getType();
}
