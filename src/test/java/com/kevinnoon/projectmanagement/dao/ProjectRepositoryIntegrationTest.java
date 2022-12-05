package com.kevinnoon.projectmanagement.dao;

import com.kevinnoon.projectmanagement.ProjectManagementApplication;
import com.kevinnoon.projectmanagement.entities.Project;
import com.kevinnoon.projectmanagement.repository.ProjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:schema.sql","classpath:data.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = {"classpath:drop.sql"})})
public class ProjectRepositoryIntegrationTest {
    @Autowired
    ProjectRepository projectRepository;
    @Test
    public void ifNewProjectSaved_ThenSuccess(){
        Project project = new Project("Test Project","COmPLETE","New Project");
        projectRepository.save(project);
        assertEquals(5,projectRepository.findAll().size());
    }
}
