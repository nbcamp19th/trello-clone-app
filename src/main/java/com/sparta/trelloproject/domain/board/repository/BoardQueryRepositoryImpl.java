package com.sparta.trelloproject.domain.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.trelloproject.common.exception.NotFoundException;
import com.sparta.trelloproject.common.exception.ResponseCode;
import com.sparta.trelloproject.domain.board.entity.Board;
import com.sparta.trelloproject.domain.board.entity.QBoard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardQueryRepositoryImpl implements BoardQueryRepository {

    private final JPAQueryFactory queryFactory;
    @PersistenceContext
    private EntityManager entityManager;

    public BoardQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Board> findAllByWorkspaceIdWithDetails(Long workspaceId) {
        QBoard board = QBoard.board;

        List<Board> boards = queryFactory.selectDistinct(board)
                .from(board)
                .where(board.workspace.id.eq(workspaceId))
                .fetch();

        // 코드 통합 후 수정 예정
        boards.forEach(b -> {
            // b.getLists().size();
            // b.getLists().forEach(list -> list.getCards().size());
        });

        return boards;
    }

    @Override
    public Board findByIdWithDetails(Long boardId) {
        QBoard board = QBoard.board;

        Board result = queryFactory.selectDistinct(board)
                .from(board)
                .where(board.id.eq(boardId))
                .fetchOne();

        if (result == null) {
            throw new NotFoundException(ResponseCode.NOT_FOUND_BOARD);
        }

        // 코드 통합 후 수정 예정
        // result.getLists().size();
        // result.getLists().forEach(list -> list.getCards().size());

        return result;
    }
}
