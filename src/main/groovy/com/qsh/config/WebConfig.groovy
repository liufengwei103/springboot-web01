package com.qsh.config

import com.qsh.converter.MyConverter
import com.qsh.domain.Pet
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.filter.HiddenHttpMethodFilter
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.util.UrlPathHelper

@Configuration
class WebConfig /*implements WebMvcConfigurer */ {

    /**
     * 修改表单提交隐藏参数名（delete put）
     */
    @Bean
    HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter()
        filter.setMethodParam("_m")
        return filter
    }

    /**
     * 使用自己的方式往容器中放一个WebMvcConfigurer组件
     * 有两种方法：
     * （1）使用@Bean注入一个WebMvcConfigurer组件
     * （2）让该配置类实现WebMvcConfigurer接口，修改要修改的方法
     */
    @Bean
    WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            //自定义MessageConverter
            @Override
            void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new MyConverter())
            }

            @Override
            void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper()
                //不移除；后面的内容。这样矩阵变量功能才能生效
                urlPathHelper.setRemoveSemicolonContent(false)
                configurer.setUrlPathHelper(urlPathHelper)
            }

            /**
             * 添加自己的转换器，转换请求参数
             * @param registry
             */
            @Override
            void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new Converter<String, Pet>() {
                    @Override
                    Pet convert(String s) {
                        //阿猫，3
                        if(s){
                            String[] split = s.split('，')

                            return new Pet(name:split[0],age:Integer.parseInt(split[1]))
                        }
                        return null
                    }
                })
            }
        }
    }
//    @Override
//    void configurePathMatch(PathMatchConfigurer configurer) {
//        UrlPathHelper urlPathHelper = new UrlPathHelper()
//        //不移除；后面的内容。这样矩阵变量功能才能生效
//        urlPathHelper.setRemoveSemicolonContent(false)
//        configurer.setUrlPathHelper(urlPathHelper)
//    }
}
