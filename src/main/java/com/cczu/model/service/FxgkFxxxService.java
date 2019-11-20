package com.cczu.model.service;

import com.cczu.model.dao.*;
import com.cczu.model.entity.*;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.dao.BarrioDao;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jason
 * @date 2017年8月8日
 */
@Transactional(readOnly = true)
@Service("SxgkfxxxService")
public class FxgkFxxxService {
    @Resource
    private FxgkFxxxDao fxgkFxxxDao;
    @Resource
    private BarrioDao barrioDao;
    @Autowired
    private IBisQyjbxxService bisQyjbxxService;
    @Resource
    private ITic_AccidentHandleDao accidentHandleDao;
    @Resource
    private YhpcRiskPointContentDao yhpcRiskPointContentDao;
    @Resource
    private FxgkFxzpzDao fxgkfxzpzdao;
    @Resource
    private FxgkTjfxDao fxgkTjfxDao;
    @Resource
    private YhpcRiskPointContentDao yhpcriskpointcontentdao;

    /**
     * 分页显示
     *
     * @param mapData
     * @return
     */
    public Map<String, Object> dataGrid(Map<String, Object> mapData) {

        List<Map<String, Object>> list = fxgkFxxxDao.dataGrid(mapData);
        //System.out.println(list);
        int getTotalCount = fxgkFxxxDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }

    public String getAllType() {
        // TODO Auto-generated method stub
        List<Tdic_AccidentHandle> list = accidentHandleDao.findAllList();
        List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
        for (Tdic_AccidentHandle dict : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", dict.getID());

            map.put("text", dict.getM1());
            map.put("extra", dict.getM2());
            arrylist.add(map);
        }

        return JsonMapper.getInstance().toJson(arrylist);

    }

    /**
     * 导出
     *
     * @param response
     * @param mapData
     */
    public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
        String[] title = {"编号", "工作场所", "环节", "部位", "责任部门", "责任人", "风险描述", "主要管控措施", "主要依据", "事故类型", "风险等级"};
        String[] keys = {"m0", "m1", "m5", "m6", "depname", "m13", "m7", "m10", "m12", "m8", "fxfj"};
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(mapData.get("colval").toString())) {
            title = (mapData.get("coltext").toString()).split(",");
            keys = (mapData.get("colval").toString()).split(",");
        }
        String fileName = "企业较大危险因素辨识与防范控制措施登记表.xls";
        List<Map<String, Object>> list = fxgkFxxxDao.getExport(mapData);
        new ExportExcel(fileName, title, keys, list, response, true);

    }

    public List<Map<String, Object>> getAllByQyid(Map<String, Object> mapData) {
        List<Map<String, Object>> list = fxgkFxxxDao.getAllByQyid(mapData);
        return list;
    }

    public String addInfo(FXGK_AccidentRisk sgfx, String bdnrids) {
        String datasuccess = "success";

        if ("1".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
            sgfx.setID1(UserUtil.getCurrentShiroUser().getQyid());
        }
        sgfx.setS1(new java.sql.Timestamp(new java.util.Date().getTime()));
        sgfx.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
        sgfx.setS3(0);

        String bindcontent = sgfx.getBindcontent();
        String rfid = sgfx.getRfid();
        //判断绑定二维码rfid是否重复
        if (checkSameBuildContent(0, bindcontent) && (!com.cczu.util.common.StringUtils.isEmpty(bindcontent))) {
            datasuccess = "ewmerror";
        } else if (checkSameRfid(0, rfid) && (!com.cczu.util.common.StringUtils.isEmpty(rfid))) {
            datasuccess = "rfiderror";
        } else {
            try {
                fxgkFxxxDao.save(sgfx);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                datasuccess = "failed";
            }
        }
        long id = sgfx.getID();
        //风险点添加成功后，绑定巡检内容
        if (id > 0) {
            String[] arrids = bdnrids.split(",");
            try {
                for (int i = 0; i < arrids.length; i++) {
                    bulidCheckContent(id, Long.parseLong(arrids[i]));
                }
            } catch (Exception e) {
                datasuccess = "bderror";
            }
        }
        updateQyfxdj(sgfx.getID1());
        // 返回结果
        return datasuccess;
    }

    public long addInfoReturnID(FXGK_AccidentRisk sgfx) {
        fxgkFxxxDao.save(sgfx);
        return sgfx.getID();
    }

    public String updateInfo(FXGK_AccidentRisk sgfx, String bdnrids) {
        String datasuccess = "success";
        if ("1".equals(UserUtil.getCurrentShiroUser().getUsertype())) {
            sgfx.setID1(UserUtil.getCurrentShiroUser().getQyid());
        }
        sgfx.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
        String bindcontent = sgfx.getBindcontent();
        String rfid = sgfx.getRfid();
        long id = sgfx.getID();
        //判断绑定二维码rfid是否重复
        if (checkSameBuildContent(id, bindcontent) && (!com.cczu.util.common.StringUtils.isEmpty(bindcontent))) {
            datasuccess = "ewmerror";
        } else if (checkSameRfid(id, rfid) && (!com.cczu.util.common.StringUtils.isEmpty(rfid))) {
            datasuccess = "rfiderror";
        } else {
            try {
                fxgkFxxxDao.updateInfo(sgfx);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                datasuccess = "failed";
            }
        }
        //风险点update后,删除绑定内容重新绑定
        yhpcriskpointcontentdao.deleteInfoByID1(sgfx.getID());
        String[] arrids = bdnrids.split(",");

        int len = arrids.length;
        try {
            for (int i = 0; i < len; i++) {
                bulidCheckContent(id, Long.parseLong(arrids[i]));
            }
        } catch (Exception e) {
            datasuccess = "bderror";
        }
        //更新企业风险等级
        updateQyfxdj(sgfx.getID1());
        // 返回结果
        return datasuccess;
    }

    public void deleteInfo(long id) {
        fxgkFxxxDao.deleteInfo(id);
    }

    public FXGK_AccidentRisk findById(Long id) {
        return fxgkFxxxDao.find(id);
    }

    public FXGK_AccidentRisk find_sgfx_ById(Long id) {
        return fxgkFxxxDao.findUniqueBy("ID", id);
    }

    public boolean checkSameBuildContent(long fxxxid, String bindcontent) {
        return fxgkFxxxDao.checkSameBuildContent(fxxxid, bindcontent);
    }

    public boolean checkSameRfid(long fxxxid, String rfid) {
        return fxgkFxxxDao.checkSameRfid(fxxxid, rfid);
    }

    /**
     * 根据id查询风险点详细信息
     *
     * @return
     */
    public Map<String, Object> findInforById(Long id) {
        return fxgkFxxxDao.findInforById(id);
    }

    /**
     * 根据qyid查询风险点详细信息
     *
     * @return
     */
    public List<Map<String, Object>> getFXByqyid(Long id) {
        return fxgkFxxxDao.getFXByqyid(id);
    }

    public Map<String, Object> xjnrdataGrid(Map<String, Object> mapData) {
        List<Map<String, Object>> list = fxgkFxxxDao.xjnrdataGrid(mapData);
        int getTotalCount = fxgkFxxxDao.getxjnrTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }

    public Map<String, Object> xjnrdataGrid2(Map<String, Object> mapData) {
        List<Map<String, Object>> list = fxgkFxxxDao.xjnrdataGrid2(mapData);
        int getTotalCount = fxgkFxxxDao.getxjnrTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }

    public Map<String, Object> xjnrdataGrid3(Map<String, Object> mapData) {
        List<Map<String, Object>> list = new ArrayList<>();
        int getTotalCount = 0;
        list = fxgkFxxxDao.xjnrdataGrid3(mapData);
        getTotalCount = fxgkFxxxDao.getxjnrTotalCount2(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }

    public void deleteXjnr(long id) {
        fxgkFxxxDao.deleteXjnr(id);
    }

    public void deleteXjnrByAccid(long id1) {
        fxgkFxxxDao.deleteXjnrByAccid(id1);
    }

    public Map<String, Object> xjnralldataGrid(Map<String, Object> mapData) {
        List<Map<String, Object>> list = fxgkFxxxDao.xjnralldataGrid(mapData);
        int getTotalCount = fxgkFxxxDao.getxjnrallTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }

    public void bulidCheckContent(long id1, long id2) {
        YHPC_RiskPoint_Content y = new YHPC_RiskPoint_Content();
        y.setID1(id1);
        y.setID2(id2);
        yhpcRiskPointContentDao.save(y);
    }

    //修改企业风险等级
    public void updateQyfxdj(long id) {
        BIS_EnterpriseEntity bis = bisQyjbxxService.findInfoById(id);
        FXGK_RiskPerEntity riskp = fxgkfxzpzdao.findInfor();
        Map<String, Object> mapdata = new HashMap<>();
        mapdata.put("qyid", id);
        List<Map<String, Object>> listm = fxgkTjfxDao.findMapList(mapdata);
        if (listm.size() > 0) {
            Map<String, Object> map = listm.get(0);
            if (map.get("m1") != null && !map.get("m1").toString().equals("")) {
                int Rhong = (int) map.get("hong");
                int Rcheng = (int) map.get("cheng");
                int Rhuang = (int) map.get("huang");
                int Rlan = (int) map.get("lan");
                float level = (float) (Math.round((riskp.getM1() * Rhong + riskp.getM2() * Rcheng + riskp.getM3() * Rhuang + riskp.getM4() * Rlan) * 10)) / 10;
                String yanse = "";
                if (level >= riskp.getM5()) {
                    yanse = "1";
                } else if (level >= riskp.getM6()) {
                    yanse = "2";
                } else if (level >= riskp.getM7()) {
                    yanse = "3";
                } else if (level < riskp.getM7()) {
                    yanse = "4";
                }
                bis.setM44(yanse);
            }
        } else {
            bis.setM44("4");
        }
        bisQyjbxxService.updateInfo(bis);
    }

    //获取所有企业的风险值
    public List<Map<String, Object>> getQyfxzJson(Map<String, Object> mapdata) {
        FXGK_RiskPerEntity riskp = fxgkfxzpzdao.findInfor();
        List<Map<String, Object>> listm = fxgkTjfxDao.findMapList(mapdata);
        if (listm.size() > 0) {
            for (Map<String, Object> map : listm) {
                Object lng = map.get("m16");
                Object lat = map.get("m17");
                int Rhong = (int) map.get("hong");
                int Rcheng = (int) map.get("cheng");
                int Rhuang = (int) map.get("huang");
                int Rlan = (int) map.get("lan");
                int count = (int) (riskp.getM1() * Rhong + riskp.getM2() * Rcheng + riskp.getM3() * Rhuang + riskp.getM4() * Rlan);
                map.clear();
                count = count < 100 ? 100 : count;
                map.put("count", count);
                map.put("lng", lng);
                map.put("lat", lat);
            }
        }
        return listm;
    }

    //获取所有网格的风险值
    public List<Map<String, Object>> getBarriofxzJson(Map<String, Object> mapdata) {
        List<Map<String, Object>> list = fxgkTjfxDao.findBarrioMapList(mapdata);
        return list;
    }


    /**
     * 分页显示 app
     *
     * @param mapData
     * @return
     */
    public Map<String, Object> dataGridForApp(Map<String, Object> mapData) {

        List<Map<String, Object>> list = fxgkFxxxDao.dataGridForApp(mapData);
        //System.out.println(list);
        int getTotalCount = fxgkFxxxDao.getTotalCount(mapData);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", list);
        map.put("total", getTotalCount);
        return map;
    }

    /**
     * 导入
     *
     * @param response
     * @param mapData
     */
    public Map<String, Object> exinExcel(Map<String, Object> map) {
        ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
        User user = UserUtil.getCurrentUser();//获取当前登录用户的对象
        Map<String, Object> resultmap = new HashMap<String, Object>();
        int result = 0;
        ExinExcel exin = new ExinExcel();
        List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
        int i = 0, error = 0;
        if (list.size() > 3) {
            result = 0;
            resultmap.put("total", list.size() - 3);
            resultmap.put("returncode", "success");
            for (List<Object> bis : list) {
                if (i <= 2) { //跳过前三行
                    i++;
                    continue;
                }
                try {
                    FXGK_AccidentRisk fxgk = new FXGK_AccidentRisk();

                    fxgk.setID1(Long.valueOf(map.get("qyid").toString()));
                    fxgk.setDepid(sessionuser.getDep().getId());//部门id
                    Timestamp t = DateUtils.getSysTimestamp();
                    fxgk.setS1(t);
                    fxgk.setS2(t);
                    fxgk.setS3(0);
                    fxgk.setBindcontent(UUID.randomUUID().toString().replaceAll("-", ""));
                    fxgk.setM0(replaceBlank(bis.get(0).toString()));
                    fxgk.setM1(replaceBlank(bis.get(1).toString()));
                    fxgk.setM5(replaceBlank(bis.get(2).toString()));
                    fxgk.setM6(replaceBlank(bis.get(3).toString()));
                    fxgk.setAreaname(replaceBlank(bis.get(4).toString()));
                    fxgk.setM13(replaceBlank(bis.get(5).toString()));
                    fxgk.setM7(replaceBlank(bis.get(6).toString()));
                    fxgk.setM10(replaceBlank(bis.get(7).toString()));
                    fxgk.setM12(replaceBlank(bis.get(8).toString()));
                    fxgk.setM8(replaceBlank(bis.get(9).toString()));
                    fxgk.setM3("冶金");

                    String ys = replaceBlank(bis.get(10).toString());
                    String m9 = "";
                    if (ys.equals("重大风险"))
                        m9 = "1";
                    else if (ys.equals("较大风险"))
                        m9 = "2";
                    else if (ys.equals("一般风险"))
                        m9 = "3";
                    else if (ys.equals("低风险"))
                        m9 = "4";
                    fxgk.setM9(m9);//风险分级

                    fxgkFxxxDao.save(fxgk);
                    result++;
                } catch (Exception e) {
                    error++;
                }
                resultmap.put("success", result);
                resultmap.put("error", error);
            }
            //更新企业风险等级
            updateQyfxdj(Long.valueOf(map.get("qyid").toString()));
        } else if (list.size() == 3) {
            resultmap.put("success", result);
            resultmap.put("error", error);
            resultmap.put("returncode", "warn");
        } else if (list.size() < 3) {
            resultmap.put("success", result);
            resultmap.put("error", error);
            resultmap.put("returncode", "ext");
        }
        if (Integer.valueOf(resultmap.get("success").toString()) == 0) {
            resultmap.put("returncode", "warn");
        }
        return resultmap;

    }

    //过滤导入数据
    public String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
