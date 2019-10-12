package stady.com.java8study.designpatterns.creational.singleton;

public class MySingletonWithInnerClass {

    private MySingletonWithInnerClass(){}

    private static class SingletonHelper{
        private static final MySingletonWithInnerClass INSTANCE = new MySingletonWithInnerClass();
    }

    public static MySingletonWithInnerClass getInstance(){
        return SingletonHelper.INSTANCE;
    }
}
