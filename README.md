spring boot学习
主要内容包括：
一、静态资源下的index.html为欢迎页，访问路径：http://localhost:8080/
二、静态资源下的favicon.ico为页面访问ico
三、测试Rest访问风格
    form表单默认只有get和post提交，put和delete提交需要开启设置：
    spring.mvc.hiddenmethod.filter.enabled=true
四、常用参数注解使用
五、使用自己的方式往容器中放一个WebMvcConfigurer组件
六、开启参数方式的内容协商
    1.访问路径带format=json,确定返回数据类型
    2. 自定义类型的converter
七、添加自己的转换器，转换请求参数

