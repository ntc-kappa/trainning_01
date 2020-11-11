package com.tas.service.scheduing;

import com.tas.common.Loggable;
import com.tas.entity.LogDeleteUserFailure;
import com.tas.repository.LogDeleteUserFailureRepository;
import com.tas.repository.ProjectRepository;
import com.tas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

@PropertySource("classpath:application.properties")
@Component
public class DeleteUserScheduling implements Loggable {
    @Autowired
    LocalContainerEntityManagerFactoryBean factoryBean;

    @Value("${retry}")
    private int retryNumber;


    @Autowired
    LogDeleteUserFailureRepository failureRepository;

    @Scheduled(cron = "${cron}")
    public void deleteUser() {
        getLogger().info("Start delete user");
        EntityManager manager = getManager();
        EntityTransaction transaction = manager.getTransaction();

        getLogger(). info("retryNumber =" + retryNumber);

        Query query = manager.createNativeQuery("select id from user  where is_active=0");
        System.out.println(query.toString());
        List<Integer> listId = query.getResultList();

        getLogger().info("Number User not active =" + listId.size());
        if (listId.size() == 0) return;

        for (Integer integer : listId) {
            for (int j = 0; j < retryNumber; j++) {
                getLogger().info("Delete " + integer + " round " + (j + 1));
                transaction.begin();
                try {
                    getLogger().info("Start Delete " + integer);
                    deleteMoreUser(integer, "user_role", "id_user", manager);
                    deleteMoreUser(integer, "user_position", "id_user", manager);
                    deleteMoreUser(integer, "user_department", "id_user", manager);
                    deleteMoreUser(integer, "user_project_position", "id_user", manager);
                    deleteMoreUser(integer, "user", "id", manager);
                    transaction.commit();
                    getLogger().info("Delete " + integer + " successfull");
                    break;
                } catch (Exception e) {
                    transaction.rollback();
                    if (j == (retryNumber - 1)) {
                                  getLogger().error("delete " + integer + " falure");
                    } else {
                        getLogger().warn("Delete" + integer + " round" + (j + 1) + " falure");
                    }
                    writeLog(integer, e);
                }
            }


        }


    }

    private EntityManager getManager() {
        EntityManagerFactory factory = factoryBean.getObject();
        EntityManager manager = factory.createEntityManager();

        return manager;
    }

    private void writeLog(Integer id, Exception e) {
        LogDeleteUserFailure userFailure = new LogDeleteUserFailure();
        userFailure.setMesseage(e.getMessage());
        e.printStackTrace();
        userFailure.setOccurredAt(new Date(System.currentTimeMillis()));
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        StringBuilder builder = new StringBuilder();
        for (int i1 = 0; i1 < stackTraceElements.length; i1++) {
            builder.append(stackTraceElements[i1].getClassName() + "   /n");
        }
        userFailure.setIdUser(id);
        userFailure.setStackTrace(builder.toString());
        failureRepository.save(userFailure);
    }

    private void deleteMoreUser(Integer id, String table, String field, EntityManager manager) {
        Query queryDelete = manager.createNativeQuery("delete from " + table + " where " + field + " = :_id ");
        queryDelete.setParameter("_id", id);
        queryDelete.executeUpdate();
    }


    @Autowired
    ProjectRepository projectRepository;

    private String getStringfromListId(List list, String field) {
        StringBuilder builder = new StringBuilder(" " + field + " = " + list.get(0) + " ");
        if (list.size() == 1) return builder.toString();
        for (int i = 1; i < list.size(); i++) {
            builder.append(" or " + field + " = " + list.get(i));
        }
        return builder.toString();
    }

    @Autowired
    UserRepository userRepository;

    private List<Integer> getListIdDelete() {
        return userRepository.findUserNotActive();
    }
}
