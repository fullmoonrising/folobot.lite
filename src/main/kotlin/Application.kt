import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.everbald")
@EnableFeignClients(basePackages = ["com.everbald"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

