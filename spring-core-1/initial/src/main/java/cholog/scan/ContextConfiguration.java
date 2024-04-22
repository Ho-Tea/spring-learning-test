package cholog.scan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "cholog.scan")
/*
ComponentScan에 대해 학습하고, ComponenetScanBean을 Bean으로 등록하기
 */
public class ContextConfiguration {

//    @Bean
//    public ComponentScanBean componentScanBean(){
//        return new ComponentScanBean();
//    };
}
