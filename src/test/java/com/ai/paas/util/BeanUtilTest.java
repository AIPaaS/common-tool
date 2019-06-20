package com.ai.paas.util;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ai.paas.util.domain.User;
import com.ai.paas.util.domain.UserWithGender;

public class BeanUtilTest {



    @Test
    public void testCopyObject()  {
        User user = new User("123456", "test");
        UserWithGender userB = new UserWithGender();
        BeanUtil.deepCopy(user, userB);
        assertTrue(userB.getUserName().equals(user.getUserName()));
    }

    @Test
    public void testCopyObjectObject() {
        User user = new User("123456", "test");
        UserWithGender userB = new UserWithGender();
        BeanUtil.copy(user, userB);
        assertTrue(userB.getUserName().equals(user.getUserName()));
        assertNull(userB.getGender());
    }

}
