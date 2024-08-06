/*import java.util.Scanner;
public class _3Taking_input {
    public static void main(String[] args)
    
  
     {      Scanner scobj = new Scanner(System.in);
    System.out.println("enter urname- ");
    String name=scobj.nextLine();
    System.out.println("my name is- "+name);

    scobj.close();
     }
} */
import java.util.Scanner;
public class _3Taking_input {
    public static void main(String[] args)
{
    Scanner  obj = new Scanner(System.in);

    System.out.println("Enter your age");
    int age= obj.nextInt();
    
        if(age>=18)
        {
            System.out.println ("eligible for vote");
        }
            else
            {
            System.out.println("not eligible for vote");
            }
     age= age+10;   
    System.out.println("Afer 10 year later age will be "+ (age+10)+" year old");
}  
}

    

