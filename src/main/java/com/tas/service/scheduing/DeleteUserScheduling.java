package com.tas.service.scheduing;

import com.tas.common.Loggable;
import com.tas.entity.LogDeleteUserFailure;
import com.tas.repository.LogDeleteUserFailureRepository;
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
    UserRepository userRepository;

    @Autowired
    LogDeleteUserFailureRepository failureRepository;

    @Scheduled(cron = "${cron}")
    public void deleteUser() {
        getLogger().info("Start delete user");
        EntityManagerFactory factory = factoryBean.getObject();
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        getLogger().info("retryNumber =" + retryNumber);
        for (int i = 0; i < retryNumber; i++) {
            getLogger().info("Delete round " + (i + 1));
            try {
                Query query = manager.createNativeQuery("select  id from user  where is_active=0");
                List<Integer> listId = query.getResultList();
                getLogger("Number User not active =" + listId.size());

                if (listId.size() == 0) break;

                getLogger().info(getStringfromListId(listId, "id"));
                Query queryDeleteUserRole = manager.createNativeQuery("delete from user_role where " + getStringfromListId(listId, "id_user"));
                queryDeleteUserRole.executeUpdate();
                Query queryDeleteUserPosition = manager.createNativeQuery("delete from user_position where " + getStringfromListId(listId, "id_user"));
                queryDeleteUserPosition.executeUpdate();
                Query queryDeleteUserDepartment = manager.createNativeQuery("delete from user_department where " + getStringfromListId(listId, "id_user"));
                queryDeleteUserDepartment.executeUpdate();
                Query queryDeleteUserProject = manager.createNativeQuery("delete from user_project_position where  " + getStringfromListId(listId, "id_user"));
                queryDeleteUserProject.executeUpdate();
                Query queryDeleteUser = manager.createNativeQuery("delete from user where  " + getStringfromListId(listId, "id"));
                queryDeleteUser.executeUpdate();
                transaction.commit();
                getLogger().info("Delete successfull");
                break;
            } catch (Exception e) {
                transaction.rollback();
                if (i == (retryNumber - 1)) {
                    getLogger().error(new Date("delete falure");
                } else {
                    getLogger().warn("round" + (i + 1) + "falure");
                }


                LogDeleteUserFailure userFailure = new LogDeleteUserFailure();
                userFailure.setMesseage(e.getMessage());
                e.printStackTrace();
                userFailure.setOccurredAt(new Date(System.currentTimeMillis()));
                StackTraceElement[] stackTraceElements = e.getStackTrace();
                StringBuilder builder = new StringBuilder();
                for (int i1 = 0; i1 < stackTraceElements.length; i1++) {
                    builder.append(stackTraceElements[i].getClassName() + "   /n");
                }
                userFailure.setStackTrace(builder.toString());
                failureRepository.save(userFailure);
            }
        }


    }

    private String getStringfromListId(List list, String field) {
        StringBuilder builder = new StringBuilder(" " + field + " = " + list.get(0) + " ");
        if (list.size() == 1) return builder.toString();
        for (int i = 1; i < list.size(); i++) {
            builder.append(" or " + field + " = " + list.get(i));
        }
        return builder.toString();
    }
}
