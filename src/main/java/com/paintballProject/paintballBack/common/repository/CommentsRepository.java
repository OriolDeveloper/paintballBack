package com.paintballProject.paintballBack.common.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.paintballProject.paintballBack.common.model.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    List<Comments> findByTargetTypeAndTargetIdAndParentIsNullOrderByCreatedAtAsc(
        String targetType, Long targetId
    );

}
