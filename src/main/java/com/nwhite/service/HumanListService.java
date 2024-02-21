package com.nwhite.service;

import com.nwhite.data.entity.HumanEntity;
import com.nwhite.data.repository.HumanEntityRepository;
import com.nwhite.dictionary.HumanEmbeddedDictionary;
import com.nwhite.pojo.Adult;
import com.nwhite.pojo.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class HumanListService {
    private final HumanEmbeddedDictionary dictionary;
    private final HumanEntityRepository humanRepository;

    @Autowired
    public HumanListService(HumanEmbeddedDictionary dictionary, HumanEntityRepository humanRepository) {
        this.dictionary = dictionary;
        this.humanRepository = humanRepository;
    }

    private final Set<Human> selectedApplicants = new HashSet<>();

    public Human findById(int id) {
//        for (Object obj : dictionary.getHumans()) {
//            if (obj instanceof Human) {
//                Human human = (Human) obj;
//                if (human.getId() == id) {
//                    return human;
//                }
//            }
//        }
//        return null;

        Optional<? super Human> optionalHuman = dictionary.getHumans()
                .stream()
                //.filter(h -> h instanceof Human)
                .filter(h -> ((Human) h).getId() == id)
                .findFirst();
        if (optionalHuman.isPresent()) {
            return (Human) optionalHuman.get();
        } else {
            throw new NoSuchElementException("Human not found");
        }


//        return dictionary.getHumans()
//                .stream()
//                //.filter(h -> h instanceof Human)
//                .filter(h -> ((Human) h).getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new NoSuchElementException("Human not found"));
    }

    public Map<String, List<Adult>> groupByName() {
        Map<String, List<Adult>> groupedByName = new HashMap<>();
        for (Object obj : dictionary.getHumans()) {
            if (obj instanceof Adult) {
                Adult adult = (Adult) obj;
                String name = adult.getName();
                if (name != null) {
                    groupedByName.computeIfAbsent(name, k -> new ArrayList<>()).add(adult);
                }
            }
        }
        return groupedByName;

//        return dictionary.getHumans().stream()
//                .filter(obj -> obj instanceof Adult)
//                .map(obj -> (Adult) obj)
//                .filter(adult -> adult.getName() != null)
//                .collect(Collectors.groupingBy(Adult::getName));
    }

    public Human getRandomHumanForLottery() {
        List<? super Human> humans = dictionary.getHumans();
        List<Human> eligibleHumans = new ArrayList<>();

        for (Object human : humans) {
            if (human instanceof Human && !selectedApplicants.contains(human)) {
                eligibleHumans.add((Human) human);
            }
        }

        //        List<Human> eligibleHumans = humans.stream()
//                .filter(h -> h instanceof Human)
//                .map(h -> (Human) h)
//                .filter(h -> !selectedApplicants.contains(h))
//                .collect(Collectors.toList());

        if (eligibleHumans.isEmpty()) {
            throw new NoSuchElementException("No eligible humans for lottery!");
        }

        Random random = new Random();
        int index = random.nextInt(eligibleHumans.size());

        Human selectedHuman = eligibleHumans.get(index);
        selectedApplicants.add(selectedHuman);

        if (selectedApplicants.size() == 5) selectedApplicants.clear();

        return selectedHuman;
        //        List<? super Human> humans = dictionary.getHumans();
//        if (humans.isEmpty()) {
//            throw new NoSuchElementException("List is empty!");
//        }
//
//        Random random = new Random();
//        int index = random.nextInt(humans.size());
//
//        return (Human) humans.get(index);

//        return humans.stream()
//                .skip(index)
//                .findFirst()
//                .map(h -> (Human) h)
//                .orElseThrow(NoSuchElementException::new);
    }

    public List<HumanEntity> findEngineerUsersWithNationality() {
        return humanRepository.
                findEngineerUsersWithNationality(LocalDate.of(1990, 1, 1),
                        LocalDate.of(1992, 12, 31));
    }

    public void save(HumanEntity humanEntity) {
        humanRepository.save(humanEntity);
    }
}
