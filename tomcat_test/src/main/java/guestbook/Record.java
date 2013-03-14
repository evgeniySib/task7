package guestbook;

import java.util.Date;


public class Record {
    private int id;
    private Date postDate;
    private String nickName;
    private String message;

    public int getId() {
        return id;
    }

    public Date getPostDate() {
        return postDate;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMessage() {
        if(message.equals("")){
            message="&nbsp";
        }
        return message;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
