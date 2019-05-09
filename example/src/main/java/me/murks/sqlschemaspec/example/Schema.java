package me.murks.sqlschemaspec.example;

import me.murks.sqlschemaspec.ColumnSpec;
import me.murks.sqlschemaspec.SchemaSpec;
import me.murks.sqlschemaspec.TableSpec;
import me.murks.sqlschemaspec.Type;
import me.murks.sqlschemaspec.templates.TemplateCompiler;

/**
 * Class that defines the example schema.
 */
public class Schema extends SchemaSpec {

    /**
     * Inner class that defines a table.
     */
    class Recipes extends TableSpec {
        ColumnSpec id = primaryKey(Type.Integer);
        ColumnSpec name = column(Type.String);
    }

    /**
     * Creating a field with a type that extends @{@link TableSpec} adds a table to the schema.
     */
    Recipes recipes = new Recipes();

    /**
     * Definition of another table.
     */
    class Ingredients extends TableSpec {
        ColumnSpec id = primaryKey(Type.Integer);
        ColumnSpec recipeId = foreignKey(recipes.id);
        ColumnSpec name = column(Type.String);
        ColumnSpec mass = column(Type.Float, true);
    }

    /**
     * Adding the ingredients table to the schema.
     */
    Ingredients ingredients = new Ingredients();

    /**
     * Constructor that invokes the @{@link TemplateCompiler#compileTable(Object, TableSpec)} method in order to compile
     * the schema. Compiling a @{@link SchemaSpec} sets all relevant fields to create a usable schema specification.
     */
    public Schema() {
        // creates a new template compiler instance
        TemplateCompiler compiler = new TemplateCompiler();

        // compiles the schema
        compiler.compileTemplate(this, this);
    }
}
