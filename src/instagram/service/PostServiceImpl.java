package instagram.service;

import instagram.exception.NoFollowersException;
import instagram.models.Post;

import java.util.*;

public class PostServiceImpl implements PostService {

    static Map<Integer, List<Post>> userPosts = new HashMap<>();

    static FollowService followService = new FollowServiceImpl();

    @Override
    public boolean post(Integer userId, String message) {
        List<Post> posts;
        if (userPosts.containsKey(userId)) {
            posts = userPosts.get(userId);
        } else {
            posts = new ArrayList<>();
        }
        posts.add(new Post(message, System.currentTimeMillis()));
        userPosts.put(userId, posts);
        return true;
    }

    @Override
    public List<Post> getPost(Integer userId, Integer numberOfPost, boolean self) throws Exception {
        if (self) {
            return getPost(Collections.singletonList(userId), numberOfPost);
        } else {
            Set<Integer> followers = followService.getFollowers(userId);
            if (followers.isEmpty()) {
                throw new NoFollowersException("You don't follow anyone");
            }
            List<Integer> userIds = new ArrayList<>(followers);
            return getPost(userIds, numberOfPost);
        }
    }


    private List<Post> getPost(List<Integer> usersIds, Integer numberOfPost) throws Exception {


        List<List<Post>> posts = new ArrayList<>();
        usersIds.forEach(a -> posts.add(userPosts.get(a)));
        List<Post> sortedData = new ArrayList<>();

        posts.forEach(a -> {
            if (a != null && !a.isEmpty()) {
                int index = numberOfPost > a.size() ? a.size() : numberOfPost;
                for (int i = index - 1; i >= 0; i--) {
                    sortedData.add(a.get(i));
                }
            }
        });
        sortedData.sort(Comparator.comparing(Post::getTs));
        List<Post> response = new ArrayList<>();
        int index = numberOfPost > sortedData.size() ? sortedData.size() : numberOfPost;
        for (int i = 0; i < index; i++) {
            response.add(sortedData.get(i));
        }
        return response;
    }
}


