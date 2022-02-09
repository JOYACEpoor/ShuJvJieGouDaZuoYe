package Data;

import java.io.Serializable;

public class Cup implements Serializable {
    public long code;
    public boolean flag;
    public long phone;
    public Cup(){
        code=-1;
        flag=true;
        phone=-1;
    }
    public Cup(int code,long phone,boolean flag){
        this.code=code;
        this.phone=phone;
        this.flag=flag;
    }
}
