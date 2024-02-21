package com.nwhite.service;

import com.nwhite.data.entity.AdultEntity;
import com.nwhite.data.entity.ChildEntity;
import com.nwhite.data.entity.HumanEntity;
import com.nwhite.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ConsoleRunnerService {
    private final int BAD_INPUT_TYPE = -1;
    private final int HUMAN_TO_PARTICIPATE_AT_LOTTERY = 5;
    private final HumanListService humanListService;
    private final LotteryService lotteryService;

    Scanner scanner = new Scanner(System.in);

    @Autowired
    public ConsoleRunnerService(HumanListService humanListService, LotteryService lotteryService) {
        this.humanListService = humanListService;
        this.lotteryService = lotteryService;
    }

    public void run() {
        while (true) {
            int userInput;

            System.out.println("***************");
            System.out.println("Insert command type:");
            System.out.println("1 to find by id;");
            System.out.println("2 to group by name;");
            System.out.println("3 to start lottery;");
            System.out.println("4 to run repository method;");
            System.out.println("0 to exit.");
            System.out.println("***************");

            userInput = checkUserInput();

            switch (userInput) {
                case 1 -> {
                    try {
                        System.out.println("Enter ID:");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Human human = humanListService.findById(id);
                        if (human == null) {
                            throw new NoSuchElementException("Human with ID " + id + " not found.");
                        }
                        System.out.println(human);
                    } catch (InputMismatchException ex) {
                        System.out.println("Invalid input. Please enter a valid integer ID.");
                        scanner.nextLine();
                    } catch (NoSuchElementException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("Grouping adult humans");
                    Map<String, List<Adult>> adultsGroupedByName =
                            humanListService.groupByName();
                    prettyPrint(adultsGroupedByName);
                }
                case 3 -> {
                    System.out.println("Starting lottery");
                    lotteryService.runLottery(HUMAN_TO_PARTICIPATE_AT_LOTTERY);
                }
                case 4 -> {
                    System.out.println("Searching");
                    List<HumanEntity> result =
                    humanListService.findEngineerUsersWithNationality();
                    System.out.println(result.size());

                }
                case 5 -> {
                    HumanEntity child1 = new ChildEntity(9, null, LocalDate.of(2016, 2, 19), Nationality.US, Education.ELEMENTARY);

                    HumanEntity adult1 = new AdultEntity(11, "Svetlana", LocalDate.of(1990, 2, 19), Nationality.EU, Qualification.ENGINEER);

                    humanListService.save(adult1);
                    humanListService.save(child1);
                }
                case 0 -> {
                    System.out.println("Shutdown our program");
                    return;
                }

                case BAD_INPUT_TYPE -> System.out.println("Wrong input, it's not are number");

                default -> System.out.println("Command type not found");
            }
        }
    }

    private int checkUserInput() {
        try {
            return Integer.parseInt(scanner.next());
        } catch (Exception e) {
            return BAD_INPUT_TYPE;
        }
    }

    private void  prettyPrint(Map<String, List<Adult>> map) {
        for (Map.Entry<String, List<Adult>> entry : map.entrySet()) {
            String name = entry.getKey();
            List<Adult> adults = entry.getValue();

            System.out.println("Name: " + name);
            System.out.println("Adults: ");

            for (Adult adult : adults) {
                System.out.println(adult.toString());
            }

            System.out.println("--------------------");
        }
    }
}
