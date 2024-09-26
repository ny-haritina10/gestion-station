package enums;

public enum TypeTransaction {

    CASH_IN, CASH_OUT;

    public String getTypeTransation() {
        switch (this) {
            case CASH_IN:
                return "CASH_IN";
            
            case CASH_OUT:
                return "CASH_OUT";
        
            default:
                break;
        }

        return "NULL";
    }
}