import com.security.web.entity.Permission;
import com.security.web.entity.User;
import com.web.security.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author YanShen.Wu
 * @date 2018-05-17 01:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserMapperTest {

    private Logger log = Logger.getLogger("test");

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindByUsername() {
        User user = userMapper.findByUsername("jack");
        System.out.println(user.getRealname());
    }

    @Test
    public void testFindPermissionByUsername() {
        List<Permission> list = userMapper.findPermissionByUsername("jack");
        for (Permission perm : list) {
            System.out.println(perm.getPermissionname() + " - " + perm.getPermissionflag());
            log.debug(perm.getPermissionname() + " - " + perm.getPermissionflag());
        }
    }
}

