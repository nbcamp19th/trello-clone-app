package com.sparta.trelloproject.domain.workspace.repository;

import com.sparta.trelloproject.domain.workspace.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long>, WorkspaceQueryRepository {
}
