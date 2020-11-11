package com.tas.service.scheduing.timer;

import com.tas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

@Component
public class DeleteUserWithTimer {
    @Autowired
    UserRepository userRepository;
    @Bean
    public Timer delete(){

        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
//                userRepository.deleteNotActive();
            }
        };
        long delay=30*24*3600*1000;
//        timer.schedule(timerTask,0,delay);

        return timer;
    }
}
