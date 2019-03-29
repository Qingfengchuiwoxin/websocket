package com.socket.web.pojo;

/**
 * @Author: 清风一阵吹我心
 * @ProjectName: socket
 * @Package: com.socket.web.pojo
 * @ClassName: User
 * @Description:
 * @CreateDate: 2018/11/9 10:55
 * @Version: 1.0
 **/
public class User {

    private String id;
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
