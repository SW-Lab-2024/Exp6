package MiniJava.codeGenerator;

public class ImmediateAddress extends Address {
    public ImmediateAddress(int num, varType varType,boolean isNull) {
        super(num, varType, isNull);
    }

    @Override
    public String toString() {
        return "#" + num;
    }
}
