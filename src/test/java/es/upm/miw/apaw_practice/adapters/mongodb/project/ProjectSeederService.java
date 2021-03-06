package es.upm.miw.apaw_practice.adapters.mongodb.project;

import es.upm.miw.apaw_practice.adapters.mongodb.project.daos.DeveloperRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.project.daos.IssueRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.project.daos.LabelRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.project.daos.UserStoryRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.project.entities.DeveloperEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.project.entities.IssueEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.project.entities.LabelEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.project.entities.UserStoryEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProjectSeederService {

    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private UserStoryRepository userStoryRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Project Initial Load -----------");
        LabelEntity[] labels = {
                LabelEntity.builder()
                        .name("enhancement")
                        .description("New feature or request")
                        .build(),
                LabelEntity.builder()
                        .name("documentation")
                        .description("Improvements or additions to documentation")
                        .build(),
                LabelEntity.builder()
                        .name("bug")
                        .description("Something isn't working")
                        .build(),
                LabelEntity.builder()
                        .name("test")
                        .description("Test new code")
                        .build()
        };
        this.labelRepository.saveAll(Arrays.asList(labels));
        DeveloperEntity[] developers = {
                DeveloperEntity.builder()
                        .name("Jaime")
                        .email("jaime@developer.com")
                        .yearsOfExperience(7)
                        .build(),
                DeveloperEntity.builder()
                        .name("Alberto")
                        .email("alberto@developer.com")
                        .yearsOfExperience(3)
                        .build(),
                DeveloperEntity.builder()
                        .name("Marta")
                        .email("marta@developer.com")
                        .yearsOfExperience(12)
                        .build()
        };
        this.developerRepository.saveAll(Arrays.asList(developers));
        IssueEntity[] issues = {
                new IssueEntity("create class user", 2, developers[0], Arrays.asList(labels[0], labels[1])),
                new IssueEntity("test class user", 1, developers[2], Arrays.asList(labels[3], labels[1])),
                new IssueEntity("setup environment", 5, developers[1], Arrays.asList(labels[2], labels[0]))
        };
        this.issueRepository.saveAll(Arrays.asList(issues));
        UserStoryEntity[] userStories = {
                new UserStoryEntity("login", "Implement login feature", 5, Arrays.asList(issues[0], issues[1])),
                new UserStoryEntity("environment", "Setup continuous integration environment", 8, Arrays.asList(issues[2])),
                new UserStoryEntity("delete", "Issue to delete", 8, Arrays.asList(issues[2]))
        };
        userStoryRepository.saveAll(Arrays.asList(userStories));
    }

    public void deleteAll() {
        this.labelRepository.deleteAll();
        this.developerRepository.deleteAll();
        this.issueRepository.deleteAll();
        this.userStoryRepository.deleteAll();
    }

}
