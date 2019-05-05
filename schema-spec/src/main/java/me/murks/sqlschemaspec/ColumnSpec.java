/* This file is part of sql-schema-spec.
 *
 * sql-schema-spec is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or any later
 * version.

 * sql-schema-spec is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with sql-schema-spec.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Copyright 2019 zouroboros@zoho.eu
 */
package me.murks.sqlschemaspec;

/**
 * Specification of a column
 * @author zouroboros
 */
public class ColumnSpec {

    private TableSpec table;
    private ColumnSpec references;
    private String name;
    private Type type;
    private Boolean nullable;
    private Boolean primaryKey;
    
    /**
     * Construct's a new column specification.
     * @see me.murks.sqlschemaspec.TableSpec The TableSpec class contains convenience methods for creating columns.
     * @param nTable The table this columns belongs to
     * @param name The name of the column
     * @param nType The type of the column
     * @param nReferences If this column is a foreign key the this is the referenced column
     * @param nNullable Weather this column is nullable
     * @param nPrimaryKey Weather this column is a primary key or not
     */
    public ColumnSpec(TableSpec nTable, String name, Type nType, ColumnSpec nReferences, Boolean nNullable, Boolean nPrimaryKey){
        this.name = name;
        this.type = nType;
        this.nullable = nNullable;
        this.primaryKey = nPrimaryKey;
        references = nReferences;
        table = nTable;
    }

    public String createStatement() {
        String nullable = " not null";
        if(isNullable()) {
            nullable = " null";
        }

        String primaryKey = "";

        if(isPrimaryKey()) {
            primaryKey = " primary key";
        }

        String foreignKey = "";

        if(getReferences() != null) {
            foreignKey = String.format(" references \"%2$s\"(\"%3$s\")", getName(),
                    getReferences().getTable().getName(), getReferences().getName());
        }

        return String.format("\"%1$s\" %2$s%3$s%4$s%5$s", getName(),
                getType().toString(), nullable, primaryKey, foreignKey);
    }

    public TableSpec getTable() {
        return table;
    }

    public void setTable(TableSpec nTable) {
        table = nTable;
    }

    public ColumnSpec getReferences() {
        return references;
    }

    public void setReferences(ColumnSpec references) {
        this.references = references;
    }

    public ColumnSpec references(ColumnSpec referecences) {
        setReferences(referecences);
        return this;
    }

    /**
     * Returns a string that can be used to refer to this column in an SQL statement
     * @return SQL expression
     */
    public String sqlName() {
        return String.format("%1$s.\"%2$s\"", getTable().sqlName(), getName());
    }

    /**
     * Returns a string that can be used to refer to this column in an SQL statement
     * @param qualified Weather to return the fully qualified name or just the column name
     * @return SQL expression
     */
    public String sqlName(boolean qualified) {
        if(qualified) {
            return sqlName();
        } else {
            return String.format("\"%1$s\"", getName());
        }
    }

    /**
     * Returns a sql expression that renames this column to a the given name.
     * @param name The new name
     * @return SQL expression
     */
    public String rename(String name) {
        return "" + sqlName() + " as \"" + name + "\"";
    }

    /**
     * Returns the the column name prefixed with the given prefix as sql expression
     * @param prefix The prefix
     * @return SQL expression
     */
    public String prefix(String prefix) {
        return "\"" + prefix + getName() + "\"";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof ColumnSpec) {
            ColumnSpec other = (ColumnSpec) obj;
            return createStatement().equals(other.createStatement());
        }

        return false;
    }

    @Override
    public String toString() {
        return createStatement();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Boolean isNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public Boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
}
