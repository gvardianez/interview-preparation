package lesson_1.builder;

public class Person {

    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final Person person;

        public Builder() {
            person = new Person();
        }

        public Builder addFirstname(String firstName) {
            this.person.firstName = firstName;
            return this;
        }

        public Builder addLastName(String lastName) {
            this.person.lastName = lastName;
            return this;
        }

        public Builder addMiddleName(String middleName) {
            this.person.middleName = middleName;
            return this;
        }

        public Builder addCountry(String country) {
            this.person.country = country;
            return this;
        }

        public Builder addAddress(String address) {
            this.person.address = address;
            return this;
        }

        public Builder addPhone(String phone) {
            this.person.phone = phone;
            return this;
        }

        public Builder addAge(int age) {
            this.person.age = age;
            return this;
        }

        public Builder addGender(String gender) {
            this.person.gender = gender;
            return this;
        }

        public Person build() {
            return person;
        }

    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Person person = Person.builder().addFirstname("Vasya").addLastName("Petrov").build();
        System.out.println(person);
    }
}
