import java.util.Scanner;
class Calculater { 
    
      public static int add(int a,int b){
          int c= a+b;
          return c;
      }
      public static int sub(int a,int b){
          int c= a-b;
          return c;
      }
      public static int mul(int a,int b){
          int c= a*b;
          return c;
      }
      public static int div(int a,int b){
          int c= a/b;
          return c;
      }
      public static int mod(int a,int b){
          int c= a%b;
          return c;
      }
      public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("a value is =");
    int a = sc.nextInt();
    System.out.println("b value is =");
    int b= sc.nextInt();
    System.out.println("Enter your choice +,-,*,/,%");
    char operation=sc.next().charAt(0);
        switch (operation) {
          case '+':
          System.out.println(add(a,b));
            
            break;
            case '-':
            System.out.println(sub(a,b));
              
              break;
              case '*':
            System.out.println(mul(a,b));
              
              break;
              case '/':
              System.out.println(div(a,b));
                
                break;
                case '%':
            System.out.println(mod(a,b));
              
              break;
          default:
           {
          System.out.println("invalid operation ");
            break;

           }
      }
    }
  }