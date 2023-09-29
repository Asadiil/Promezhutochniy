import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToyStore {
   private List<Toy> toys;
   private List<Toy> prizeToys;
   private static final String FILE_PATH = "prize_toys.txt";

   public ToyStore() {
      toys = new ArrayList<>();
      prizeToys = new ArrayList<>();
   }

   // Добавление новой игрушки в магазин
   public void addToy(Toy toy) {
      toys.add(toy);
   }

   // Изменение веса (частоты выпадения) игрушки
   public void changeWeight(int toyId, double weight) {
      for (Toy toy : toys) {
         if (toy.getId() == toyId) {
            toy.setWeight(weight);
            return;
         }
      }
      System.out.println("Игрушка с указанным ID не найдена!");
   }

   // Розыгрыш игрушки
   public void playToy() {
      double totalWeight = 0;
      for (Toy toy : toys) {
         totalWeight += toy.getWeight();
      }

      double randomNum = Math.random() * totalWeight;
      double currentWeight = 0;

      for (Toy toy : toys) {
         currentWeight += toy.getWeight();
         if (randomNum < currentWeight) {
            prizeToys.add(toy);
            toy.setQuantity(toy.getQuantity() - 1);
            if (toy.getQuantity() == 0) {
               toys.remove(toy);
            }
            break;
         }
      }
   }

   // Получение призовой игрушки
   public void getPrizeToy() {
      if (!prizeToys.isEmpty()) {
         Toy prizeToy = prizeToys.remove(0);
         System.out.println("Вы получили призовую игрушку: " + prizeToy.getName());

         try {
            FileWriter writer = new FileWriter(FILE_PATH, true);
            writer.write(prizeToy.getName() + "\n");
            writer.close();
         } catch (IOException e) {
            System.out.println("Не удалось записать в файл!");
         }

      } else {
         System.out.println("Призовые игрушки закончились!");
      }
   }

   public static void main(String[] args) {
      ToyStore toyStore = new ToyStore();

      // Создание и добавление игрушек в магазин
      Toy toy1 = new Toy(1, "Мячик", 5, 30);
      Toy toy2 = new Toy(2, "Кукла", 10, 20);
      Toy toy3 = new Toy(3, "Машинка", 8, 25);

      toyStore.addToy(toy1);
      toyStore.addToy(toy2);
      toyStore.addToy(toy3);

      // Пример изменения веса и розыгрыша игрушек
      toyStore.changeWeight(1, 50);
      toyStore.changeWeight(2, 10);
      toyStore.changeWeight(3, 40);

      toyStore.playToy();
      toyStore.playToy();
      toyStore.playToy();

      toyStore.getPrizeToy();
      toyStore.getPrizeToy();
   }

   public void saveToysToFile() {
        try 
        {
           FileWriter writer = new FileWriter("toys.txt");
           for (Toy toy : toys) 
           {
              writer.write(toy.getId() + "," + toy.getName() + "," + toy.getQuantity() + "," + toy.getWeight() + "\n");
           }
           writer.close();
        } 
        catch (IOException e) 
        {
           System.out.println("Не удалось записать в файл!");
        }
     }
     
   public void loadToysFromFile() {
   try {
      File file = new File("toys.txt");
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
         String line = scanner.nextLine();
         String[] data = line.split(",");
         int id = Integer.parseInt(data[0]);
         String name = data[1];
         int quantity = Integer.parseInt(data[2]);
         double weight = Double.parseDouble(data[3]);
         Toy toy = new Toy(id, name, quantity, weight);
         toys.add(toy);
        }
        scanner.close();
        catch (FileNotFoundException e) {
        System.out.println("Файл не найден!");
        }
    }
    public void displayPrizeToysFromFile() 
    {
        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);
            System.out.println("Список призовых игрушек:");
            while (scanner.hasNextLine()) 
                {
                String line = scanner.nextLine();
                System.out.println(line);
                }
        scanner.close();
            } 
        catch (FileNotFoundException e) 
        {
            System.out.println("Файл не найден!");
        }
    }

    public void displayAvailableToys() 
    {
        System.out.println("Список доступных игрушек:");
        for (Toy toy : toys) 
        {
            System.out.println(toy.getName() + " (ID: " + toy.getId() + ")");
        }
    }
    
}