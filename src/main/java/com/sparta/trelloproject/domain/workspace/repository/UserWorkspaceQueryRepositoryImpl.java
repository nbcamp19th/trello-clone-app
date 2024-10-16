package com.sparta.trelloproject.domain.workspace.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.trelloproject.domain.board.dto.BoardAuthorityDto;
import com.sparta.trelloproject.domain.list.dto.ListAuthorityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.sparta.trelloproject.domain.board.entity.QBoard.board;
import static com.sparta.trelloproject.domain.list.entity.QLists.lists;
import static com.sparta.trelloproject.domain.workspace.entity.QUserWorkspace.userWorkspace;

@Repository
@RequiredArgsConstructor
public class UserWorkspaceQueryRepositoryImpl implements UserWorkspaceQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<BoardAuthorityDto> findUserWorkspaceWithUserIdAndBoardId(long workspaceId, long userId, long boardId) {
        return Optional.ofNullable(
                jpaQueryFactory.select(
                                Projections.fields(BoardAuthorityDto.class,
                                        board.id.as("boardId"),
                                        board.title.as("title"),
                                        board.backgroundColor.as("backgroundColor"),
                                        board.lists.size().as("listCount"),
                                        board.workspace.id.as("workspaceId"),
                                        userWorkspace.workSpaceUserRole.as("workspaceAuthority")
                                )
                        )
                        .distinct()
                        .from(userWorkspace)
                        .join(board)
                        .on(userWorkspace.workspace.id.eq(board.workspace.id))
                        .where(
                                userWorkspace.workspace.id.eq(workspaceId),
                                userWorkspace.user.id.eq(userId),
                                board.id.eq(boardId)
                        )
                        .fetchFirst()
        );
    }

    @Override
    public Optional<ListAuthorityDto> findUserWorkspaceWithUserIdAndListId(long workspaceId, long userId, long listId) {
        return Optional.ofNullable(
                jpaQueryFactory.select(
                                Projections.fields(ListAuthorityDto.class,
                                        lists.id.as("listId"),
                                        userWorkspace.workSpaceUserRole.as("workspaceAuthority"),
                                        lists.board.id.as("boardId"),
                                        lists.sequence.as("sequence"),
                                        lists.title.as("title")
                                )
                        )
                        .distinct()
                        .from(userWorkspace)
                        .join(board)
                        .on(userWorkspace.workspace.id.eq(board.workspace.id))
                        .join(lists)
                        .on(board.id.eq(lists.board.id))
                        .where(
                                userWorkspace.workspace.id.eq(workspaceId),
                                userWorkspace.user.id.eq(userId),
                                lists.id.eq(listId)
                        )
                        .fetchFirst()
        );
    }
}
