package gcxy.security.utils;

public class ResInfo {
    private int state;

    private String msg;

    private Object obj;

    public ResInfo(int state, String msg) {
        this.state = state;
        this.msg = msg;
        this.obj = null;
    }

    public ResInfo(int state, String msg, Object obj) {
        this.state = state;
        this.msg = msg;
        this.obj = obj;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    // 200 成功
    // 302 验证错误，403 权限错误
    // 400 一般错误，401 参数错误，402 文件系统错误，407 数据库错误
    public static ResInfo success() {
        return new ResInfo(200, "好家伙，你是真的牛逼，成功了");
    }

    public static ResInfo success(Object obj) {
        return new ResInfo(200, "好家伙，你是真的牛逼，成功了", obj);
    }

    public static ResInfo error_normal(String msg) {
        return new ResInfo(400, msg);
    }

    public static ResInfo error_database(String msg) {
        return new ResInfo(407, msg);
    }

    public static ResInfo error_param(String msg) {
        return new ResInfo(401, msg);
    }

    public static ResInfo error_file(String msg) {
        return new ResInfo(402, msg);
    }
}
