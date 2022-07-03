package instagram.main;

import instagram.exception.NoFollowersException;
import instagram.models.Post;
import instagram.service.FollowService;
import instagram.service.FollowServiceImpl;
import instagram.service.PostService;
import instagram.service.PostServiceImpl;

import java.util.List;

public class Entry {

    static FollowService followService = new FollowServiceImpl();
    static PostService postService = new PostServiceImpl();

    public static void main(String[] args) throws Exception {
        Integer phoenix = 1;
        Integer sova = 2;
        Integer reyna = 4;

        followService.follow(sova, phoenix);
        followService.follow(sova, reyna);

        postService.post(reyna, "Hello This is Reyna");
        postService.post(phoenix, "Hello This is phoenix");
        postService.post(reyna, "Hello This is Reyna again");
        postService.post(phoenix, "Hello This is phoenix again");

        List<Post> minePost = postService.getPost(sova, 2, true);
        List<Post> othersPost = postService.getPost(sova, 2, false);
        minePost.forEach(System.out::println);
        othersPost.forEach(System.out::println);
    }

}
