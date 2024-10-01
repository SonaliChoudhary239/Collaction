import java.util.ArrayList;
import java.util.List;

public class CollectionsExample {
public static void main(String[] args) {
// Creating a list
List<String> list = new ArrayList<>();

// Adding elements to the list
list.add("Java");
list.add("Python");
list.add("C++");

// Iterating over the list
for (String language : list) {
System.out.println(language);
}
}
}
