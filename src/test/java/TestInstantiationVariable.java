public class TestInstantiationVariable {
    public TestInstantiationVariable(int a){
        this.a = a;
    }
    int a = 1;

    public void printA(){
        System.out.println(a);
    }

    public static void main(String[] args) {
        TestInstantiationVariable testInstantiationVariable = new TestInstantiationVariable(2);
        testInstantiationVariable.printA();
    }
}
