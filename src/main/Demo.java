package main;

public class Demo {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        Parser parser = new Parser();

        parser.setData(fileReader);

        parser.parseData();
        parser.populateEntrySet();
        parser.printResult();
    }
}
