package com.kevinnoon.projectmanagement.repository;

import com.kevinnoon.projectmanagement.dto.ProjectStage;
import com.kevinnoon.projectmanagement.dto.TimeChartData;
import com.kevinnoon.projectmanagement.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "api-projects",path = "api-projects")
public interface ProjectRepository extends PagingAndSortingRepository<Project,Long> {
    @Override
    public List<Project> findAll();
    @Query(nativeQuery = true,value = "SELECT stage as label, COUNT(*) as count FROM project PROJECT GROUP BY stage")
    public List<ProjectStage> projectStage();
    @Query(nativeQuery = true,value = "SELECT name as projectName, start_date as startDate, end_date as endDate FROM project " +
            "WHERE start_date is not null")
    public List<TimeChartData> getTimeData();
    Project findByProjectId(long id);
}
