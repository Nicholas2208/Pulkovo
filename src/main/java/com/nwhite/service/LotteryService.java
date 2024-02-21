package com.nwhite.service;

import com.nwhite.pojo.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

@Service
public class LotteryService {
    private final HumanListService humanListService;
    List<Human> applicantsList
            = Collections.synchronizedList(new ArrayList<>());
    CyclicBarrier cyclicBarrier;

    @Autowired
    public LotteryService(HumanListService humanListService) {

        this.humanListService = humanListService;
    }

    public void runLottery(int numberOfApplicant) {
        cyclicBarrier = new CyclicBarrier(numberOfApplicant, new CheckWinner());
        ScheduledExecutorService executorService =
                Executors.newScheduledThreadPool(numberOfApplicant);
        for (int i = 0; i < numberOfApplicant; i++) {
            executorService.schedule(new ApplicantFinder(), i, TimeUnit.SECONDS);
        }

        executorService.shutdown();
    }

    class ApplicantFinder implements Runnable {
        @Override
        public void run() {
            Human applicant = humanListService.getRandomHumanForLottery();
            applicantsList.add(applicant);
            System.out.println("applicant name is " + applicant.getName()
                    + " with id " + applicant.getId());
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Closing ApplicantFinder thread");
        }
    }

    class CheckWinner implements Runnable {
        @Override
        public void run() {
            Random random = new Random();
            Human winner = applicantsList.get(random.nextInt(applicantsList.size()));
            System.out.println("Lottery is complete");
            System.out.println("Winner is - " +
                    winner.getName() + " with ID " + winner.getId());
            applicantsList.clear();
            cyclicBarrier.reset();
        }
    }
}
