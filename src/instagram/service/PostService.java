package instagram.service;

import instagram.exception.NoFollowersException;
import instagram.models.Post;

import java.util.List;

public interface PostService {
    boolean post(Integer userId, String message);

    List<Post> getPost(Integer userId, Integer numberOfPost, boolean self) throws Exception;
}
