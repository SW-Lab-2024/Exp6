package MiniJava.codeGenerator;

public abstract class Address {
    public int num;
    public varType varType;
    public boolean isNull;
    public Address(int num, varType varType, boolean isNull) {
        this.num = num;
        this.varType = varType;
        this.isNull=isNull;
    }

    public abstract String toString();
}

class DirectAddress extends Address {
    public DirectAddress(int num, varType varType, boolean isNull) {
        super(num, varType,isNull);
    }

    @Override
    public String toString() {
        return num + "";
    }
}

class IndirectAddress extends Address {
    public IndirectAddress(int num, varType varType, boolean isNull) {
        super(num, varType,isNull);
    }

    @Override
    public String toString() {
        return "@" + num;
    }
}
class NullAddress extends Address {
    // Step 2: Create a static instance of the class
    private static NullAddress instance;

    // Step 1: Make the constructor private
    private NullAddress(int num, varType varType, boolean isNull) {
        super(num, varType, isNull);
    }

    // Step 3: Provide a public static method to get the single instance
    public static NullAddress getInstance(int num, varType varType, boolean isNull) {
        if (instance == null) {
            instance = new NullAddress(num, varType, isNull);
        }
        return instance;
    }

    @Override
    public String toString() {
        return "";
    }
}

