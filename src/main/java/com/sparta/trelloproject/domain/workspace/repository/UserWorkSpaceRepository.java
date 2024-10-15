package com.sparta.trelloproject.domain.workspace.repository;

import com.sparta.trelloproject.domain.workspace.entity.UserWorkspace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWorkSpaceRepository extends JpaRepository<UserWorkspace , Long> {
    UserWorkspace findByWorkspaceIdAndUserId(Long workspaceId , Long userId);
}
