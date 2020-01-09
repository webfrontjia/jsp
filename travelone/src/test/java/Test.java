import com.travelone.domain.ResultCode;
import com.travelone.domain.ResultInfo;
import com.travelone.service.UserService;
import com.travelone.service.impl.UserServiceImpl;

public class Test {
    @org.junit.Test
    public void login(){
        UserService userService = new UserServiceImpl();
        ResultInfo wangwu = userService.login("zhangsan", "111111a");
        System.out.println("wangwu = " + wangwu);
    }

    @org.junit.Test
    public void show() {

        String user = "driverClassName";

        String msg1 = TestEnum.getMsg1(user);

        System.out.println("msg1 = " + msg1);

    }
}
