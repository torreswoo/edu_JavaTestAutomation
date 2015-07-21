package com.skplanet.tutorial.testautomation.todo.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static com.skplanet.tutorial.testautomation.util.Tuples.*;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TodoServiceTestConfig.class)
@Transactional
public class TodoServiceTest {

    private static final String TEST_TODO = "test_todo";
    
    @Autowired TodoService service;
    
    private JdbcTemplate jt;
    
    
    @Autowired public void setDataSource(DataSource dataSource) {
        this.jt = new JdbcTemplate(dataSource);
    }
    
    @Test public void TODO_추가_가능() 
    {
        //when
        Item newItem = service.add(TEST_TODO);
        
        //than
        assertThat(newItem.getText(), is(TEST_TODO));
        assertThat(newItem.getId(), is(not(0)));
    }
        
    @Test public void 초기에는_데이터가_없음()
    {
        //when
        List<Item> list = service.findAll();
        
        //than
        assertThat(list, is(hasSize(0)));
    }
    
    @SuppressWarnings("deprecation")
    @Test public void 데이터를_넣은_개수만큼_목록을_얻음()
    {
        //given
        JdbcTestUtils.executeSqlScript(jt, 
                new ClassPathResource("com/skplanet/tutorial/testautomation/todo/domain/insert_test_todos.sql"), 
                false);
        
        //when
        List<Item> list = service.findAll();
        
        //than
        assertThat(list, is(hasSize(3)));
    }
    
    @Test public void TODO를_수행_완료로_표시할_수_있음()
    {
        Item newItem = service.add(TEST_TODO);
        service.checkDone(newItem.getId());
        Tuple2<Boolean, Timestamp> result = 
                jt.<Tuple2<Boolean, Timestamp>>queryForObject(
                        "SELECT status, done_date FROM todos WHERE id="+newItem.getId(), 
                        (rs, rownum) -> tuple(rs.getBoolean("status"), rs.getTimestamp("done_date")));
        assertThat(result.v1, is(true));
        assertThat(result.v2, is(notNullValue()));
    }

    @AfterTransaction public void afterTx() 
    {
        System.out.println("AFTER TEST: " + JdbcTestUtils.countRowsInTable(jt, "todos"));
    }
}
