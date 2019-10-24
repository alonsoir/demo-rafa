package hello.dto;

import java.util.List;

public class AjaxResponseBody {

    String msg;
    List<Greeting> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Greeting> getResult() {
        return result;
    }

    public void setResult(List<Greeting> result) {
        this.result = result;
    }
}
