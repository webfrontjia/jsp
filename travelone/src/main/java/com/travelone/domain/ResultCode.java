package com.travelone.domain;

public class ResultCode {


    public static final int CHECKCODEISNULL=2001;
    public static final String CHECKCODEISNULLMSG="验证码为空，请重新输入";

    public static final int CHECKCODENOTEQUAL=2002;
    public static final String CHECKCODENOTEQUALMSG="验证码不一致，请重新输入";

    public static final int PASSWORDNOEQUAL=2003;
    public static final String PASSWORDNOEQUALMSG="密码输入错误，请重新输入！";

    public static final int NOREGIST=3001;
    public static final String NOREGISTMSG="该用户未注册，请先注册！";

    public static final int USERISNOACTIVE=3002;
    public static final String USERISNOACTIVEMSG="该账户未激活，请去邮箱激活！";

    public static final int USEREXIST=3003;
    public static final String USEREXISTMSG="该用户已存在，请重新输入！";

    public static final int SUCCESS=2000;
    public static final int FAILURECODE=4000;

    public static final int LOGOUT=3008;
    public static final int NOTLOGIN=5000;

    public static final int AUTOLOGIN=6000;
    public static final int NOTAUTOLOGIN=6001;

}
