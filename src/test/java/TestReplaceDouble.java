public class TestReplaceDouble {
    public static void main(String[] args) {
        String x1 = "x+1+8+x";
        System.out.println(x1.replaceAll("x", String.valueOf(3.3)));
        System.out.println(Double.parseDouble(x1.replaceAll("x", String.valueOf(3.3))));
    }
}
