package com.kevinnoon.projectmanagement.services;

import com.kevinnoon.projectmanagement.dto.ProjectStage;
import com.kevinnoon.projectmanagement.dto.TimeChartData;
import com.kevinnoon.projectmanagement.entities.Employee;
import com.kevinnoon.projectmanagement.entities.Project;
import com.kevinnoon.projectmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public List<ProjectStage> getProjectStage() {
        return projectRepository.projectStage();
    }

    public Project getProjectById(long id) {
        return projectRepository.findById(id).get();
    }

    public void deleteById(long id) {
        try {
            projectRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
        }
    }
        public List<TimeChartData> getTimeData(){
            System.out.println(projectRepository.getTimeData());
        return projectRepository.getTimeData();
    }
    public Project findByProjectId(long id){
        return projectRepository.findByProjectId(id);
    }
}
