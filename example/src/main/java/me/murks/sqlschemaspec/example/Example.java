package me.murks.sqlschemaspec.example;

/**
 * Class that uses the example @{@link Schema}
 */
public class Example {
    public static void main(String[] args) {
        // create the schema
        Schema schema = new Schema();

        // the sql statements to create the schema
        for (String statement: schema.createStatement()) {
            System.out.println(statement);
        }
    }
}