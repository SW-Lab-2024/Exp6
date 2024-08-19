public abstract class Address {
    public int num;
    public varType varType;

    public Address(int num, varType varType) {
        this.num = num;
        this.varType = varType;
    }

    public abstract String toString();
}

class DirectAddress extends Address {
    public DirectAddress(int num, varType varType) {
        super(num, varType);
    }

    @Override
    public String toString() {
        return num + "";
    }
}

class IndirectAddress extends Address {
    public IndirectAddress(int num, varType varType) {
        super(num, varType);
    }

    @Override
    public String toString() {
        return "@" + num;
    }
}

class ImmediateAddress extends Address {
    public ImmediateAddress(int num, varType varType) {
        super(num, varType);
    }

    @Override
    public String toString() {
        return "#" + num;
    }
}
