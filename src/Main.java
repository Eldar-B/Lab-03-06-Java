import java.util.Scanner;

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

    public String getUpdate() {
        return update;
    }

    public String getFormattedUpdate() {
        return "Date of last update: " + update.substring(update.indexOf("25.11.2024"));
    }
}

class Result {
    private int value;

    public Result(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

class FieldCharacteristics {
    private static int totalFields = 0; // Статическое поле для подсчета объектов
    private float fieldCost = 0, size = 0;
    private float timePlowing = 0, costPlowing = 0;
    private float timeCultivation = 0, costCultivation = 0;
    private float timeRolling = 0, costRolling = 0;
    private float costFertilization = 0, timeFertilization = 0, volumeMineralFertilizers = 0;
    private float finalTime = 0, finalCost = 0;

    public FieldCharacteristics(float size, PricingPolicy pp) {
        this.size = size;
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
        totalFields++;
    }

    public static int getTotalFields() {
        return totalFields;
    }

    public void display() {
        System.out.println("Field size: " + size + " He");
        System.out.println("Field cost: " + fieldCost + " rub");
        System.out.println("================================");
        System.out.println("Plowing time: " + timePlowing + " h");
        System.out.println("Cultivation time: " + timeCultivation + " h");
        System.out.println("Rolling time: " + timeRolling + " h");
        System.out.println("Fertilization time: " + timeFertilization + " h");
        System.out.println("Final time: " + finalTime + " h");
        System.out.println("================================");
        System.out.println("Plowing cost: " + costPlowing + " rub");
        System.out.println("Cultivation cost: " + costCultivation + " rub");
        System.out.println("Rolling cost: " + costRolling + " rub");
        System.out.println("Fertilization cost: " + costFertilization + " rub");
        System.out.println("Final cost: " + finalCost + " rub");
        System.out.println("================================");
        System.out.println("Additional (volume of mineral fertilizers): " + volumeMineralFertilizers + " L");
    }

    public boolean compareFinalCost(FieldCharacteristics other) {
        return this.finalCost > other.finalCost;
    }

    public Result getSizeAsResult() {
        return new Result((int) size);
    }
}

public class Main {
    public static void main(String[] args) {
        PricingPolicy pp = new PricingPolicy();
        FieldCharacteristics[] fields = new FieldCharacteristics[5]; // Массив объектов
        int currentFieldIndex = 0;

        Scanner scanner = new Scanner(System.in);
        int gameStatus = 0;
        do {
            System.out.println("================================");
            System.out.println("Add field | Show info | Show total fields | Exit");
            System.out.println("    1     |     2     |         4         |   3");
            System.out.println("================================");
            gameStatus = scanner.nextInt();

            if (gameStatus == 1 && currentFieldIndex < fields.length) {
                System.out.print("Enter field size in He: ");
                float size = scanner.nextFloat();
                fields[currentFieldIndex++] = new FieldCharacteristics(size, pp);
            } else if (gameStatus == 2) {
                for (int i = 0; i < currentFieldIndex; i++) {
                    System.out.println("Field " + (i + 1) + " details:");
                    fields[i].display();
                }
            } else if (gameStatus == 4) {
                System.out.println("Total fields created: " + FieldCharacteristics.getTotalFields());
            }
        } while (gameStatus != 3);

        scanner.close();
    }
}