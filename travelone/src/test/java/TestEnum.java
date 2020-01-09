public enum TestEnum {

    DCName("driverClassName", "com.mysql.cj.jdbc.Driver"),

    URL("url", "jdbc:mysql://localhost:3306/travel?"),

    UserName("username", "root");

    private String key;
    private String value;

    private TestEnum(String key, String value){
        this.key=key;
        this.value=value;
    }

    public static String getMsg1(String key){
        for (TestEnum enum3 : TestEnum.values()){

            if (enum3.getKey() .equals( key )){

                return enum3.getValue() ;
            }
        }
        return null ;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
