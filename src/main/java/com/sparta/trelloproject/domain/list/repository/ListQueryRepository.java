package com.sparta.trelloproject.domain.list.repository;

public interface ListQueryRepository {
    void updateSequence(long listId, int sequence);

    void updateSequenceIncrement(int sequence, int originSequence, long boardId);

    void updateSequenceDecrement(int sequence, int originSequence, long boardId);
}
