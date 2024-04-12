## Онлайн калькулятор на микросервисной архитектуре
### 1. Устанавливаем Intellij
<a href='https://postimages.org/' target='_blank'><img src='https://i.postimg.cc/hGcn9DQV/image.png' border='0' alt='image'/></a>
<a href='https://postimages.org/' target='_blank'><img src='https://i.postimg.cc/d1mL2sBC/image.png' border='0' alt='image'/></a>

### 2.Заходим на https://start.spring.io/ и конфигурируем проект.
<a href='https://postimg.cc/V5YfzXQZ' target='_blank'><img src='https://i.postimg.cc/HWMV4tSm/image.png' border='0' alt='image'/></a>

### 3.Открываем проект 
<a href='https://postimages.org/' target='_blank'><img src='https://i.postimg.cc/MH0Bs0m9/image.png' border='0' alt='image'/></a>

### 4.Создаем папку controller и в ней файл CalcController.java.
<a href='https://postimages.org/' target='_blank'><img src='https://i.postimg.cc/1tC286qY/image.png' border='0' alt='image'/></a>

### 5.Добавляем код для сложения и вычитания заданых чисел

```java
package ru.neoflex.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {
    @GetMapping("/plus/{a}/{b}")
    public Integer Sum (@PathVariable("a") Integer a, @PathVariable("b") Integer b){
        return a+b;
    }

    @GetMapping("/minus/{a}/{b}")
    public Integer Diff(@PathVariable("a") Integer a, @PathVariable("b") Integer b){
        return a-b;
    }
}
```
### 6.Тестируем контроллер
<a href='https://postimages.org/' target='_blank'><img src='https://i.postimg.cc/LspLdbnm/image.png' border='0' alt='image'/></a>
<a href='https://postimages.org/' target='_blank'><img src='https://i.postimg.cc/QCQFhpCv/image.png' border='0' alt='image'/></a>

### 7.Для подключения Swagger, добавляем в pom.xml зависимость
```java
<dependency>
		<groupId>org.springdoc</groupId>
		<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
		<version>2.0.4</version>
</dependency>
```       
### 8. Проверяем интерфейс Swagger по адресу http://localhost:8080/swagger-ui/index.html 
<a href='https://postimg.cc/7bCscYms' target='_blank'><img src='https://i.postimg.cc/PfygCCZj/image.png' border='0' alt='image'/></a>

<a href='https://postimg.cc/1n0Gx2Vz' target='_blank'><img src='https://i.postimg.cc/Px92Dkt1/image.png' border='0' alt='image'/></a>

<a href='https://postimg.cc/PpNj0sRW' target='_blank'><img src='https://i.postimg.cc/zBFGbqwc/image.png' border='0' alt='image'/></a>

### 9. Написать тесты на проект
#### Добавляем зависимость для тестов 
```java
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-test</artifactId>
	</dependency>
<dependency>
	<groupId>junit</groupId>
	<artifactId>junit</artifactId>
	<scope>test</scope>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-test-autoconfigure</artifactId>
</dependency>
<dependency>
	<groupId>org.testng</groupId>
	<artifactId>testng</artifactId>
	<version>RELEASE</version>
	<scope>compile</scope>
</dependency>
```
#### Добавляем тесты
```java
package ru.neoflex.practice.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(CalcController.class)
@AutoConfigureMockMvc
class CalcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalcController calcController;

    @Test
    public void Sum() throws Exception {

        int a = 5;
        int b = 3;
        int expectedSum = a+b;

        BDDMockito.given(calcController.Sum(a,b))
                .willReturn(a+b);

        mockMvc.perform(MockMvcRequestBuilders.get("/plus/{a}/{b}",a, b))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedSum)));
    }

    @Test
    public void Diff() throws Exception {

        int a = 40;
        int b = 13;
        int expectedDiff = a-b;

        BDDMockito.given(calcController.Diff(a,b))
                .willReturn(a-b);

        mockMvc.perform(MockMvcRequestBuilders.get("/minus/{a}/{b}", a, b))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedDiff)));
    }
}
```
#### Проверка тестов
<a href='https://postimages.org/' target='_blank'><img src='https://i.postimg.cc/3RKZQXXq/image.png' border='0' alt='image'/></a>
