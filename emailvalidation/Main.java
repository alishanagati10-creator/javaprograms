import java.lang.annotation.*;
import java.lang.reflect.Field;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface EmailValidation {
    String message() default "Invalid Email Address";
}


class User {

    @EmailValidation
    String email;

    User(String email) {
        this.email = email;
    }

class EmailValidator {

    public static void validate(Object obj) {

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {

            if (field.isAnnotationPresent(EmailValidation.class)) {

                field.setAccessible(true);

                try {
                    String value = (String) field.get(obj);

                    if (!value.contains("@") || !value.contains(".")) {
                        EmailValidation annotation = field.getAnnotation(EmailValidation.class);
                        System.out.println(annotation.message());
                    } else {
                        System.out.println("Valid Email: " + value);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

// Main class
public class Main {

    public static void main(String[] args) {

        User u1 = new User("test@gmail.com");
        User u2 = new User("invalidemail");

        EmailValidator.validate(u1);
        EmailValidator.validate(u2);
    }
}
}