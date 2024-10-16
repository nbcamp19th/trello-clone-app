package com.sparta.trelloproject.domain.list.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.sparta.trelloproject.domain.list.entity.QLists.lists;

@Repository
@RequiredArgsConstructor
public class ListQueryRepositoryImpl implements ListQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void updateSequence(long listId, int sequence) {
        jpaQueryFactory.update(lists)
                .set(lists.sequence, sequence)
                .where(lists.id.eq(listId))
                .execute();
    }

    @Override
    public void updateSequenceIncrement(int sequence, int originSequence, long boardId) {
        jpaQueryFactory.update(lists)
                .set(lists.sequence, lists.sequence.add(1))
                .where(lists.sequence.goe(sequence), (lists.sequence.lt(originSequence)), lists.board.id.eq(boardId))
                .execute();
    }

    @Override
    public void updateSequenceDecrement(int sequence, int originSequence, long boardId) {
        jpaQueryFactory.update(lists)
                .set(lists.sequence, lists.sequence.add(-1))
                .where(lists.sequence.loe(sequence), (lists.sequence.gt(originSequence)), lists.board.id.eq(boardId))
                .execute();
    }
}
