import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

 class Main {
    public static void main(String[] args) {
        try {
            Scanner fileScanner = new Scanner(new File("input.txt"));
            int totalSeats = fileScanner.nextInt();
            Map<Integer, TreeSet<Integer>> seatingPlan = new HashMap<>();

            for (int i = 0; i < totalSeats; i++) {
                int rowNumber = fileScanner.nextInt();
                int seatNumber = fileScanner.nextInt();
                seatingPlan.computeIfAbsent(rowNumber, r -> new TreeSet<>()).add(seatNumber);
            }
            fileScanner.close();

            int targetRow = -1;
            int firstFreeSeatInTargetRow = -1;

            for (Map.Entry<Integer, TreeSet<Integer>> rowEntry : seatingPlan.entrySet()) {
                int currentRow = rowEntry.getKey();
                TreeSet<Integer> reservedSeats = rowEntry.getValue();

                System.out.println("Проверяем ряд: " + currentRow + ", Занятые места: " + reservedSeats);

                Integer lastSeat = null;
                for (Integer currentSeat : reservedSeats) {
                    if (lastSeat != null && currentSeat == lastSeat + 3) {
                        int freeSeatCandidate = lastSeat + 1;
                        if (currentRow > targetRow || (currentRow == targetRow && freeSeatCandidate < firstFreeSeatInTargetRow)) {
                            targetRow = currentRow;
                            firstFreeSeatInTargetRow = freeSeatCandidate;
                        }
                    }
                    lastSeat = currentSeat;
                }
            }

            if (targetRow == -1) {
                System.out.println("Подходящий ряд не найден.");
            } else {
                System.out.println(targetRow + " " + firstFreeSeatInTargetRow);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        }
    }
}
