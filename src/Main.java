import java.util.ArrayList;
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
}

class FieldCharacteristics {
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
}
public class Main {
    public static void main(String[] args) {
        PricingPolicy pp = new PricingPolicy();
        ArrayList<FieldCharacteristics> fieldList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int gameStatus = 0;

        do {
            System.out.println("================================");
            System.out.println("Add field | Show info | Exit");
            System.out.println("================================");
            System.out.println("    1     |     2     |   3");
            System.out.println("================================");

            gameStatus = scanner.nextInt();

            if (gameStatus == 1) {
                System.out.print("Enter field size in He: ");
                float size = scanner.nextFloat();
                fieldList.add(new FieldCharacteristics(size, pp));
            } else if (gameStatus == 2) {
                for (int i = 0; i < fieldList.size(); i++) {
                    System.out.println("Field " + (i + 1) + " details:");
                    fieldList.get(i).display();
                }
            }
        } while (gameStatus != 3);

        scanner.close();
    }
}