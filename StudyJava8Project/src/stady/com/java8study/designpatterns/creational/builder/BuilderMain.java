package stady.com.java8study.designpatterns.creational.builder;

import java.util.Date;

public class BuilderMain {

    public static void main(String[] args) {
        PersonBuilder builder = new PersonBuilder();
        Person p = builder
                .firstName("Adrian")
                .lastName("Stanciu")
                .cnp(1098938922)
                .birthDate(new Date())
                .build();

        System.out.println(p.getFirstName() + " " + p.getLastName());
    }
}
