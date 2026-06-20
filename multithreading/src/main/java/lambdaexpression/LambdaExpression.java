package lambdaexpression;

public class LambdaExpression {
  public static void main(String[] args) {
    // way to declaration anonymous functions in JAVASCRIPT
    Runnable runnable = () -> System.out.println("Hello world");
    /*  Interfaces with a single abstract method are called Functional Interfaces.
                This means the interface has only one method without a body.
            Example:
                            () -> { }
            This is called a Lambda Expression.

            A lambda expression provides the implementation/body of the single
            abstract method present inside a functional interface.

            In simple words:
            Functional Interface = one abstract method
            Lambda Expression    = implementation of that method
            which mean the above lambda expression acts as the implementation of the
            only abstract method which is run() in Runnable interface
    */
    Thread t1 = new Thread(runnable);
    t1.start();

    // or
      Thread t2 = new Thread(()->{
          System.out.println("Thread 2 is here");
      });
      t2.start();
  }
}
