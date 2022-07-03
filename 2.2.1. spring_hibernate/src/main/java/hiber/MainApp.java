package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      User dima = new User("Дмитрий", "Связной", "svaz165@mail.ru");
      User ujin = new User("Евгений", "Шишкин", "shishkin1995@gmail.com");
      User yulia = new User("Юлия", "Алексеева", "yalex@yandex.ru");
      User alex = new User("Александра", "Добрая", "dobralex90@mail.ru");

      Car volvo = new Car("Volvo", 9);
      Car bmw = new Car("BMW", 325);
      Car kia = new Car("KIA", 562);
      Car mazda = new Car("Mazda", 542);

      userService.add(dima.setCar(volvo).setUser(dima));
      userService.add(ujin.setCar(bmw).setUser(ujin));
      userService.add(yulia.setCar(kia).setUser(yulia));
      userService.add(alex.setCar(mazda).setUser(alex));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("BMW", 325));


      try {
         User notFoundUser = userService.getUserByCar("Audi", 6785);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
