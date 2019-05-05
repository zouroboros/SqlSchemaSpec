package me.murks.sqlschemaspec.example;

import me.murks.sqlschemaspec.ColumnSpec;
import me.murks.sqlschemaspec.SchemaSpec;
import me.murks.sqlschemaspec.TableSpec;
import me.murks.sqlschemaspec.Type;
import me.murks.sqlschemaspec.templates.TemplateCompiler;

public class Schema extends SchemaSpec {
    class Recipes extends TableSpec {
        ColumnSpec id = primaryKey(Type.Integer);
        ColumnSpec name = column(Type.String);
    }

    Recipes recipes = new Recipes();

    public Schema() {
        TemplateCompiler compiler = new TemplateCompiler();
        compiler.compileTemplate(this, this);
    }
}
