package instagram.models;

public class Post {
    String message;
    Long ts;

    public Post(String message, Long ts) {
        this.message = message;
        this.ts = ts;
    }

    public Post() {
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Post{" + "message='" + message + '}';
    }
}
