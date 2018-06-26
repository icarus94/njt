package njt.myproject.dax.repository;

import njt.myproject.dax.models.UserHasTodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserHasTodoListRepository extends JpaRepository<UserHasTodoList, Long> {

    @Query("from UserHasTodoList where user_id = :user_id and todo_list_id = :todo_list_id")
    UserHasTodoList findByReviewId(@Param("user_id") int user_id);

    @Query("from UserHasTodoList where user_id = :user_id")
    List<UserHasTodoList> getByUserId(@Param("user_id") int user_id);
}
