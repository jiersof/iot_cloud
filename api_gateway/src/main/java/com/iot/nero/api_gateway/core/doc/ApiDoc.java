package com.iot.nero.api_gateway.core.doc;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.iot.nero.api_gateway.common.UtilJson;
import com.iot.nero.api_gateway.core.core.ApiStore;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2017/8/29
 * Time   下午6:29
 */
public class ApiDoc {


    public void genHtml(List<ApiStore.ApiRunnable> apiRunnableList, HttpServletResponse response) {

        try {
            UtilJson.JSON_MAPPER.configure(
                    SerializationFeature.WRITE_NULL_MAP_VALUES, true);

            String htmlHeader = "<div style='width:100%;'>";

            for(ApiStore.ApiRunnable apiRunnable:apiRunnableList){

                    htmlHeader+="<p style='width:100%;background:blue;color:#fff;margin-top:10px;padding:10px;'> "+apiRunnable.getApiName()+"</p>";
            }
            htmlHeader+="</div>";
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            if (htmlHeader != null) {
                response.getWriter().write(htmlHeader);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
