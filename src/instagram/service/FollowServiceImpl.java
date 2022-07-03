package instagram.service;

import instagram.exception.UserAlreadyNotFollowException;

import java.util.*;

public class FollowServiceImpl implements FollowService {

    static Map<Integer, Set<Integer>> follow = new HashMap<>();

    @Override
    public boolean follow(Integer userId1, Integer userId2) {
        try {
            Set<Integer> followers;
            if (follow.containsKey(userId1)) {
                followers = follow.get(userId1);
            } else {
                followers = new HashSet<>();
            }
            followers.add(userId2);
            follow.put(userId1, followers);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean unFollow(Integer userId1, Integer userId2) {
        try {

            Set<Integer> followers = follow.get(userId1);
            if (!followers.contains(userId2)) {
                throw new UserAlreadyNotFollowException(userId1 + "Not follow " + userId2);
            }
            followers.remove(userId2);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Set<Integer> getFollowers(Integer userId) {
        return follow.get(userId);
    }
}
