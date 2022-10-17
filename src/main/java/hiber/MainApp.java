package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User userWithCar1 = new User("UserWithCar1", "LastnameWithCar1", "userWithCar1@mail.ru", new Car("Lada", 2));
      User userWithCar2 = new User("UserWithCar2", "LastnameWithCar2", "userWithCar2@mail.ru", new Car("Ford", 13));
      User userWithCar3 = new User("UserWithCar3", "LastnameWithCar3", "userWithCar3@mail.ru", new Car("BMW", 6));
      userService.add(userWithCar1);
      userService.add(userWithCar2);
      userService.add(userWithCar3);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println(userService.getUserByModelAndSeriesOfCar("BMW", 6));

      context.close();
   }
}
