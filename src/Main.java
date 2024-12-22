import java.util.*;

// Абстрактный базовый класс
abstract class Field implements Cloneable {
    protected float size;

    public Field(float size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Field size must be greater than 0.");
        }
        this.size = size;
    }

    public float getSize() {
        return size;
    }

    public abstract float getCost();

    @Override
    public abstract String toString();

    @Override
    public Field clone() throws CloneNotSupportedException {
        return (Field) super.clone(); // Мелкое клонирование
    }
}

// Интерфейс для поля с дополнительными затратами
interface AdditionalCost {
    float calculateExtraCost();
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

    public float getFinalCost() {
        return costPlowing + costCultivation + costRolling + costFertilization;
    }
}

// Производный класс - Характеристики стандартного поля
class FieldCharacteristics extends Field {
    protected float finalCost;

    public FieldCharacteristics(float size, PricingPolicy pp) {
        super(size);
        this.finalCost = pp.getFinalCost() * size;
    }

    @Override
    public float getCost() {
        return finalCost;
    }

    @Override
    public String toString() {
        return "FieldCharacteristics: size=" + size + " He, cost=" + finalCost + " rub";
    }
}

// Производный класс - Поле с орошением
class IrrigatedField extends FieldCharacteristics implements AdditionalCost {
    private float irrigationCost;

    public IrrigatedField(float size, PricingPolicy pp, float irrigationCost) {
        super(size, pp); // Вызов конструктора базового класса
        this.irrigationCost = irrigationCost;
    }

    @Override
    public float calculateExtraCost() {
        return irrigationCost * size;
    }

    @Override
    public float getCost() {
        // Перегрузка метода с вызовом метода базового класса
        return super.getCost() + calculateExtraCost();
    }

    @Override
    public String toString() {
        return super.toString() + ", irrigationCost=" + calculateExtraCost() + " rub";
    }
}

// Класс, реализующий глубокое клонирование
class SpecialField extends Field implements Cloneable {
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
    public String toString() {
        return "SpecialField: size=" + size + " He, cost=" + getCost() + " rub";
    }

    @Override
    public SpecialField clone() {
        return new SpecialField(size, extraCost); // Глубокое клонирование
    }
}

// Шаблонный класс
class Box<T extends Field> {
    private T field;

    public Box(T field) {
        this.field = field;
    }

    public T getField() {
        return field;
    }

    @Override
    public String toString() {
        return "Box contains: " + field.toString();
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
            System.out.println("2. Add irrigated field");
            System.out.println("3. Add special field");
            System.out.println("4. Show all fields");
            System.out.println("5. Clone a field");
            System.out.println("6. Sort fields by cost");
            System.out.println("7. Exit");
            System.out.println("================================");
            choice = scanner.nextInt();

            try {
                if (choice == 1) {
                    System.out.print("Enter field size in He: ");
                    float size = scanner.nextFloat();
                    fields.add(new FieldCharacteristics(size, pp));
                } else if (choice == 2) {
                    System.out.print("Enter field size in He: ");
                    float size = scanner.nextFloat();
                    System.out.print("Enter irrigation cost per hectare: ");
                    float irrigationCost = scanner.nextFloat();
                    fields.add(new IrrigatedField(size, pp, irrigationCost));
                } else if (choice == 3) {
                    System.out.print("Enter special field size in He: ");
                    float size = scanner.nextFloat();
                    System.out.print("Enter extra cost per hectare: ");
                    float extraCost = scanner.nextFloat();
                    fields.add(new SpecialField(size, extraCost));
                } else if (choice == 4) {
                    for (Field field : fields) {
                        System.out.println(field);
                    }
                } else if (choice == 5) {
                    System.out.print("Enter index of the field to clone: ");
                    int index = scanner.nextInt();
                    if (index >= 0 && index < fields.size()) {
                        Field clonedField = fields.get(index).clone();
                        fields.add(clonedField);
                        System.out.println("Field cloned successfully.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                } else if (choice == 6) {
                    fields.sort(Comparator.comparing(Field::getCost));
                    System.out.println("Fields sorted by cost.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        } while (choice != 7);

        scanner.close();
    }
}