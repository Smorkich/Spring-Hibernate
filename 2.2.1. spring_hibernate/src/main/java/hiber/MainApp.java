package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      Car car1 = new Car("Kia",10);
      Car car2 = new Car("Skoda",11);
      Car car3 = new Car("Lada",12);
      User user1 = new User("Ivan","Ivanov","Ivan@mail.ru");
      User user2 = new User("Petr","Petrov","Petr@mail.ru");
      User user3 = new User("Alex","Alexandrov","Alex@gmail.com");
      User user4 = new User("Nik","Nikonov","Nik@gmail.com");
      userService.add(user1.setCar(car2));
      userService.add(user2.setCar(car1));
      userService.add(user3.setCar(car2));
      userService.add(user4.setCar(car3));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
      System.out.println(userService.getUserByCar("Kia",10));
      context.close();
   }
}
