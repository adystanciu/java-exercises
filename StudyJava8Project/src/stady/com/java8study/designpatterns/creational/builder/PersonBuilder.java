package stady.com.java8study.designpatterns.creational.builder;

import java.util.Date;

public class PersonBuilder {

    private String firstName;
    private String lastName;
    private long cnp;
    private Date birthDate;

   public PersonBuilder firstName(String firstName){
       this.firstName = firstName;
       return this;
   }

    public PersonBuilder lastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder cnp(long cnp){
        this.cnp = cnp;
        return this;
    }

    public PersonBuilder birthDate(Date birthDate){
        this.birthDate = birthDate;
        return this;
    }

    public Person build(){
       return new Person(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getCnp() {
        return cnp;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
