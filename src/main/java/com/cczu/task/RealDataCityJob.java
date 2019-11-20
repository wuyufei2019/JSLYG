package com.cczu.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.cczu.thirdapi.city.WbHttpClient;
import com.cczu.zxjkyj.service.MonitorAlarmDataService;
import com.cczu.zxjkyj.service.MonitorPointMaintainService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RealDataCityJob {
    private final Logger logger = LoggerFactory.getLogger(RealDataCityJob.class);
    @Value("${city_wb_username}")
    private String username;
    @Value("${city_wb_password}")
    private String password;

    @Autowired
    private MonitorPointMaintainService maintainService;
    @Autowired
    private MonitorAlarmDataService alarmDataService;

    //@Scheduled(cron = "0 */4 * * * ?")  //每4分钟执行一次
    //@Scheduled(fixedRate = 100000L)
    public void MonitorData() throws Exception {
        Map map = new HashMap();
        map.put("UserID", username);
        map.put("Password", password);
        List<Map<String, Object>> list = maintainService.listMap();
        map.put("data", list);
        ArrayList<Object> param = Lists.newArrayList();
        param.add(map);

        String re = WbHttpClient.doPost("/MonitorData", JSON.toJSONString(param, SerializerFeature.WriteDateUseDateFormat));
        String result = re.substring(re.indexOf("[") + 1, re.indexOf("]"));
        JSONObject parse = JSON.parseObject(result);
        if (parse.getBoolean("Result")) {
            logger.info(parse.getString("Content"));
        }
    }

    //@Scheduled(cron = "0 0/1 * * * ?")  //每1分钟执行一次
    //@Scheduled(fixedRate = 100000L)
    public void job1() throws Exception {
        Map map = new HashMap();
        map.put("UserID", username);
        map.put("Password", password);
        List<Map> list = alarmDataService.listAlarmNoReport();
        if (list.size() > 0) {
            map.put("data", list);
            ArrayList<Object> param = Lists.newArrayList();
            param.add(map);
            //序列化时忽略 id
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
            filter.getExcludes().add("id");
            String json = JSON.toJSONString(param, filter,
                    SerializerFeature.WriteDateUseDateFormat);
            String re = WbHttpClient.doPost("/WarningData", json);
            logger.info("上传报警数据" + json);
            //格式化获取结果再序列化
            String result = re.substring(re.indexOf("[") + 1, re.indexOf("]"));
            JSONObject parse = JSON.parseObject(result);
            if (parse.getBoolean("Result")) {
                logger.info(parse.getString("Content"));
                //更新所有报警数据为已上报
                String ids = "";
                for (Map m : list) {
                    ids += m.get("id") + ",";
                }
                alarmDataService.updateReport(ids.substring(0, ids.length() - 1));
            }
        }

    }

}
