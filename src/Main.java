import java.util.*;

// Базовый класс
abstract class Field {
    protected float size;

    public Field(float size) {
        this.size = size;
    }

    public float getSize() {
        return size;
    }

    public abstract float getCost();

    public abstract void display();
}

// Класс политики ценообразования
class PricingPolicy {
    private String update = "last price update 25.11.2024";
    private float fieldCost = 500000;
    private float timePlowing = 5, costPlowing = 13000;
    private float timeCultivation = 3, costCultivation = 11500;
    private float timeRolling = 2, costRolling = 10000;
    private float costFertilization = 11500, timeFertilization = 1, volumeMineralFertilizers = 100;

    public float getFieldCost() {
        return fieldCost;
    }

    public float getTimePlowing() {
        return timePlowing;
    }

    public float getCostPlowing() {
        return costPlowing;
    }

    public float getTimeCultivation() {
        return timeCultivation;
    }

    public float getCostCultivation() {
        return costCultivation;
    }

    public float getTimeRolling() {
        return timeRolling;
    }

    public float getCostRolling() {
        return costRolling;
    }

    public float getTimeFertilization() {
        return timeFertilization;
    }

    public float getCostFertilization() {
        return costFertilization;
    }

    public float getVolumeMineralFertilizers() {
        return volumeMineralFertilizers;
    }

    public float getFinalTime() {
        return timePlowing + timeCultivation + timeRolling + timeFertilization;
    }

    public float getFinalCost() {
        return costPlowing + costCultivation + costRolling + costFertilization;
    }
}

// Производный класс - Характеристики стандартного поля
class FieldCharacteristics extends Field {
    private float fieldCost = 0;
    private float timePlowing = 0, costPlowing = 0;
    private float timeCultivation = 0, costCultivation = 0;
    private float timeRolling = 0, costRolling = 0;
    private float costFertilization = 0, timeFertilization = 0, volumeMineralFertilizers = 0;
    private float finalTime = 0, finalCost = 0;

    public FieldCharacteristics(float size, PricingPolicy pp) {
        super(size);
        this.fieldCost = pp.getFieldCost() * size;
        this.timePlowing = pp.getTimePlowing() * size;
        this.costPlowing = pp.getCostPlowing() * size;
        this.timeCultivation = pp.getTimeCultivation() * size;
        this.costCultivation = pp.getCostCultivation() * size;
        this.timeRolling = pp.getTimeRolling() * size;
        this.costRolling = pp.getCostRolling() * size;
        this.costFertilization = pp.getCostFertilization() * size;
        this.timeFertilization = pp.getTimeFertilization() * size;
        this.volumeMineralFertilizers = pp.getVolumeMineralFertilizers() * size;
        this.finalTime = pp.getFinalTime() * size;
        this.finalCost = pp.getFinalCost() * size;
    }

    @Override
    public float getCost() {
        return finalCost;
    }

    @Override
    public void display() {
        System.out.println("Field size: " + size + " He");
        System.out.println("Field cost: " + fieldCost + " rub");
        System.out.println("Final cost: " + finalCost + " rub");
    }
}

// Производный класс - Особенное поле
class SpecialField extends Field {
    private float extraCost;

    public SpecialField(float size, float extraCost) {
        super(size);
        this.extraCost = extraCost;
    }

    @Override
    public float getCost() {
        return size * extraCost;
    }

    @Override
    public void display() {
        System.out.println("Special field size: " + size + " He");
        System.out.println("Special field cost: " + getCost() + " rub");
    }
}

// Основной класс программы
public class Main {
    public static void main(String[] args) {
        PricingPolicy pp = new PricingPolicy();
        List<Field> fields = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("================================");
            System.out.println("1. Add standard field");
            System.out.println("2. Add special field");
            System.out.println("3. Show all fields");
            System.out.println("4. Sort fields by cost");
            System.out.println("5. Search field by size");
            System.out.println("6. Exit");
            System.out.println("================================");
            choice = scanner.nextInt();

            try {
                if (choice == 1) {
                    System.out.print("Enter field size in He: ");
                    float size = scanner.nextFloat();
                    fields.add(new FieldCharacteristics(size, pp));
                } else if (choice == 2) {
                    System.out.print("Enter special field size in He: ");
                    float size = scanner.nextFloat();
                    System.out.print("Enter extra cost per hectare: ");
                    float extraCost = scanner.nextFloat();
                    fields.add(new SpecialField(size, extraCost));
                } else if (choice == 3) {
                    for (Field field : fields) {
                        field.display();
                    }
                } else if (choice == 4) {
                    fields.sort(Comparator.comparing(Field::getCost));
                    System.out.println("Fields sorted by cost.");
                } else if (choice == 5) {
                    System.out.print("Enter field size to search: ");
                    float size = scanner.nextFloat();
                    Field result = fields.stream()
                            .filter(field -> field.getSize() == size)
                            .findFirst()
                            .orElse(null);
                    if (result != null) {
                        System.out.println("Field found:");
                        result.display();
                    } else {
                        System.out.println("No field found with size " + size + " He.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        } while (choice != 6);

        scanner.close();
    }
}