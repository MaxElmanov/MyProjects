public class main {
    public static void main(String[] args) {
        String cancel = "Введите пароль здесь";

        StringBuilder converted = new StringBuilder(cancel.length()*6);
        for(char c : cancel.toCharArray()){
            converted.append(getCharRepresentation(c));
        }
        System.out.println("Initial:   "+cancel);
        System.out.println("Converted: "+converted.toString());
    }

    private static String getCharRepresentation(char c){
        StringBuilder cs = new StringBuilder("00").append(Integer.toHexString(c));
        return "\\u"+cs.substring(cs.length() - 4).toUpperCase();
    }
}
