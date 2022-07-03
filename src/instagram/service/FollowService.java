package instagram.service;

import java.util.Set;

public interface FollowService {
    boolean follow(Integer userId1, Integer userId2);

    boolean unFollow(Integer userId1, Integer userId2);

    Set<Integer> getFollowers(Integer userId);
}
