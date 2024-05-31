import java.util.ArrayList;
import java.util.Scanner;

public class CheapestCar {
    public static void main(String[] args) {
        // Define car information
        Car[] cars = new Car[25];
        cars[0] = new Car("spoticar.com.tr", "Red", "Astra", 1069000, "used", false, "automatic", "diesel", 2019);
        cars[1] = new Car("spoticar.com.tr", "White", "Astra", 875000, "used", false, "automatic", "gasoline", 2016);
        cars[2] = new Car("spoticar.com.tr", "Grey", "Corsa", 1065000, "used", false, "automatic", "gasoline", 2022);
        cars[3] = new Car("spoticar.com.tr", "Red", "Corsa", 1050000, "used", false, "automatic", "electric", 2022);
        cars[4] = new Car("spoticar.com.tr", "Green", "Mokka", 1230000, "used", false, "automatic", "electric", 2023);
        cars[5] = new Car("sahibinden.com", "Grey", "Astra", 680000, "used", true, "manual", "diesel", 2016);
        cars[6] = new Car("sahibinden.com", "Grey", "Corsa", 495000, "used", true, "manual", "diesel", 2011);
        cars[7] = new Car("sahibinden.com", "Red", "Astra", 675000, "used", true, "automatic", "gasoline", 2011);
        cars[8] = new Car("arabam.com", "Grey", "Astra", 849000, "used", true, "manual", "gasoline", 2020);
        cars[9] = new Car("arabam.com", "Black", "Astra", 900000, "used", false, "manual", "gasoline", 2020);
        cars[10] = new Car("arabam.com", "Red", "Corsa", 1024900, "used", false, "automatic", "electric", 2023);
        cars[11] = new Car("arabam.com", "Red", "Corsa", 770000, "used", false, "automatic", "gasoline", 2018);
        cars[12] = new Car("arabam.com", "Green", "Mokka", 1160000, "used", false, "automatic", "electric", 2022);
        cars[13] = new Car("arabam.com", "Black", "Mokka", 1299000, "used", false, "automatic", "gasoline", 2023);
        cars[14] = new Car("tr.vava.cars", "White", "Astra", 752000, "used", false, "manual", "gasoline", 2019);
        cars[15] = new Car("tr.vava.cars", "Black", "Astra", 636000, "used", true, "manual", "gasoline", 2017);
        cars[16] = new Car("tr.vava.cars", "Grey", "Corsa", 696000, "used", false, "manual", "gasoline", 2020);
        cars[17] = new Car("tr.vava.cars", "Grey", "Mokka", 1300000, "used", false, "automatic", "gasoline", 2023);
        cars[18] = new Car("tr.vava.cars", "Green", "Mokka", 1170000, "used", false, "automatic", "gasoline", 2022);
        cars[19] = new Car("opel.com.tr", "Black", "Astra", 1564900, "new", false, "automatic", "gasoline", 2024);
        cars[20] = new Car("opel.com.tr", "White", "Astra", 1715900, "new", false, "automatic", "electric", 2024);
        cars[21] = new Car("opel.com.tr", "Grey", "Corsa", 1340900, "new", false, "automatic", "gasoline", 2024);
        cars[22] = new Car("opel.com.tr", "Grey", "Corsa", 1340900, "new", false, "automatic", "electric", 2024);
        cars[23] = new Car("opel.com.tr", "Green", "Mokka", 1553900, "new", false, "automatic", "electric", 2024);
        cars[24] = new Car("opel.com.tr", "Grey", "Mokka", 1392000, "new", false, "automatic", "gasoline", 2024);

        // Get desired car criteria from the user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, welcome to the Cheapest Car.\nOur site has an agreement with Opel and currently only covers Opel cars and we only provide service in Turkey.\nPlease enter the Opel brand car information you want to buy at the cheapest price.\nImportant: You can skip the criteria you do not want to specify by pressing enter.");
        System.out.print("\nModel: ");
        String desiredModel = scanner.nextLine();

        // Check if the entered model is valid or empty
        if (!desiredModel.isEmpty() && !isValidModel(desiredModel, cars)) {
            System.out.println("\nInvalid model. Please restart the application and enter a valid Opel model.");
            scanner.close();
            return;
        }
        System.out.print("\nCondition:\n>new\n>used\n ");
        String desiredCondition = scanner.nextLine();
        boolean hasChangedPart = false;
        if (desiredCondition.equalsIgnoreCase("used")) {
            System.out.print("\nDoes it have changed parts?:\n>true\n>false\n ");
            String hasChangedPartInput = scanner.nextLine();
            if (!hasChangedPartInput.isEmpty()) {
                hasChangedPart = Boolean.parseBoolean(hasChangedPartInput);
            }
        }
        System.out.print("\nTransmission:\n>automatic\n>manual\n ");
        String desiredTransmission = scanner.nextLine();
        System.out.print("\nFuel Type:\n>gasoline\n>diesel\n>electric\n ");
        String desiredFuelType = scanner.nextLine();
        System.out.print("\nColor: ");
        String desiredColor = scanner.nextLine();

        // Find the cheapest cars and show which site they are on.
        ArrayList<Car> cheapestCars = findCheapestCars(cars, desiredModel, desiredTransmission, desiredColor, desiredCondition, hasChangedPart, desiredFuelType);
        if (!cheapestCars.isEmpty()) {
            System.out.println("\nCheapest car(s) according to the specified criteria:");
            for (Car car : cheapestCars) {
                System.out.println("Site: '" + car.getSite() + "', Price: " + car.getPrice() + " TL, Model: Opel " + car.getModel() + ", Transmission: " + car.getTransmission() + ", Color: " + car.getColor() + ", Condition: " + car.getCondition() + ", Fuel type: " + car.getFuelType() + ", Production year: " + car.getYear());
            }
        } else {
            System.out.println("\nSorry, no car found matching your criteria.\nVery soon you can filter all cars.");
        }
        System.out.println("\nWe hope we were able to help.\nWe are here whenever you need.");

        scanner.close();
    }
    // Checks if the entered model is valid
    private static boolean isValidModel(String model, Car[] cars) {
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(model)) {
                return true;
            }
        }
        return false;
    }
    // Finds the cheapest cars
    private static ArrayList<Car> findCheapestCars(Car[] cars, String desiredModel, String desiredTransmission, String desiredColor, String desiredCondition, boolean hasChangedPart, String desiredFuelType) {
        double lowestPrice = Double.MAX_VALUE;
        ArrayList<Car> cheapestCars = new ArrayList<>();

        for (Car car : cars) {
            boolean matchesModel = desiredModel.isEmpty() || car.getModel().equalsIgnoreCase(desiredModel);
            boolean matchesTransmission = desiredTransmission.isEmpty() || car.getTransmission().equalsIgnoreCase(desiredTransmission);
            boolean matchesColor = desiredColor.isEmpty() || car.getColor().equalsIgnoreCase(desiredColor);
            boolean matchesCondition = desiredCondition.isEmpty() || car.getCondition().equalsIgnoreCase(desiredCondition);
            boolean matchesFuelType = desiredFuelType.isEmpty() || car.getFuelType().equalsIgnoreCase(desiredFuelType);
            boolean matchesChangedPart = desiredCondition.isEmpty() || car.getCondition().equalsIgnoreCase("new") || car.hasChangedPart() == hasChangedPart;

            if (matchesModel && matchesTransmission && matchesColor && matchesCondition && matchesFuelType && matchesChangedPart) {
                if (car.getPrice() < lowestPrice) {
                    lowestPrice = car.getPrice();
                    cheapestCars.clear();
                    cheapestCars.add(car);
                } else if (car.getPrice() == lowestPrice) {
                    cheapestCars.add(car);
                }
            }
        }
        return cheapestCars;
    }
}
// Car class
class Car {
    private String site;
    private String color;
    private String model;
    private double price;
    private String condition;
    private boolean changedPart;
    private String transmission;
    private String fuelType;
    private int year;

    public Car(String site, String color, String model, double price, String condition, boolean changedPart, String transmission, String fuelType, int year) {
        this.site = site;
        this.color = color;
        this.model = model;
        this.price = price;
        this.condition = condition;
        this.changedPart = changedPart;
        this.transmission = transmission;
        this.fuelType = fuelType;
        this.year = year;
    }

    public String getSite() {
        return site;
    }
    public String getColor() {
        return color;
    }
    public String getModel() {
        return model;
    }
    public double getPrice() {
        return price;
    }
    public String getCondition() {
        return condition;
    }
    public boolean hasChangedPart() {
        return changedPart;
    }
    public String getTransmission() {
        return transmission;
    }
    public String getFuelType() {
        return fuelType;
    }
    public int getYear() {
        return year;
    }
}