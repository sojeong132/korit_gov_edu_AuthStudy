package com.korit.authstudy.init;

import com.korit.authstudy.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@RequiredArgsConstructor
class OptionalStudy<T> {
    private final T present;

    public void ifPresentOrElse(Consumer<T> action, Runnable runnable) {
        if(present == null) {
            action.accept(present);
        }else {
            runnable.run();
        }
    }
}

@Component
public class Rambda implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        User user = User.builder()
                .id(100)
                .username("test")
                .password("1234")
                .build();
        OptionalStudy<User> optionalStudy = new OptionalStudy<>(user);
        Consumer<User> consumer = new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println("user객체 찾음: " + user);
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("user객체 못 찾아서 여기서 다른 작업 실행");
            }
        };
        optionalStudy.ifPresentOrElse(consumer,runnable);

        Consumer<User> consumerLambda = (u) -> {
            System.out.println("user객체 찾음: " + u);
        };
        Runnable runnableLambda = () -> {
            System.out.println("user객체 못 찾아서 여기서 다른 작업 실행");
        };
        optionalStudy.ifPresentOrElse(consumerLambda, runnableLambda);

        optionalStudy.ifPresentOrElse(
            (u) -> {
                System.out.println("user객체 찾음: " + u);
            },
            () -> {
                System.out.println("user객체 못 찾아서 여기서 다른 작업 실행");
            }
        );
    }
}
