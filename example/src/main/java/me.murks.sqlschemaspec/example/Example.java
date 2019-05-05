package me.murks.sqlschemaspec.example;

import me.murks.sqlschemaspec.example.me.murks.sqlschemaspec.example.Schema;

public class Example {
	public static void main(String[] args) {
		Schema schema = new Schema();
		for (String statement: schema.createStatement()) {
			System.out.println(statement);
		}
	}
}