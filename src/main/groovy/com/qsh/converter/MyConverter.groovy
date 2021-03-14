package com.qsh.converter

import com.qsh.domain.User
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import org.springframework.lang.Nullable

/**
 * 自定义类型的converter
 */
class MyConverter implements HttpMessageConverter<User> {
    @Override
    boolean canRead(Class<?> aClass, @Nullable MediaType mediaType) {
        return false
    }

    @Override
    boolean canWrite(Class<?> aClass, @Nullable MediaType mediaType) {
        //方法返回值类型是User类型，即为true
        return aClass.isAssignableFrom(User.class)
    }

    /**
     * 服务器要统计所有的MessageConverter都能写出哪些内容
     *
     * 该converter的操作类型是application/x-my
     */
    @Override
    List<MediaType> getSupportedMediaTypes() {
        return MediaType.parseMediaTypes("application/x-my")
    }

    @Override
    User read(Class<? extends User> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null
    }

    @Override
    void write(User user, @Nullable MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        //自定义协议数据的写出
        String data = "${user.username},${user.age},${user.birth}"

        //写出去
        OutputStream body = httpOutputMessage.getBody()
        body.write(data.getBytes())
    }
}
